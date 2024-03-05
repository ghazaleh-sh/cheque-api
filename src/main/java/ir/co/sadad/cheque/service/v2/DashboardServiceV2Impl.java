package ir.co.sadad.cheque.service.v2;

import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.domain.entity.ChequeUserInfo;
import ir.co.sadad.cheque.domain.enums.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.repository.ChequeReasonsRepository;
import ir.co.sadad.cheque.service.ChequeIssueService;
import ir.co.sadad.cheque.service.StakeholderService;
import ir.co.sadad.cheque.service.TransferService;
import ir.co.sadad.cheque.service.UserInfoService;
import ir.co.sadad.cheque.service.mapper.DashboardMapper;
import ir.co.sadad.cheque.utils.DateConvertor;
import ir.co.sadad.cheque.web.rest.errors.BaambaseClientException;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.PichakClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static ir.co.sadad.cheque.utils.ConverterHelper.setUserType;
import static ir.co.sadad.cheque.utils.DateConvertor.convertCurrentToUTC;
import static ir.co.sadad.cheque.utils.DateConvertor.convertISO8601ToJalali;

/**
 * {@inheritDoc}
 */
@Log4j2
@Service
public class DashboardServiceV2Impl extends DashboardServiceV2 {

    private final PreDashboardV2Service preDashboardV2Service;
    private final UserInfoService userInfoService;
    private final ChequeIssueService chequeIssueService;
    private final StakeholderService stakeholderService;
    private final TransferService transferService;
    private final ChequeReasonsRepository reasonsRepository;
    private final ChakadClient chakadClient;
    private final PichakClient pichakClient;
    private final DashboardMapper mapper;
    private static final Integer AGENT_VALUE = 2;

    public DashboardServiceV2Impl(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager, PreDashboardV2Service preDashboardV2Service, UserInfoService userInfoService, ChakadClient chakadClient, ChequeIssueService chequeIssueService, StakeholderService stakeholderService, TransferService transferService, ChequeReasonsRepository reasonsRepository, ChakadClient chakadClient1, PichakClient pichakClient, DashboardMapper mapper) {
        super(httpServletRequest, ssoClientTokenManager);
        this.preDashboardV2Service = preDashboardV2Service;
        this.userInfoService = userInfoService;
        this.chequeIssueService = chequeIssueService;
        this.stakeholderService = stakeholderService;
        this.transferService = transferService;
        this.reasonsRepository = reasonsRepository;
        this.chakadClient = chakadClient1;
        this.pichakClient = pichakClient;
        this.mapper = mapper;
    }

    @Override
    @SneakyThrows
    public UserCertificationResponseDto userCertification(String authToken) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        CertificationResBodyDto bodyResponse = preDashboardV2Service.userCertifications(authToken);

        Optional<ChequeUserInfo> currentUserInfo = userInfoService.getByUserIdOptional(userSsn, userType.equals(2));

        UserCertificationStatus status = UserCertificationStatus.ACTIVATION;
        if (currentUserInfo.isPresent()) {
            ChequeUserInfo userInfo = currentUserInfo.get();
            ActivationResponseStatus activationStatus = userInfo.getActivationResponseStatus();
            String currentUserCid = userInfo.getCertificateKeyId();

            if (activationStatus != null) {
                if (activationStatus.equals(ActivationResponseStatus.ACTIVE)) {
                    if (!currentUserCid.equals(bodyResponse.getCertificateKeyId()))
                        status = UserCertificationStatus.UPDATE_PROFILE;
                    else
                        status = UserCertificationStatus.MATCHED;

                } else if (currentUserCid.equals(bodyResponse.getCertificateKeyId()))
                    throw new BaambaseClientException(INVALID_CERTIFICATION);
            }
        }

        UserCertificationResponseDto response = new UserCertificationResponseDto();
        response.setCertificateKeyId(bodyResponse.getCertificateKeyId());
        response.setDevice(bodyResponse.getDevice());
        response.setDeviceId(bodyResponse.getDeviceId());
        response.setProductType(TokenType.NAMAD);
        response.setProductId(bodyResponse.getProductUid().toString());
        response.setStatus(status);

