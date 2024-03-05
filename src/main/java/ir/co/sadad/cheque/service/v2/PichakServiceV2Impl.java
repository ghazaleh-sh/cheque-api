package ir.co.sadad.cheque.service.v2;

import ir.co.sadad.cheque.domain.dto.SayadChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.domain.dto.v2.PichakLeafInquiryResponseDto;
import ir.co.sadad.cheque.domain.dto.v2.PichakReportResponseDto;
import ir.co.sadad.cheque.domain.dto.v2.PichakRequestChequeReqDto;
import ir.co.sadad.cheque.domain.dto.v2.SuccessClientResponseDto;
import ir.co.sadad.cheque.domain.enums.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.SayadMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.errors.SayadClientException;
import ir.co.sadad.cheque.web.rest.errors.SayadInquiresClientException;
import ir.co.sadad.cheque.web.rest.external.BillClient;
import ir.co.sadad.cheque.web.rest.external.EsServiceClient;
import ir.co.sadad.cheque.web.rest.external.PichakEgwClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ir.co.sadad.cheque.utils.ConverterHelper.setUserType;
import static ir.co.sadad.cheque.utils.DateConvertor.convertToUTC;

@Log4j2
@Service
public class PichakServiceV2Impl extends PichakServiceV2 {

    private final EsServiceClient esServiceClient;
    private final PichakEgwClient pichakEgwClient;
    private final SayadMapper sayadMapper;
    private final BillClient billClient;

    public PichakServiceV2Impl(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager, EsServiceClient esServiceClient, PichakEgwClient pichakEgwClient, SayadMapper sayadMapper, BillClient billClient) {
        super(httpServletRequest, ssoClientTokenManager);
        this.esServiceClient = esServiceClient;
        this.pichakEgwClient = pichakEgwClient;
        this.sayadMapper = sayadMapper;
        this.billClient = billClient;
    }

    @Override
    @SneakyThrows
    public SuccessClientResponseDto requestCheque(PichakRequestChequeReqDto requestChequeReqDto, String authToken) {
        String userSsn = getSsn();
        checkAccountBelongToUser(requestChequeReqDto.getIban()
            .substring(requestChequeReqDto.getIban().length() - 13), userSsn);

        SayadRequestResponseDto sayadRequestResponseDto =
            esServiceClient.sayadRequest(getToken(), buildChequeRequest(requestChequeReqDto));

        if (sayadRequestResponseDto.getMessageCode() != SUCCESS_SAYAD_CODE) {
            throw new SayadClientException(sayadRequestResponseDto.getMessageCode());
        }

        return SuccessClientResponseDto.builder()
            .isSucceeded(true).build();
    }

    @Override
    @SneakyThrows
    public List<PichakReportResponseDto> reportCheque(String iban, String authToken) {
        String userSsn = getSsn();
        checkAccountBelongToUser(iban.substring(iban.length() - 13), userSsn);

        SayadReportResponseDto sayadReportResponse =
            esServiceClient.sayadReport(getToken(), SayadReportRequestDto.builder()
                .IBAN(iban)
                .length(REPORT_LENGTH)
                .build());

        if (sayadReportResponse.getMessageCode() != SUCCESS_SAYAD_CODE &&
            sayadReportResponse.getMessageCode() != ACCOUNT_DOES_NOT_HAVE_CHEQUE)
            throw new SayadClientException(sayadReportResponse.getMessageCode());

        return buildReportResponse(sayadReportResponse.getData()
            .stream().filter(requestItemDto -> requestItemDto.getMediaType() == null
                || Objects.equals(requestItemDto.getMediaType(), DIGITAL_MEDIA_TYPE))
            .collect(Collectors.toList()));
    }

