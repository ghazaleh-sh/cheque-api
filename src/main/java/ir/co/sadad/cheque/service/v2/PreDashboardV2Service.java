package ir.co.sadad.cheque.service.v2;

import ir.co.sadad.cheque.domain.dto.ChakadChallengeCodeResDto;
import ir.co.sadad.cheque.domain.dto.ChakadDeactivationRequestDto;
import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.BaseService;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ChallengeCodeDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CustomerRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CertificationResBodyDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.InquiryStatusResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class PreDashboardV2Service extends BaseService {

    public PreDashboardV2Service(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    public abstract InquiryStatusFinalResDto inquiryStatus();

    /**
     * <pre>
     *     firstly, call userCertification service to identify whether user has an active NAMAD sign
     *     then, considering successful sign, call challengeCodeRes chakad service
     *     tbs(dataToBeSign)-base64 ultimately will be created
     * </pre>
     *
     * @param authToken               token of user
     * @param tbsActivationRequestDto request for activation
     * @return response of activation that contains cif
     */
    public abstract TbsActivationResponseDto tsbActivation(String authToken, TbsActivationRequestDto tbsActivationRequestDto);

    public abstract void tsbActivationUpdate(String authToken);

    public abstract CertificationResBodyDto userCertifications(String authToken);

    public abstract ChakadResponseDto<InquiryStatusResponseDto> chakadClientInquiry(List<CustomerRequestDto> listOfReq);

    public abstract ChakadChallengeCodeResDto challengeCode(String authToken, ChallengeCodeDto challengeCodeReqDto);

    public abstract SuccessClientResponseDto activation(String authToken, ActivationClientRequestDto activationClientRequestDto);

    public abstract SuccessClientResponseDto deactivation(String authToken);
}