        return response;
    }


    @Override
    @SneakyThrows
    public IssueRequestResDto requestIssue(IssueRequestReqDto issueRequestReq, String authToken) {

        ChequeReason existedReason = checkReasonValidation(issueRequestReq.getReason());

        String userSsn = getSsn();

        List<CustomerRequestDto> listOfReq = new ArrayList<>();

        List<UserDto> receivers = issueRequestReq.getReceivers();
        for (UserDto receiver : receivers) {
            Integer recType = setUserType(receiver.getIdentifier());
            listOfReq.add(getCustomerFromToken(receiver.getIdentifier(), recType));
        }

        ChakadResponseDto<InquiryStatusResponseDto> inquiryResForReceiversInfo = preDashboardV2Service.chakadClientInquiry(listOfReq);
        List<ActivationStatusDto> receiversStatus = inquiryResForReceiversInfo.getData().getActivationStatus();

        insertOrUpdateReceiversInfo(receivers, receiversStatus);

        String settlementDateJalali = convertISO8601ToJalali(issueRequestReq.getSettlementDate());
        String tbs = buildTbs(issueRequestReq, receiversStatus, settlementDateJalali);

        ChequeIssue savedIssue = insertIssue(issueRequestReq, tbs, existedReason, settlementDateJalali);

        UserInfoResponseDto currentUserInfo = userInfoService.getBy(userSsn, setUserType(userSsn).equals(2));

        insertStakeholderIssuer(currentUserInfo, issueRequestReq, savedIssue);
        insertStakeholderReceiver(receivers, savedIssue);
//        insertStakeholderGrantor();

        IssueRequestResDto response = new IssueRequestResDto();
        response.setCertificateKeyId(currentUserInfo.getCertificateKeyId());
        response.setProductType(currentUserInfo.getTokenType());
        response.setProductId(namadProductUid);
        response.setDataToBeSign(Base64.getEncoder().encodeToString(tbs.getBytes()));
        response.setSayadId(issueRequestReq.getSayadId());
        return response;
    }

    @Override
    @SneakyThrows
    public ChequeIssueResponseDto issue(ChequeIssueRequestDto issueReq, String authToken) {
        IssueResponseDto savedCheque = chequeIssueService.getBy(issueReq.getSayadId());
        List<StakeholderResponseDto> savedStakeholders = stakeholderService.getBy(issueReq.getSayadId());

        IssueChequeRequestDto req = createIssueClient(issueReq, savedCheque, savedStakeholders);
        ChakadResponseDto<ObjectUtils.Null> issueResponse = chakadClient.issueCheque(getToken(),
            getUserAgent(),
            req);

        if (!SUCCESS_CHAKAD_CODE.equals(issueResponse.getCode()))
            throw new ChakadClientException(issueResponse.getCode());

        try {
            chequeIssueService.updateIssueStatus(issueReq.getSayadId(), IssueStatus.ISSUED);
        } catch (Exception e) {
            //TODO: save into log, processing job later
            log.error("-----------------can not save ISSUED status into issue table");
        }

//TODO: call create and signedData baambase services + update signed field in issue table

        ChequeIssueResponseDto response = new ChequeIssueResponseDto();
        response.setAmount(savedCheque.getAmount());
        response.setSayadId(issueReq.getSayadId());
        response.setDescription(savedCheque.getDescription());
        response.setSerialNumber(savedCheque.getSerialNumber());
        response.setSeriNumber(savedCheque.getSeriNumber());
        response.setSettlementDate(savedCheque.getSettlementDate());
        response.setReason(savedCheque.getChequeReason() != null ?
            savedCheque.getChequeReason().getReasonCode() : null);
        response.setResponseTime(convertCurrentToUTC());
        response.setReceivers(mapper.toUserReceiverDto(req.getCheque().getReceivers()));
        response.setSigners(mapper.toUserSignerDto(req.getCheque().getSigners()));

        return response;
    }

    @Override
    @SneakyThrows
    public List<ChequeCartableDto> cartable(String authToken) {
        String userSsn = getSsn();

        CartableRequestDto cartableReq = new CartableRequestDto();
        cartableReq.setIdCode(userSsn);
        cartableReq.setIdType(setUserType(userSsn));

        ChakadResponseDto<CartableResponseDto> cartable =
            chakadClient.cartable(getToken(), getUserAgent(), cartableReq);

        if (SUCCESS_CHAKAD_CODE.equals(cartable.getCode()))
            return cartableMapToResponseDto(cartable.getData().getCheques());
        else
            throw new ChakadClientException(cartable.getCode());
    }

    @Override
    public SuccessClientResponseDto acceptance(String sayadId, AcceptanceRequestDto acceptanceRequestDto, String authToken) {

        Integer statusCode = AcceptanceStatus.getCodeByDescription(acceptanceRequestDto.getAction());

        ChakadResponseDto<ObjectUtils.Null> acceptResponse = pichakClient.acceptCheques(getToken(),
            ACCEPT_SERVICE_USERNAME,
            buildAcceptRequest(acceptanceRequestDto, sayadId, statusCode));

        if (!SUCCESS_PICHAK_CODE.equals(acceptResponse.getMessageCode()))
            throw new ChakadClientException(acceptResponse.getMessageCode());

        chequeIssueService.updateIssueStatus(sayadId, Objects.equals(statusCode, 1)
            ? IssueStatus.APPROVED : IssueStatus.REJECTED);

        return SuccessClientResponseDto.builder().isSucceeded(true).build();
    }

    @Override
    @SneakyThrows
    public TransferResponseDto transfer(TransferRequestDto transferRequestDto, String authorizationCode, String authToken) {
        ChequeReason existedReason = checkReasonValidation(transferRequestDto.getReason());

        ChakadTransferTotalResponseDto transferResponse = chakadClient.transferCheques(getToken(),
            authToken,
            getUserAgent(),
            authorizationCode,
            buildTransferRequest(transferRequestDto));

        if (!SUCCESS_CHAKAD_CODE.equals(transferResponse.getMessage()))
            throw new ChakadClientException(transferResponse.getMessage());

        try {
            ChequeTransfer savedTransfer = transferService.createTransfer(transferRequestDto, existedReason);
            insertStakeholderTransfer(transferRequestDto, savedTransfer);

        } catch (Exception e) {
            //TODO: insert transferRequestDto into log table
            log.warn("--------transfer and its stakeholders insert data exception --------");

        }
        TransferResponseDto response = mapper.toTransferResponse(transferRequestDto);
        response.setCurrentDateTime(convertCurrentToUTC());
        return response;
    }


    @Override
    @SneakyThrows
    public SuccessClientResponseDto depositRegister(DepositRegisterRequestDto depositRegisterRequestDto, String authToken) {
        String userSsn = getSsn();

        ChakadErrorResponseDto depositRegisterResponse = chakadClient.depositRegister(getToken(),
            buildDepositRegisterRequest(depositRegisterRequestDto, userSsn));

        if (!depositRegisterResponse.isSucceeded())
            throw new ChakadClientException(depositRegisterResponse.getMessage());

        return SuccessClientResponseDto.builder().isSucceeded(true).build();
    }

    @Override
    @SneakyThrows
    public SuccessClientResponseDto depositCancel(DepositCancelRequestDto cancelRequestDto, String authToken) {

//        DepositInquiryResponseDto depositInquiryResponse = chakadClient.depositInquiry(getToken(),
//            buildDepositInquiryRequest(cancelRequestDto.getSayadId(), null));
//
//        if (!depositInquiryResponse.isSucceeded())
//            throw new ChakadClientException(depositInquiryResponse.getMessage());

        ChakadErrorResponseDto cancelResponse = chakadClient.depositCancel(getToken(),
            (DepositCancelDto) buildDepositCancelRequest(cancelRequestDto.getSayadId()));
//                depositInquiryResponse.getData().get(depositInquiryResponse.getData().size() - 1).getDepositId()));

        if (!cancelResponse.isSucceeded())
            throw new ChakadClientException(cancelResponse.getMessage());

        return SuccessClientResponseDto.builder().isSucceeded(true).build();
    }

    private DepositInquiryDto buildDepositCancelRequest(String sayadId) {
        String userSsn = getSsn();
//        if (depositId == null) {
//            DepositInquiryDto req = new DepositInquiryDto();
//            req.setSayadId(sayadId);
//            req.setHolderIdentifier(userSsn);
//            req.setHolderIdentifierType(setUserType(userSsn));
//            return req;
//        }
        DepositCancelDto cancelReq = new DepositCancelDto();
//        cancelReq.setDepositId(depositId);
        cancelReq.setSayadId(sayadId);
        cancelReq.setHolderIdentifier(userSsn);
        cancelReq.setHolderIdentifierType(setUserType(userSsn));
        return cancelReq;

    }

    private DepositRegisterDto buildDepositRegisterRequest(DepositRegisterRequestDto depositRegisterRequestDto, String userSsn) {
        DepositRegisterDto req = mapper.toDepositRegister(depositRegisterRequestDto);
        req.setMediaType(DIGITAL_MEDIA_TYPE);
        req.setHolderIdentifier(userSsn);
        req.setHolderIdentifierType(setUserType(userSsn));
        return req;
    }

    private ChequeReason checkReasonValidation(String reason) {
        if (reason != null && !reason.isEmpty()) {
            return reasonsRepository.findByReasonCode(reason)
                .orElseThrow(() -> new ChakadClientException("reason.not.find", Status.BAD_REQUEST));
        }
        return null;
    }

    private void insertStakeholderTransfer(TransferRequestDto transferRequestDto, ChequeTransfer savedTransfer) {
        if (transferRequestDto.getReceivers() != null)
            transferRequestDto.getReceivers().forEach(receiver ->
                stakeholderService.createStakeholder(StakeholderDto.builder()
                    .sayadId(savedTransfer.getSayadId())
                    .name(receiver.getName())
                    .identifier(receiver.getIdentifier())
                    .identifierType(setUserType(receiver.getIdentifier()).toString())
                    .role(StakeholderRole.RECEIVER)
                    .chequeIssue(savedTransfer.getChequeIssue())
                    .chequeTransfer(savedTransfer)
                    .build()
                ));

        transferRequestDto.getSigners().forEach(signer ->
            stakeholderService.createStakeholder(StakeholderDto.builder()
                .sayadId(savedTransfer.getSayadId())
                .name(signer.getName())
                .identifier(signer.getIdentifier())
                .identifierType(setUserType(signer.getIdentifier()).toString())
                .role(StakeholderRole.TRANSFER)
                .chequeIssue(savedTransfer.getChequeIssue())
                .chequeTransfer(savedTransfer)
                .agent(AGENT_VALUE)
                .build()
            ));
    }

    private TransferDto buildTransferRequest(TransferRequestDto transferRequestDto) {
        String userSsn = getSsn();

        TransferDto req = mapper.toTransferChequeReq(transferRequestDto);
        req.setIdentifier(userSsn);
        req.setIdentifierType(setUserType(userSsn));

        req.getSigners().forEach(signer -> {
            signer.setIdentifierType(setUserType(signer.getIdentifier()));
            signer.setLegalStamp(legalStamp);
            signer.setAgent(AGENT_VALUE);
        });

        if (req.getReceivers() != null)
            req.getReceivers().forEach(receiver ->
                receiver.setIdentifierType(setUserType(receiver.getIdentifier())));

        return req;
    }

    private AcceptChequeRequestDto buildAcceptRequest(AcceptanceRequestDto requestDto, String sayadId, Integer statusCode) {
        String userSsn = getSsn();

        AcceptChequeRequestDto req = new AcceptChequeRequestDto();
        req.setSayadId(sayadId);
        req.setAccept(statusCode);
        req.setDescription(requestDto.getDescription());
        req.setIdentifier(userSsn);
        req.setIdentifierType(setUserType(userSsn));

        return req;
    }


    private List<ChequeCartableDto> cartableMapToResponseDto(List<CartableDto> cheques) {
        List<ChequeCartableDto> res = new ArrayList<>();
        cheques.forEach(cartableDto -> {
            ChequeCartableDto dto = mapper.toCartableResDto(cartableDto);
            dto.setLocalizedChequeType(dto.getChequeType().getDescription());
            dto.setLocalizedChequeStatus(dto.getChequeStatus().getDescription());
            dto.setLocalizedGuaranteeStatus(dto.getGuaranteeStatus().getDescription());
            dto.setDueDateUtc(convertCurrentToUTC());

            try {
                ChequeReason chequeReason = checkReasonValidation(cartableDto.getReason());
                dto.setLocalizedReason(chequeReason != null ? chequeReason.getReasonTitle() : null);
            } catch (Exception e) {
                dto.setLocalizedReason(null);
            }

//            dto.getChequeOwner().forEach(owner -> {
//                owner.setIdType(CustomerIdType.getByValue(setUserType(owner.getIdCode())));
//            });

            if (cartableDto.getChequeStatus().equals(CartableChequeStatus.REGISTERED.getCode())
                && cartableDto.getLocked().equals(1)) {
                dto.setChequeStatus(CartableChequeStatus.CHECKOUT_PROCESSING);
                dto.setLocalizedChequeStatus(CartableChequeStatus.CHECKOUT_PROCESSING.getDescription());
            }

            res.add(dto);
        });

        return res;
    }

    private IssueChequeRequestDto createIssueClient(ChequeIssueRequestDto issueReq, IssueResponseDto savedCheque,
                                                    List<StakeholderResponseDto> savedStakeholders) {
        String userSsn = getSsn();

        List<StakeholderResponseDto> savedReceivers = savedStakeholders.stream().filter(s -> s.getRole().equals(StakeholderRole.RECEIVER))
            .collect(Collectors.toList());
        List<StakeholderResponseDto> savedSigners = savedStakeholders.stream().filter(s -> s.getRole().equals(StakeholderRole.ISSUER))
            .collect(Collectors.toList());
        List<StakeholderResponseDto> savedGuarantors = savedStakeholders.stream().filter(s -> s.getRole().equals(StakeholderRole.GRANTOR))
            .collect(Collectors.toList());

        ChequeDto cheque = new ChequeDto();
        cheque.setAmount(Long.valueOf(savedCheque.getAmount()));
        cheque.setSayadId(issueReq.getSayadId());
        cheque.setDescription(savedCheque.getDescription());
        cheque.setSerialNumber(savedCheque.getSerialNumber());
        cheque.setSeriNumber(savedCheque.getSeriNumber());
        cheque.setSettlementDate(savedCheque.getSettlementDate());

        List<GuarantorDto> grantors = new ArrayList<>();
        for (StakeholderResponseDto grantor : savedGuarantors) {
            GuarantorDto g = new GuarantorDto();
            g.setName(grantor.getName());
            g.setIdentifier(grantor.getIdentifier());
            g.setIdentifierType(grantor.getIdentifierType() != null ? Integer.valueOf(grantor.getIdentifierType()) : null);
            grantors.add(g);
        }

        List<ReceiverDto> receivers = new ArrayList<>();
        for (StakeholderResponseDto receiver : savedReceivers) {
            ReceiverDto rec = new ReceiverDto();
            rec.setName(receiver.getName());
            rec.setIdentifier(receiver.getIdentifier());
            rec.setIdentifierType(receiver.getIdentifierType() != null ? Integer.valueOf(receiver.getIdentifierType()) : null);
            receivers.add(rec);
        }

        List<SignerDto> signers = new ArrayList<>();
        for (StakeholderResponseDto signer : savedSigners) {
            SignerDto si = new SignerDto();
            si.setName(signer.getName());
            si.setIdentifier(signer.getIdentifier());
            si.setIdentifierType(Integer.valueOf(signer.getIdentifierType()));
            si.setGrantorName(signer.getGrantorName());
            si.setGrantorIdentifier(signer.getGrantorIdentifier());
            si.setGrantorType(signer.getGrantorType() != null ? Integer.valueOf(signer.getGrantorType()) : null);
            si.setAgent(signer.getAgent());
            si.setLegalStamp(legalStamp);
            signers.add(si);
        }

        cheque.setGuarantors(grantors);
        cheque.setReceivers(receivers);
        cheque.setSigners(signers);

        Sign sign = new Sign();
        sign.setCms(issueReq.getCms());
        sign.setSigningStatus(1);
        sign.setTokenType(TokenType.NAMAD.getValue());
        sign.setLegalStamp(Integer.parseInt(legalStamp));
        sign.setSignerIdCode(userSsn);
        sign.setSignerIdType(setUserType(userSsn));
        sign.setSignerOrganizationIdCode("");
        sign.setSignerOrganizationIdType(0);
        sign.setSignDate(DateConvertor.ConvertCurrentToJalaliWithTime());

        IssueChequeRequestDto issue = new IssueChequeRequestDto();
        issue.setCheque(cheque);
        issue.setSign(sign);

        return issue;
    }

    private void insertOrUpdateReceiversInfo(List<UserDto> receivers, List<ActivationStatusDto> inquiryResult) {
        inquiryResult.forEach(inquiredReceiver -> {
            String[] nameParts = null;
            for (UserDto receiverUser : receivers) {
                if (receiverUser.getIdentifier().equals(inquiredReceiver.getIdCode()))
                    if (receiverUser.getName() != null && !receiverUser.getName().trim().isEmpty())
                        nameParts = receiverUser.getName().split(" ");
            }

            assert nameParts != null;
            userInfoService.createOrUpdateProfileRequestUser(UserInfoDto.builder()
                .userId(inquiredReceiver.getIdCode())
                .shahabId(inquiredReceiver.getShahabId())
                .firstName(nameParts[0])
                .lastName(nameParts[nameParts.length - 1])
                .build());

        });
    }

    private String buildTbs(IssueRequestReqDto issueRequestReq, List<ActivationStatusDto> activationStatus, String settlementDate) {
        StringBuilder customersTbs = new StringBuilder();
        activationStatus.forEach(customer ->
            customersTbs.append(customer.getIdCode()).append("_").append(customer.getIdType()).append("_").append(customer.getShahabId()).append("|"));

        return issueRequestReq.getSayadId() + "|"
            + settlementDate + "|"
            + issueRequestReq.getAmount() + "|"
            + Base64.getEncoder().encodeToString(issueRequestReq.getDescription().getBytes()) + "|"
            + customersTbs
            + "" /*toIban*/ + "|"
            + issueRequestReq.getReason();
    }

    private void insertStakeholderIssuer(UserInfoResponseDto currentUserInfo, IssueRequestReqDto issueRequestReq, ChequeIssue savedIssue) {
        stakeholderService.createStakeholder(StakeholderDto.builder()
            .agent(AGENT_VALUE)
            .certificationKeyId(currentUserInfo.getCertificateKeyId())
            .role(StakeholderRole.ISSUER)
            .name((currentUserInfo.getFirstName()) + " " + currentUserInfo.getLastName())
            .identifier(currentUserInfo.getUserId())
            .identifierType(currentUserInfo.getIdType().getValue().toString())
            .shahabId(currentUserInfo.getShahabId())
            .sayadId(issueRequestReq.getSayadId())
            .grantorIdentifier(issueRequestReq.getGuarantors() == null ? null : issueRequestReq.getGuarantors().get(0).getIdentifier())
            .grantorName(issueRequestReq.getGuarantors() == null ? null : issueRequestReq.getGuarantors().get(0).getName())
            .chequeIssue(savedIssue)
            .build());
    }

    private void insertStakeholderReceiver(List<UserDto> receivers, ChequeIssue savedIssue) {
        receivers.forEach(receiver -> {
                Optional<ChequeUserInfo> receiverInfo = userInfoService.getByUserIdOptional(receiver.getIdentifier(),
                    setUserType(receiver.getIdentifier()).equals(2));

                stakeholderService.createStakeholder(StakeholderDto.builder()
                    .role(StakeholderRole.RECEIVER)
                    .name(receiver.getName())
                    .certificationKeyId(receiverInfo.map(ChequeUserInfo::getCertificateKeyId).orElse(null))
                    .identifier(receiver.getIdentifier())
                    .identifierType(receiverInfo.map(rec -> rec.getIdType().getValue().toString()).orElse(null))
                    .shahabId(receiverInfo.map(ChequeUserInfo::getShahabId).orElse(null))
                    .sayadId(savedIssue.getSayadId())
                    .chequeIssue(savedIssue)
                    .build());
            }
        );
    }

    private ChequeIssue insertIssue(IssueRequestReqDto issueRequestReq, String tbs, ChequeReason existedReason, String settlementDate) {
        return chequeIssueService.createIssue(IssueDto.builder()
            .sayadId(issueRequestReq.getSayadId())
            .serialNumber(issueRequestReq.getSerialNumber())
            .seriNumber(issueRequestReq.getSeriNumber())
            .amount(issueRequestReq.getAmount())
            .description(issueRequestReq.getDescription())
            .settlementDate(settlementDate)
            .tbs(tbs)
            .chequeReason(existedReason)
            .build());
    }
}
