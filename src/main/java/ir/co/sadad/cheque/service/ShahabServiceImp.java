package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ShahabRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabSuccessResponseDto;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.ShahabMapper;
import ir.co.sadad.cheque.web.rest.external.ShahabClient;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabDataResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j2
public class ShahabServiceImp extends ShahabService {

    private final ShahabClient shahabClient;
    private final ShahabMapper shahabMapper;

    public ShahabServiceImp(HttpServletRequest httpServletRequest,
                            SsoClientTokenManager ssoClientTokenManager,
                            ShahabClient shahabClient,
                            ShahabMapper shahabMapper) {

        super(httpServletRequest, ssoClientTokenManager);
        this.shahabClient = shahabClient;
        this.shahabMapper = shahabMapper;
    }

    @Override
    public ShahabSuccessResponseDto inquiryRequest(ShahabRequestDto shahabRequestDto) {

        ShahabResponseDto shahabResponse = shahabClient.shahabInquiry(getToken(), shahabMapper.mapToInquiryRequest(shahabRequestDto));
        if (shahabResponse.getResponse() != null) {
            return shahabMapper.mapToResponse(shahabResponse.getResponse());
        } else {
            // handles error
        }

        return null;
    }

    @Override
    public ShahabSuccessResponseDto trackRequest(String ssn, String trackCode) {
        ShahabResponseDto shahabResponse = shahabClient.shahabTrack(getToken(), ssn, trackCode);
        if (shahabResponse.getResponse() != null) {
            return shahabMapper.mapToResponse(shahabResponse.getResponse());
        } else {
            // handles error
        }
        return null;
    }

    @Override
    public ShahabSuccessResponseDto codeRequest(String ssn) {
        ShahabDataResponseDto shahabResponse = shahabClient.shahabCode(
            getToken(), ssn).getResponse();
        return shahabMapper.mapToResponseShahabCode(shahabResponse);

    }
}