    @Override
    @SneakyThrows
    public List<SayadChequeInquiryBatchResponseDto> ChequeBookInquiry(String accountNumber, String authToken) {
        String userSsn = getSsn();
        checkAccountBelongToUser(accountNumber, userSsn);

        ChequeInquiryBatchResponseDto response =
            pichakEgwClient.bookInquiry(getToken(),
                getUserAgent(),
                "",
                buildBookInquiryRequest(accountNumber, userSsn));

        if (response.getIsSuccess())
            return buildChequeBookInquiryResponse(response.getResult()
                .stream().filter(result -> ((result.getChequeMedia() == null || !result.getChequeMedia())
                    && result.getStatusCode().equals(ISSUED_CODE)))
                .collect(Collectors.toList()));

        throw new SayadInquiresClientException(response.getErrorCodes());
    }

    @Override
    @SneakyThrows
    public List<PichakLeafInquiryResponseDto> ChequeLeafInquiry(Long chequeIssuedId, String accountNumber, String authToken) {
        String userSsn = getSsn();
        checkAccountBelongToUser(accountNumber, userSsn);

        ChequeInquirySheetResponseDto response = pichakEgwClient.sheetInquiry(getToken(),
            getUserAgent(),
            "",
            buildLeafInquiryRequest(chequeIssuedId));

        if (response.getIsSuccess()) {
            return buildChequeLeafInquiryResponse(response.getResult());
        }
        throw new SayadInquiresClientException(response.getErrorCodes());
    }

    private List<PichakLeafInquiryResponseDto> buildChequeLeafInquiryResponse(List<ChequeInquirySheetDataResponseDto> result) {
        List<PichakLeafInquiryResponseDto> totalResponse = new ArrayList<>();
        result.forEach(leaf -> {
            PichakLeafInquiryResponseDto leafResponse = sayadMapper.toSingleLeafResponse(leaf);
            leafResponse.setChequeType(InquiryChequeType.getByCode(leaf.getChequeType()));
            leafResponse.setLocalizedChequeType(leafResponse.getChequeType().getDescription());

            leafResponse.setStatusType(LeafInquiryStatusType.getByCode(leaf.getStatus()));
            leafResponse.setLocalizedStatusType(leafResponse.getStatusType().getDescription());

            leafResponse.setBounceStatusType(LeafInquiryReturnStatusType.getByCode(leafResponse.getBounceStatus()));
            leafResponse.setLocalizedBounceStatusType(leafResponse.getBounceStatusType().getDescription());

            leafResponse.setBlockStatusType(LeafInquiryBlockStatus.getByCode(leafResponse.getBlockStatus()));
            leafResponse.setLocalizedBlockStatusType(leafResponse.getBlockStatusType().getDescription());

            leafResponse.setBusinessType(LeafInquiryBusinessType.getByCode(leaf.getBusinessType()));
            leafResponse.setLocalizedBusinessType(leafResponse.getBusinessType().getDescription());

            leafResponse.setClearType(LeafInquiryEreqType.getByCode(leaf.getClearType()));
            leafResponse.setLocalizedClearType(leafResponse.getClearType().getDescription());

            leafResponse.setDepositDate(convertToUTC(leaf.getDepositDate()));
            leafResponse.setIssueDateUtc(convertToUTC(leaf.getIssueDate()));
            leafResponse.setSettlementDate(convertToUTC(leaf.getSettlementDate()));

            totalResponse.add(leafResponse);
        });
        return totalResponse;
    }

    private ChequeInquirySheetRequestDto buildLeafInquiryRequest(Long chequeIssuedId) {
        ChequeInquirySheetRequestDto req = new ChequeInquirySheetRequestDto();
        req.setChequeIssueId(chequeIssuedId);
        req.setArchiveType(0);
        return req;
    }

