package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public abstract class PreDashboardService extends BaseService {

    public PreDashboardService(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    public abstract void chakadActivation(String authToken, ChakadActivationRequestDto deactiveRequestDto);

    public abstract void chakadDeactivation(String authToken, ChakadDeactivationRequestDto deactiveRequestDto);

    public abstract List<InquiryActivationStatusDto> chakadInquiryStatus(String authToken, ChakadStatusRequestDto chakadStatusRequestDto);

    /**
     * service for challenge code
     *
     * @param authToken                 token
     * @param chakadChallengeCodeReqDto request
     * @return challenge code
     */
    public abstract ChakadChallengeCodeResDto challengeCode(String authToken, ChakadChallengeCodeReqDto chakadChallengeCodeReqDto);
}
