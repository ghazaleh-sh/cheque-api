package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ShahabRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabSuccessResponseDto;
import ir.co.sadad.cheque.management.SsoClientTokenManager;

import javax.servlet.http.HttpServletRequest;

public abstract class ShahabService extends BaseService {

    public ShahabService(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    public abstract ShahabSuccessResponseDto inquiryRequest(ShahabRequestDto shahabRequestDto);

    public abstract ShahabSuccessResponseDto trackRequest(String ssn, String trackCode);

    public abstract ShahabSuccessResponseDto codeRequest(String ssn);
}