    private List<SayadChequeInquiryBatchResponseDto> buildChequeBookInquiryResponse(List<ChequeInquiryBatchDataResponseDto> result) {
        List<SayadChequeInquiryBatchResponseDto> response = new ArrayList<>();
        result.forEach(eachCheque -> {
            SayadChequeInquiryBatchResponseDto inquiry = sayadMapper.toSingleBatchResponse(eachCheque);
            inquiry.setAccountType(BookInquiryAccountType.getByCode(eachCheque.getAccountType()));
            inquiry.setLocalizedAccountType(inquiry.getAccountType().getDescription());

            inquiry.setChequeType(InquiryChequeType.getByCode(eachCheque.getChequeType()));
            inquiry.setLocalizedChequeType(inquiry.getChequeType().getDescription());

            inquiry.setStateType(BookInquiryState.getByCode(eachCheque.getState()));
            inquiry.setLocalizedstateType(inquiry.getStateType().getDescription());

            inquiry.setStatus(BookInquiryStatusCode.getByCode(eachCheque.getStatusCode()));
            inquiry.setLocalizedStatus(inquiry.getStatus().getDescription());

            inquiry.setSheetCount(eachCheque.getSheetCount());

            inquiry.setChequeNumbers(eachCheque.getChequeNumbers() == null ? null : List.of(eachCheque.getChequeNumbers().split("-")));

            inquiry.setDeliveryDate(convertToUTC(eachCheque.getDeliveryDate()));
            inquiry.setIssuanceDate(convertToUTC(eachCheque.getIssuanceDate()));

            response.add(inquiry);
        });
        return response;
    }

    private ChequeInquiryBatchRequestDto buildBookInquiryRequest(String accountNumber, String userSsn) {

        ChequeInquiryBatchRequestDto req = new ChequeInquiryBatchRequestDto();
        req.setAccountNumber(accountNumber);
        req.setIdentifier(userSsn);
        req.setIdentifierType(setUserType(userSsn));
        req.setAccountType(BookInquiryAccountType.ALL.getCode());
        req.setState(BookInquiryState.UNKNOWN.getCode());
        req.setStatusCode(BookInquiryStatusCode.UNKNOWN.getCode());
        req.setSheetCount(BOOK_SHEET_COUNT);

        return req;
    }

    private SayadRequestDto buildChequeRequest(PichakRequestChequeReqDto requestChequeReqDto) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        AccountOwnerDto ownerDto = new AccountOwnerDto();
        ownerDto.setIdentifier(userSsn);
        ownerDto.setPersonType(userType.equals(1) ? 0 : 1);
        List<AccountOwnerDto> listOwner = new ArrayList<>();
        listOwner.add(ownerDto);

        SayadRequestDto req = new SayadRequestDto();
        req.setIBAN(requestChequeReqDto.getIban());
        req.setSheetCount(requestChequeReqDto.getSheetCountId());
        req.setAccountType(0);  //انفرادی
        req.setMediaType(DIGITAL_MEDIA_TYPE);
        req.setAccountOwner(listOwner);

        return req;
    }

    private List<PichakReportResponseDto> buildReportResponse(List<RequestItemDto> data) {
        List<PichakReportResponseDto> listOfRes = new ArrayList<>();
        data.forEach(item -> {
            PichakReportResponseDto res = new PichakReportResponseDto();
            res.setChequeNumber(item.getChequeNumber());
            res.setRequestInput(RequestInputType.valueOfResultCode(item.getRequestInput()));
            res.setRequestDate(convertToUTC(item.getRequestDate().toString()));
            res.setUpdateDate(convertToUTC(item.getUpdateDate().toString()));
            res.setSheetCount(item.getSheetCount());
            res.setRequestStatus(PichakRequestStatus.getByCode(item.getRequestStatus()));
            res.setLocalizedRequestStatus(res.getRequestStatus().getDescription());

            listOfRes.add(res);
        });

        return listOfRes;
    }

    private void checkAccountBelongToUser(String accountNumber, String userSsn) {
        try {
            CustomerAccountResponseDto billResponse = billClient.getCustomerAccount(accountNumber);
            if (billResponse.getSSN() == null || !billResponse.getSSN().equals(userSsn))
                throw new ChakadClientException("CHQB.ACCOUNT.NUMBER.NOT.BELONG", Status.BAD_REQUEST);
        } catch (Exception e) {
            throw new ChakadClientException("CHQB.ACCOUNT.NUMBER.NOT.BELONG", Status.BAD_REQUEST);
        }
    }
}
