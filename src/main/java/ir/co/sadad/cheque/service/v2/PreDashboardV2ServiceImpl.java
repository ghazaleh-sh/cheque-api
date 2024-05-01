package ir.co.sadad.cheque.service.v2;


import ir.co.sadad.cheque.domain.dto.ChakadChallengeCodeResDto;
import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.domain.enums.ActivationType;
import ir.co.sadad.cheque.domain.enums.CreateSignTestDisplay;
import ir.co.sadad.cheque.domain.enums.RequestType;
import ir.co.sadad.cheque.domain.enums.TokenType;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.UserInfoService;
import ir.co.sadad.cheque.service.mapper.ChakadMapper;
import ir.co.sadad.cheque.utils.DateConvertor;
import ir.co.sadad.cheque.web.rest.errors.BaambaseClientException;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.external.BaambaseClient;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ir.co.sadad.cheque.utils.ConverterHelper.setUserType;

@Log4j2
@Service
public class PreDashboardV2ServiceImpl extends PreDashboardV2Service {
    private final ChakadClient chakadClient;
    private final BaambaseClient baambaseClient;
    private final ChakadMapper chakadMapper;
    private final UserInfoService userInfoService;

    public PreDashboardV2ServiceImpl(HttpServletRequest httpServletRequest,
                                     SsoClientTokenManager ssoClientTokenManager,
                                     ChakadClient chakadClient,
                                     BaambaseClient baambaseClient,
                                     ChakadMapper chakadMapper,
                                     UserInfoService userInfoService) {
        super(httpServletRequest, ssoClientTokenManager);
        this.chakadClient = chakadClient;
        this.baambaseClient = baambaseClient;
        this.chakadMapper = chakadMapper;
        this.userInfoService = userInfoService;
    }

    @Override
    @SneakyThrows
    public InquiryStatusFinalResDto inquiryStatus() {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        List<CustomerRequestDto> listOfReq = Collections.singletonList(getCustomerFromToken(userSsn, userType));
        ChakadResponseDto<InquiryStatusResponseDto> response = chakadClientInquiry(listOfReq);

        return chakadMapper.toInquiryV2Response(response.getData().getActivationStatus().get(0));
    }

    @Override
    public TbsActivationResponseDto tsbActivation(String authToken, TbsActivationRequestDto tbsActivationRequestDto) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        CertificationResBodyDto userCertification = userCertifications(authToken);

        ChakadChallengeCodeResDto challengeCodeRes = challengeCode(authToken,
            buildChallengeCodeRequest(userSsn, userType, tbsActivationRequestDto.getMobileNumber(), RequestType.ACTIVATION));

        String dateTime = DateConvertor.ConvertCurrentToJalaliWithTime();

        userInfoService.createActiveRequestUser(UserInfoDto.builder()
            .userId(userSsn)
            .firstName(tbsActivationRequestDto.getFirstName())
            .lastName(tbsActivationRequestDto.getLastName())
            .shahabId(tbsActivationRequestDto.getShahabId())
            .mobileNumber(0 + tbsActivationRequestDto.getMobileNumber()
                .substring(tbsActivationRequestDto.getMobileNumber().length() - 10))
            .challengeCode(challengeCodeRes.getChallengeCode())
            .activationTicket(challengeCodeRes.getActivationTicketId())
            .certificateKeyId(userCertification.getCertificateKeyId())
            .requestDateTime(dateTime)
            .build());

        String tbsTail;
        if (userType.equals(2)) { // legal
            tbsTail = "|||" + userSsn + "|" + userType + "|" + tbsActivationRequestDto.getShahabId();
        } else
            tbsTail = userSsn + "|" + userType + "|" + tbsActivationRequestDto.getShahabId() + "|||";

        String tbs = challengeCodeRes.getChallengeCode().toString() + "|"
            + TokenType.NAMAD.getValue().toString() + "|"
            + requestType + "|"
            + Long.valueOf(dateTime) + "|"
            + tbsTail;

        TbsActivationResponseDto responseDto = new TbsActivationResponseDto();
        responseDto.setCertificateKeyId(userCertification.getCertificateKeyId());
        responseDto.setDevice(userCertification.getDevice());
        responseDto.setDeviceId(userCertification.getDeviceId());
        responseDto.setDataToBeSign(Base64.getUrlEncoder().encodeToString(tbs.getBytes()));
        responseDto.setProductId(namadProductUid.toString());
        responseDto.setProductType(TokenType.NAMAD);

