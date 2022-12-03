package ir.co.sadad.cheque.service;

import com.ibm.icu.util.TimeZone;
import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.entity.Cheque;
import ir.co.sadad.cheque.domain.enums.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.repository.ChequeRepository;
import ir.co.sadad.cheque.service.mapper.AllocatingInquiryMapper;
import ir.co.sadad.cheque.service.mapper.BouncedChequeInquiryMapper;
import ir.co.sadad.cheque.utils.DateConvertor;
import ir.co.sadad.cheque.web.rest.errors.ChequeException;
import ir.co.sadad.cheque.web.rest.external.BamClient;
import ir.co.sadad.cheque.web.rest.external.ChequeClient;
import ir.co.sadad.cheque.web.rest.external.InquiryRemainedChequeClient;
import ir.co.sadad.cheque.web.rest.external.SadadRestClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.BouncedChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.ChequeRegisterDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.ChequeReportRequest;
import ir.co.sadad.cheque.web.rest.external.dto.request.SamatInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChequeServiceImp implements ChequeService, AccessTokenProcessor {

    private final BamClient bamClient;
    private final SadadRestClient sadadRestClient;
    private final ChequeRepository chequeRepository;
    private final InquiryRemainedChequeClient inquiryRemainedChequeClient;
    private final MessageSource messageSource;
    private final ChequeClient chequeClient;
    private final SsoClientTokenManager ssoClientTokenManager;
    private final HttpServletRequest request;
    private final AllocatingInquiryMapper allocatingEstelamRequestDto;
    private final BouncedChequeInquiryMapper bouncedChequeInquiryMapper;
    private static final Locale LOCALE_FA = new Locale("fa");

    @Override
    public ChequeRequestResponse chequeRequest(ChequeRequestDto chequeRequestDto) {
        String userSsn = getCurrentUserInfo().getBmiOAuth2User().getSsn();
        String passwordToken = getCurrentUserInfo().getAccessToken();
        String clientToken = ssoClientTokenManager.getClientToken();

        /* Check for duplicate requests*/
        List<ChequeStatus> chequeStatusList = Stream
            .of(ChequeStatus.REGISTER_REQUEST, ChequeStatus.CONFIRM_REQUEST, ChequeStatus.WAITING)
            .collect(Collectors.toList());
        if (!chequeRepository.findAllByNationalCodeAndChequeStatusIn(userSsn, chequeStatusList).isEmpty()) {
            throw new ChequeException(ChequeErrorType.DUPLICATE_REQUEST);
        }

        /* 1- check is the first check */
        if (isFirstCheque(passwordToken)) {
            ChequeRequestResponse chequeRequestResponse = new ChequeRequestResponse();
            chequeRequestResponse.setStatus(HttpStatus.NO_CONTENT.value());
            chequeRequestResponse.setErrorDescription(initializeMessage("cheque-api.error.first.cheque.request.requirement", LOCALE_FA));
            chequeRequestResponse.setRemainedCheque("0");
            return chequeRequestResponse;
        }
        /* 2- Check Overdue Facilities */
        if (!hasOverdueFacilities(clientToken, userSsn).isEmpty()) {
            ChequeRequestResponse chequeRequestResponse = new ChequeRequestResponse();
            chequeRequestResponse.setStatus(HttpStatus.CREATED.value());
            chequeRequestResponse.setDelayedFacility(hasOverdueFacilities(passwordToken, userSsn));
            chequeRequestResponse.setErrorDescription(initializeMessage("cheque-api.error.has.over.due.facilities", LOCALE_FA));
            return chequeRequestResponse;
        }

        /* 3- Check Returned Cheque */
        if (!hasReturnedCheque(clientToken, userSsn).isEmpty()) {
            ChequeRequestResponse chequeRequestResponse = new ChequeRequestResponse();
            chequeRequestResponse.setStatus(HttpStatus.ACCEPTED.value());
            chequeRequestResponse.setReturnedCheque(hasReturnedCheque(passwordToken, userSsn));
            chequeRequestResponse.setErrorDescription(initializeMessage("cheque-api.error.has.returned.cheque", LOCALE_FA));
            return chequeRequestResponse;
        }
        /* 4- Check Remained Cheque */
        String chequeRemainedResult = hasRemainedTwentyPercentCheque(passwordToken, chequeRequestDto.getAccountNo(), userSsn);
        if (hasRemainedTwentyPercentCheque(passwordToken, chequeRequestDto.getAccountNo(), userSsn) != null) {
            ChequeRequestResponse chequeRequestResponse = new ChequeRequestResponse();
            chequeRequestResponse.setStatus(HttpStatus.NO_CONTENT.value());
            chequeRequestResponse.setErrorDescription(chequeRemainedResult);
            chequeRequestResponse.setRemainedCheque(chequeRemainedResult);
            return chequeRequestResponse;
        }
        // TODO: 12/27/2021  move to the controler
        if (!Objects.requireNonNull(AccountType.getAccountType(chequeRequestDto.getAccountNo())).equals(AccountType.CURRENT)) {
            throw new ChequeException(ChequeErrorType.INVALID_ACCOUNT_TYPE);
        }

        /**Register Cheque Service */
        SelectedAccountDto selectedAccountDto = getAccount(passwordToken, chequeRequestDto.getAccountNo());
        chequeRegister(clientToken, selectedAccountDto.getIban(), userSsn);
        Cheque cheque = new Cheque();
        cheque.setChequeAccountType(ChequeAccountType.SINGLE);
        cheque.setChequeStatus(ChequeStatus.REGISTER_REQUEST);
        cheque.setRequestInputType(RequestInputType.BAAM);
        cheque.setRegisterDateTime(new Date());
        cheque.setLastUpdateDateTime(new Date());
        cheque.setAccountId(chequeRequestDto.getAccountNo());
        cheque.setIban(selectedAccountDto.getIban());
        cheque.setAccountId(chequeRequestDto.getAccountNo());
        cheque.setBranchCode(selectedAccountDto.getBranchCode());
        cheque.setNationalCode(userSsn);
        chequeRepository.save(cheque);

        ChequeInfo chequeInfo = new ChequeInfo();
        chequeInfo.setAccountNo(selectedAccountDto.getAccountNo());
        chequeInfo.setIban(selectedAccountDto.getIban());
        chequeInfo.setBranchCode(selectedAccountDto.getBranchCode());

        ChequeRequestResponse chequeRequestResponse = new ChequeRequestResponse();
        chequeRequestResponse.setStatus(HttpStatus.OK.value());
        chequeRequestResponse.setSuccess(chequeInfo);

        return chequeRequestResponse;
    }

    @Override
    public AllocatingEstelamResponseDto chequeAllocatingInquiry(AllocatingInquiryRequestDto allocatingInquiryRequestDto) {
        String clientToken = ssoClientTokenManager.getClientToken();
        AllocatingEstelamResponseDto allocatingEstelamResult = chequeClient.getAllocatingEstelam(
            clientToken,
            allocatingEstelamRequestDto.mapToRequest(allocatingInquiryRequestDto)
        );
        List<AllocatingEstelamDataDto> dataList = allocatingEstelamResult.getData();
        for (AllocatingEstelamDataDto item : dataList) {
            item.setCreateDate(DateConvertor.convertJalaliToEpoch(item.getCreateDate()));
            item.setSettlementDate(DateConvertor.convertJalaliToEpoch(item.getSettlementDate()));
            item.setDepositeDate(DateConvertor.convertJalaliToEpoch(item.getDepositeDate()));
        }
        return allocatingEstelamResult;
    }

    @Override
    public BouncedChequeResponseDto bouncedInquiry() {
        BouncedChequeRequestDto bouncedChequeRequestDto = bouncedChequeInquiryMapper.mapToRequest(
            getCurrentUserInfo().getBmiOAuth2User().getSsn()
        );
        String clientToken = ssoClientTokenManager.getClientToken();
        BouncedChequeInquiryResponseDto response = sadadRestClient
            .getBouncedChequeInquiry(clientToken, bouncedChequeRequestDto)
            .getResponse();
        if (response.getChequeInfo() != null) {
            response
                .getChequeInfo()
                .forEach(item -> {
                    try {
                        item.setBouncedDate(String.valueOf(DateConvertor.stringDateToEpoch(item.getBouncedDate())));
                        item.setDueDate(String.valueOf(DateConvertor.stringDateToEpoch(item.getDueDate())));
                    } catch (ParseException e) {
                        log.error("cheque-api.exception.bounced.inquiry.convert.date.error");
                        e.printStackTrace();
                    }
                });
        }

        return bouncedChequeInquiryMapper.mapToResponse(response);
    }

    @Override
    public BouncedChequeResponseDto bouncedInquiryWithSsn(String ssn) {
        BouncedChequeRequestDto bouncedChequeRequestDto = bouncedChequeInquiryMapper.mapToRequest(ssn);
        String clientToken = ssoClientTokenManager.getClientToken();
        BouncedChequeInquiryResponseDto response = sadadRestClient
            .getBouncedChequeInquiry(clientToken, bouncedChequeRequestDto)
            .getResponse();
        return bouncedChequeInquiryMapper.mapToResponse(response);
    }

    @Override
    public ChequeResponse report(String iban, int timeInterval) {
        Cheque topByIban;
        try {
            topByIban = chequeRepository.findTopByIban(iban);
            if (topByIban == null) {
                throw new ChequeException(ChequeErrorType.IBAN_WITHOUT_CHEQUE_REQUEST);
            } else if (!topByIban.getNationalCode().equals(getCurrentUserInfo().getBmiOAuth2User().getSsn())) {
                throw new ChequeException(ChequeErrorType.LACK_OF_ACCESS_TO_ACCOUNT);
            }
        } catch (NoResultException ex) {
            log.error("sayad report service has error");
            throw new ChequeException(ChequeErrorType.IBAN_WITHOUT_CHEQUE_REQUEST);
        }

        ChequeResponse chequeResponse = callChequeReport(timeInterval, topByIban);

        ChequeData maxTime = chequeResponse
            .getChequeDataList()
            .stream()
            .max(Comparator.comparing(ChequeData::getUpdateTime))
            .orElseThrow(NoSuchElementException::new);
        topByIban.setLastUpdateDateTime(new Date((maxTime.getUpdateTime())));

        chequeResponse
            .getChequeDataList()
            .forEach(chequeData -> {
                if (!chequeData.getChequeNumber().equals("") && chequeData.getChequeNumber().contains("-")) {
                    String[] chequeNumber = chequeData.getChequeNumber().split("-");
                    topByIban.setChequeFirstNumber(chequeNumber[0]);
                    topByIban.setChequeLastNumber(chequeNumber[1]);
                }
            });

        chequeRepository.save(topByIban);
        return chequeResponse;
    }

    private boolean isFirstCheque(String bearerToken) {
        List<ChequebookResponseDto> bouncedChequeInquiry = bamClient.checkIsFirstCheque(bearerToken);
        return bouncedChequeInquiry == null;
    }

    private List<HashMap<String, Object>> hasOverdueFacilities(String bearerToken, String ssn) {
        SamatInquiryRequestDto samatInquiryRequestDto = new SamatInquiryRequestDto();
        samatInquiryRequestDto.setCustomerType(1); // set static for real customer
        samatInquiryRequestDto.setCustomerIdentifier(ssn);
        samatInquiryRequestDto.setInquiryIdentifier("");
        samatInquiryRequestDto.setIndividualFirstName("");
        samatInquiryRequestDto.setIndividualLastNameOrLegalName("");

        ApiGeneralResponseDto<SamatInquiryMainResponseDto> samatInquiryMainResponseDtoApiGeneralResponseDto = null;
        try {
            samatInquiryMainResponseDtoApiGeneralResponseDto = sadadRestClient.inquiryFacility(bearerToken, samatInquiryRequestDto);
        } catch (Exception e) {
            throw new ChequeException(ChequeErrorType.SERVICE_HAS_ERROR);
        }

        List<HashMap<String, Object>> inquiryFacilityList = new ArrayList();
        if (samatInquiryMainResponseDtoApiGeneralResponseDto.getResponse().getInquiryEntries() != null) {
            samatInquiryMainResponseDtoApiGeneralResponseDto
                .getResponse()
                .getInquiryEntries()
                .forEach(response -> {
                    long pastDueBalance = response.getPastDueBalance();
                    long overDueBalance = response.getOverDueBalance();
                    long doubtfulBalance = response.getDoubtfulBalance();
                    long destroyAmount = response.getDestroyAmount() == null ? 0 : response.getDestroyAmount();
                    if (pastDueBalance != 0 || overDueBalance != 0 || doubtfulBalance != 0 || destroyAmount != 0) {
                        try {
                            long contractTime = new SimpleDateFormat("yyyy-MM-dd").parse(response.getContractDate()).getTime();
                            long badCreditAmount = pastDueBalance + overDueBalance + doubtfulBalance + destroyAmount;
                            String[] bankInfo = response.getBankCode().split("-");
                            HashMap<String, Object> inquiryFacilityMap = new HashMap<String, Object>();
                            inquiryFacilityMap.put("contractDate", contractTime);
                            inquiryFacilityMap.put("bankCode", response.getBankCode());
                            inquiryFacilityMap.put("branchCode", response.getBranchCode());
                            inquiryFacilityMap.put("bankName", bankInfo[0].trim());
                            inquiryFacilityMap.put("branchName", bankInfo[bankInfo.length - 1].trim());
                            inquiryFacilityMap.put("badCreditAmount", badCreditAmount);
                            inquiryFacilityList.add(inquiryFacilityMap);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
        }

        return inquiryFacilityList;
    }

    private List<HashMap<String, Object>> hasReturnedCheque(String bearerToken, String ssn) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerType(1); // set static for real customer
        customerInfo.setNationalIdentifier(ssn);
        BouncedChequeRequestDto bouncedChequeRequestDto = new BouncedChequeRequestDto(customerInfo, new Date().getTime() + ssn);
        ApiGeneralResponseDto<BouncedChequeInquiryResponseDto> bouncedChequeInquiry = null;
        try {
            bouncedChequeInquiry = sadadRestClient.getBouncedChequeInquiry(bearerToken, bouncedChequeRequestDto);
        } catch (Exception e) {
            throw new ChequeException(ChequeErrorType.SERVICE_HAS_ERROR);
        }

        List<HashMap<String, Object>> returnedChequeList = new ArrayList();
        if (bouncedChequeInquiry.getResponse().getChequeInfo() != null) {
            bouncedChequeInquiry
                .getResponse()
                .getChequeInfo()
                .forEach(response -> {
                    String[] bankInfo = response.getChequeIdentifier().toString().split("-");
                    HashMap<String, Object> returnedChequeMap = new HashMap<String, Object>();

                    returnedChequeMap.put("chequeIdentifier", response.getChequeIdentifier());
                    returnedChequeMap.put("bankCode", response.getBankCode());
                    returnedChequeMap.put("branchCode", response.getBouncedBranchCode());
                    returnedChequeMap.put("bankName", bankInfo[0].trim());
                    returnedChequeMap.put("branchName", bankInfo[bankInfo.length - 1].trim());
                    returnedChequeMap.put("iban", response.getIban());
                    returnedChequeMap.put("chequeSerialNumber", response.getChequeSerial());
                    returnedChequeList.add(returnedChequeMap);
                });
        }
        return returnedChequeList;
    }

    private String hasRemainedTwentyPercentCheque(String bearerToken, String accountId, String ssn) {
        List<ChequeStatus> chequeStatusList = Stream.of(ChequeStatus.PRINT_CHEQUE).collect(Collectors.toList());
        List<Cheque> printedChequeList = chequeRepository.findAllByNationalCodeAndChequeStatusIn(ssn, chequeStatusList);
        Cheque printedCheque = null;
        if (printedChequeList != null) {
            printedCheque = !printedChequeList.isEmpty() ? printedChequeList.get(0) : null;
        }

        RemainedTwentyPercentChequeResponseDto bouncedChequeInquiry = inquiryRemainedChequeClient.callService(bearerToken, accountId);
        if (bouncedChequeInquiry.getStatus() == 202) {
            if (printedCheque == null) {
                return null;
            } else {
                String messageTemplate = messageSource.getMessage("cheque.error.remained.cheque.message", null, request.getLocale());
                return String.format(messageTemplate, "25", printedCheque.getBranchCode());
            }
        } else {
            String messageTemplate = messageSource.getMessage("cheque.error.remained.cheque.message", null, request.getLocale());
            String strPattern = "^0+(?!$)";
            String notPresentedCheque = bouncedChequeInquiry.getNotPresentedChequeNo().replaceAll(strPattern, "");
            return String.format(messageTemplate, notPresentedCheque, bouncedChequeInquiry.getBranchCode());
        }
    }

    private SelectedAccountDto getAccount(String bearerToken, String accountId) {
        SelectedAccountDto selectedAccountDto = new SelectedAccountDto();
        try {
            List<AccountDto> bouncedChequeInquiry = bamClient.getAccountList(bearerToken);
            bouncedChequeInquiry.forEach(account -> {
                if (account.getId().equals(accountId)) {
                    String branchCode = String.valueOf(account.getBranchCode());
                    selectedAccountDto.setBranchCode(("0000" + branchCode).substring(branchCode.length()));
                    selectedAccountDto.setIban(account.getIban());
                    selectedAccountDto.setAccountNo(accountId);
                }
            });
        } catch (Exception e) {
            log.error("get account info has error");
            throw new ChequeException(ChequeErrorType.SERVICE_HAS_ERROR);
        }
        if (selectedAccountDto.getAccountNo() == null) {
            log.error("lack of access to account");
            throw new ChequeException(ChequeErrorType.LACK_OF_ACCESS_TO_ACCOUNT);
        }
        return selectedAccountDto;
    }

    private boolean chequeRegister(String sayadToken, String iban, String ssn) {
        List<AccountOwner> accountOwnerList = new ArrayList<>();
        AccountOwner accountOwner = new AccountOwner();
        accountOwner.setIdentifier(ssn);
        accountOwner.setPersonType(0);
        accountOwnerList.add(accountOwner);

        ChequeRegisterDto chequeRegisterDto = new ChequeRegisterDto();
        chequeRegisterDto.setIban(iban);
        chequeRegisterDto.setAccountType(0);
        chequeRegisterDto.setSheetCount(1);
        chequeRegisterDto.setAccountOwner(accountOwnerList);

        int messageCode = chequeClient.sayadRequest(sayadToken, chequeRegisterDto).getMessageCode();
        log.info("cheque register result status : " + messageCode);

        if (messageCode == 100) {
            return true;
        } else {
            log.error("sayad register service error : " + ChequeErrorType.valueOfResultCode(messageCode).getDescription());
            ChequeErrorType chequeErrorType = ChequeErrorType.valueOfResultCode(messageCode);
            throw new ChequeException(chequeErrorType);
        }
    }

    private ChequeResponse callChequeReport(int timeInterval, Cheque cheque) {
        String clientToken = ssoClientTokenManager.getClientToken();
        final ChequeReportResponseDto report = chequeClient.report(clientToken, new ChequeReportRequest(cheque.getIban(), timeInterval));
        ChequeResponse chequeResponse = new ChequeResponse();
        List<ChequeData> chequeDataList = new ArrayList<>();
        if (report.getMessageCode() == 100) {
            for (int i = 0; i < report.getData().size(); i++) {
                ChequeData chequeData = new ChequeData();
                chequeData.setSheetCount(report.getData().get(i).getSheetCount());

                TimeZone timeZone = com.ibm.icu.util.TimeZone.getTimeZone("UTC");
                com.ibm.icu.util.ULocale locale = new com.ibm.icu.util.ULocale("fa_IR@calendar=persian");
                com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(timeZone, locale);

                String date1 = String.valueOf(report.getData().get(i).getRequestDate());
                calendar.set(
                    Integer.parseInt(date1.substring(0, 4)),
                    Integer.parseInt(date1.substring(4, 6)) - 1,
                    Integer.parseInt(date1.substring(6, 8))
                );
                Date registerDate = calendar.getTime();
                chequeData.setRequestDate(registerDate.getTime());

                String date = String.valueOf(report.getData().get(i).getUpdateDate());
                calendar.set(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(4, 6)) - 1,
                    Integer.parseInt(date.substring(6, 8))
                );
                Date updateDate = calendar.getTime();
                chequeData.setUpdateTime(updateDate.getTime());

                ChequeStatus chequeStatus = ChequeStatus.valueOfResultCode(report.getData().get(i).getRequestStatus());
                cheque.setChequeStatus(chequeStatus);
                chequeData.setChequeStatusIndex(chequeStatus.getCode());
                chequeData.setChequeStatus(chequeStatus.getDescription());
                chequeData.setRequestInput(RequestInputType.valueOfResultCode(report.getData().get(i).getRequestInput()).name());
                chequeData.setChequeNumber(report.getData().get(i).getChequeNumber());
                chequeDataList.add(chequeData);
            }
            chequeResponse.setAccountNo(cheque.getAccountId());
            chequeResponse.setIban(cheque.getIban());
            chequeResponse.setBranchCode(cheque.getBranchCode());
            chequeResponse.setIdentifier(getCurrentUserInfo().getBmiOAuth2User().getSsn());
            List<ChequeData> chequeDataListResult = chequeDataList
                .stream()
                .sorted(Comparator.comparing(ChequeData::getUpdateTime).reversed())
                .collect(Collectors.toList());
            chequeResponse.setChequeDataList(chequeDataListResult);
            return chequeResponse;
        } else {
            log.error("sayad report service error : " + ChequeErrorType.valueOfResultCode(report.getMessageCode()).getDescription());
            ChequeErrorType chequeErrorType = ChequeErrorType.valueOfResultCode(report.getMessageCode());
            throw new ChequeException(chequeErrorType);
        }
    }

    private String initializeMessage(String message, Locale locale) {
        return messageSource.getMessage(message, null, locale);
    }
}
