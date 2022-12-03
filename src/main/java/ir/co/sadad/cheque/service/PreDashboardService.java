package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ChakadActivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadDeactivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadInquiryStatusResponseDto;
import ir.co.sadad.cheque.domain.dto.ChakadStatusRequestDto;
import ir.co.sadad.cheque.management.SsoClientTokenManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;


public abstract class PreDashboardService extends BaseService {

    public PreDashboardService(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    public abstract ResponseEntity<HttpStatus> chakadActivation(String authToken, ChakadActivationRequestDto deactiveRequestDto);

    public abstract ResponseEntity<HttpStatus> chakadDeactivation(String authToken, ChakadDeactivationRequestDto deactiveRequestDto);

    public abstract ChakadInquiryStatusResponseDto chakadInquiryStatus(String authToken, ChakadStatusRequestDto chakadStatusRequestDto);
}