        return responseDto;
    }

    @Override
    @SneakyThrows
    public void tsbActivationUpdate(String authToken) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        CertificationResBodyDto userCertification = userCertifications(authToken);

        UserInfoResponseDto currentUserInfo = userInfoService.getBy(userSsn, userType.equals(2));

        ChakadChallengeCodeResDto challengeCodeRes = challengeCode(authToken,
            buildChallengeCodeRequest(userSsn, userType, currentUserInfo.getMobileNumber(), RequestType.UPDATE_PROFILE));

        userInfoService.createUpdateProfileRequestUser(UserInfoDto.builder()
            .userId(userSsn)
            .challengeCode(challengeCodeRes.getChallengeCode())
            .activationTicket(challengeCodeRes.getActivationTicketId())
            .certificateKeyId(userCertification.getCertificateKeyId())
            .build());
    }

    @Override
    @SneakyThrows
    public ChakadChallengeCodeResDto challengeCode(String authToken, ChallengeCodeDto challengeCodeReqDto) {

        ChakadResponseDto<ChallengeCodeResponseDto> response = chakadClient.challengeCode(getToken(),
            getUserAgent(),
            challengeCodeReqDto
        );

        if (!response.isSucceeded())
            throw new ChakadClientException(response.getCode());

        return chakadMapper.toChallengeResponse(response.getData());
    }

    @Override
    @SneakyThrows
    public SuccessClientResponseDto activation(String authToken, ActivationClientRequestDto activationClientRequestDto) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        UserInfoResponseDto userInfo = userInfoService.getBy(userSsn, userType.equals(2));

        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.activationProfile(getToken(),
            getUserAgent(),
            buildActivationRequest(userSsn, userType, userInfo.getMobileNumber(), userInfo.getChallengeCode(), userInfo.getRequestDateTime(),
                userInfo.getActivationTicket(), activationClientRequestDto.getCms(), ActivationType.valueOf(activationClientRequestDto.getRequestType()).getValue()));

        if (!SUCCESS_CHAKAD_CODE.equals(response.getCode()))
            throw new ChakadClientException(response.getCode());

        userInfoService.activeUserBy(userSsn, userType.equals(2));

        SuccessClientResponseDto res = SuccessClientResponseDto.builder()
            .isSucceeded(true).build();

        if (!coreSignRequest)
            return res;

        try {
            //----------------------createSignature service
            BaamBaseResponseDto<CreateSignatureResBodyDto> createSignatureRes = baambaseClient.createSignature(authToken,
                buildCreateSignatureRequest(CreateSignTestDisplay.CHAKAD_ACTIVATION, userSsn, activationClientRequestDto.getCertificateKeyID(),
                    activationClientRequestDto.getDataToBeSign()));

            if (createSignatureRes.getResultCode() != 200)
                throw new BaambaseClientException(createSignatureRes.getResultCode().toString());

            //----------------------SignedData service
            BaamBaseResponseDto<SignedDataResBodyDto> signedDataResponse = baambaseClient.signedData(authToken,
                buildSignedDataRequest(createSignatureRes.getResponseBody().getTransactionId(), activationClientRequestDto.getSignatureValue()));

            if (signedDataResponse.getResultCode() != 200)
                throw new BaambaseClientException(signedDataResponse.getResultCode().toString());

            userInfoService.addTransactionId(userSsn, signedDataResponse.getResponseBody().getTransactionId(), userType.equals(2));

//            if (!signedDataResponse.getResponseBody().getVerifyStatus().equals("OK"))
//                throw new BaambaseClientException(signedDataResponse.getResponseBody().getDescription(), Status.BAD_REQUEST);

        } catch (Exception ex) {
            //TODO: save service's log into DB
            log.error("exception in coreSign services------->" + ex.getMessage());
            return res;
        }

        return res;
    }

    @Override
    @SneakyThrows
    public DeactivationResponseDto deactivation(String authToken) {
        String userSsn = getSsn();
        Integer userType = setUserType(userSsn);

        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.deactivationProfile(getToken(),
            getUserAgent(),
            chakadMapper.toDeactivationRequest(getCustomerFromToken(userSsn, userType)));

        if (!SUCCESS_CHAKAD_CODE.equals(response.getCode()))
            throw new ChakadClientException(response.getCode());

        UserInfoResponseDto savedUser = userInfoService.deactivateUserBy(userSsn, userType.equals(2));

        try {
            baambaseClient.userDeactivation(authToken, savedUser.getCertificateKeyId());
        } catch (Exception e) {
            return DeactivationResponseDto.builder()
                .certificateKeyId(savedUser.getCertificateKeyId()).build();
        }

        return DeactivationResponseDto.builder()
            .certificateKeyId(savedUser.getCertificateKeyId()).build();
    }

    @Override
    @SneakyThrows
    public CertificationResBodyDto userCertifications(String authToken) {
        BaamBaseResponseDto<List<CertificationResBodyDto>> response = baambaseClient.userCertification(authToken);

        if (response.getResultCode() != 200)
            throw new ChakadClientException(response.getResultCode().toString());

        List<CertificationResBodyDto> filteredCert = response.getResponseBody().stream()
            .filter(deviceCertification -> deviceCertification.getProductUid().equals(namadProductUid))// || deviceCertification.getProductUid().equals(5))
            .filter(namadCertification -> namadCertification.getStatus().equals("OK"))
            .collect(Collectors.toList());

        if (filteredCert.isEmpty())
            throw new BaambaseClientException(INVALID_CERTIFICATION);

//        filteredCert = filteredCert.stream().filter(certificationDto -> certificationDto.getDeviceId().equals(userDeviceId))
//            .collect(Collectors.toList());
//
//        if (filteredCert.isEmpty())
//            throw new BaambaseClientException(VALID_CERTIFICATION_OTHER_DEVICE);

        return filteredCert.get(filteredCert.size() - 1);

    }

    @SneakyThrows
    public ChakadResponseDto<InquiryStatusResponseDto> chakadClientInquiry(List<CustomerRequestDto> listOfReq) {
        InquiryStatusRequestDto req = new InquiryStatusRequestDto();
        req.setCustomers(listOfReq);

        ChakadResponseDto<InquiryStatusResponseDto> response = chakadClient.inquiryStatus(getToken(),
            getUserAgent(),
            req);

        if (!response.isSucceeded())
            throw new ChakadClientException(response.getCode() != null ? response.getCode() : response.getMessage());

        return response;
    }

    private ChallengeCodeDto buildChallengeCodeRequest(String userSsn, Integer userType, String mobileNumber, RequestType requestType) {

        ChallengeCodeDto challengeCodeReq = new ChallengeCodeDto();
        challengeCodeReq.setRequestType(requestType.getCode());
        challengeCodeReq.setTokenType(TokenType.NAMAD.getValue());
        challengeCodeReq.setLegalStamp(Integer.parseInt(legalStamp));
        challengeCodeReq.setMobileNumber(mobileNumber);
        challengeCodeReq.setSimlessIdentifier("");
        if (userType.equals(2)) {
            OrganizationDto legalUser = new OrganizationDto();
            legalUser.setIdCode(userSsn);
            legalUser.setIdType(userType);

            challengeCodeReq.setOrganization(legalUser);

        } else challengeCodeReq.setCustomer(getCustomerFromToken(userSsn, userType));

        return challengeCodeReq;
    }

    private ActivationRequestDto buildActivationRequest(String userSsn, Integer userType, String mobileNumber, Integer challengeCode,
                                                        String requestDateTime, String ticketId, String cms, Integer requestType) {

        CustomerActivation customer = new CustomerActivation();
        customer.setRequestType(requestType); //1 activation  2 update
        customer.setActivationTicketId("");
        customer.setLegalStamp(Integer.parseInt(legalStamp));
        customer.setTokenType(TokenType.NAMAD.getValue());
        customer.setMobileNumber(mobileNumber);
        customer.setSimlessIdentifier("string");
        customer.setChallengeCode(challengeCode);
        customer.setActivationTicketId(ticketId);
        customer.setRequestDateTime(requestDateTime);

        Sign sign = new Sign();
        sign.setCms(cms);
        sign.setSigningStatus(1);
        sign.setTokenType(TokenType.NAMAD.getValue());
        sign.setLegalStamp(Integer.parseInt(legalStamp));
//        sign.setSignDate(DateConvertor.ConvertCurrentToJalali());

        if (userType.equals(2)) {
            OrganizationDto legalUser = new OrganizationDto();
            legalUser.setIdCode(userSsn);
            legalUser.setIdType(userType);

            customer.setOrganization(legalUser);
            sign.setSignerOrganizationIdType(userType);
            sign.setSignerOrganizationIdCode(userSsn);

        } else {
            customer.setCustomer(getCustomerFromToken(userSsn, userType));
            sign.setSignerIdType(userType);
            sign.setSignerIdCode(userSsn);
            sign.setSignerOrganizationIdCode("string");
            sign.setSignerOrganizationIdType(0);
        }

        ActivationRequestDto requestDto = new ActivationRequestDto();
        requestDto.setCustomerActivation(customer);
        requestDto.setSign(sign);

        return requestDto;
    }

    private CreateSignatureRequestDto buildCreateSignatureRequest(CreateSignTestDisplay enumType, String userSsn, String certificateKeyID, String dataToBeSign) {
        DataToBeSignedDto data = DataToBeSignedDto.builder()
            .dataType("RAW_DATA")
            .data(dataToBeSign)
            .name(enumType.toString())
            .signatureImageParameter(null)
            .isEncrypted(false)
            .build();

        SignatureCertificateDto signCrf = SignatureCertificateDto.builder()
            .productUid(namadProductUid)
            .certificateKeyId(certificateKeyID)
            .build();

        CreateSignatureRequestDto req = new CreateSignatureRequestDto();
        req.setUsername(userSsn);
        req.setDataToBeDisplayed(DataToBeDisplayedDto.builder().textToBeDisplayed(enumType.getDescription()).build());
        req.setDataToBeSigned(data);
        req.setSignatureCertificate(signCrf);
        req.setDigestAlgorithm("SHA256");
        req.setSignMechanism("RSA_PKCS");
        req.setCallBackUrl(null);
        req.setCallBackRejectPath(null);

        return req;
    }


    private SignedDataRequestDto buildSignedDataRequest(String transactionId, String signatureValue) {
        SignedDataRequestDto req = new SignedDataRequestDto();
        req.setTransactionId(transactionId);
        req.setSignatureValue(signatureValue);
        return req;
    }

}
