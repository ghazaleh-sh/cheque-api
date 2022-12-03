package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ChakadActivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadDeactivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadInquiryStatusResponseDto;
import ir.co.sadad.cheque.domain.dto.ChakadStatusRequestDto;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.ChakadMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.InquiryStatusRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.InquiryStatusResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Service
public class PreDashboardServiceImp extends PreDashboardService {

    private final ChakadClient chakadClient;
    private final ChakadMapper chakadMapper;

    public PreDashboardServiceImp(HttpServletRequest httpServletRequest,
                                  SsoClientTokenManager ssoClientTokenManager,
                                  ChakadClient chakadClient,
                                  ChakadMapper chakadMapper) {
        super(httpServletRequest, ssoClientTokenManager);
        this.chakadClient = chakadClient;
        this.chakadMapper = chakadMapper;
    }

    @Override
    @SneakyThrows
    public ResponseEntity<HttpStatus> chakadActivation(String authToken, ChakadActivationRequestDto activationRequestDto) {
        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.activationProfile(getToken(),
            getUserAgent(),
            chakadMapper.mapToActivationRequest(activationRequestDto));

        if (SUCCESS_CHAKAD_CODE.equals(response.getMassageCode()))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            throw new ChakadClientException(response.getMessage());
    }

    @Override
    @SneakyThrows
    public ResponseEntity<HttpStatus> chakadDeactivation(String authToken, ChakadDeactivationRequestDto deactiveRequestDto) {

        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.deactivationProfile(getToken(),
            getUserAgent(),
            chakadMapper.mapToDeactivationRequest(deactiveRequestDto));

        if (SUCCESS_CHAKAD_CODE.equals(response.getMassageCode()))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            throw new ChakadClientException(response.getMessage());

    }

    @Override
    @SneakyThrows
    public ChakadInquiryStatusResponseDto chakadInquiryStatus(String authToken, ChakadStatusRequestDto chakadStatusRequestDto) {
        InquiryStatusRequestDto req = new InquiryStatusRequestDto();
        req.setCustomers(chakadMapper.toCaptureListItems(chakadStatusRequestDto.getCustomers()));

        ChakadResponseDto<InquiryStatusResponseDto> response = chakadClient.inquiryStatus(getToken(),
            getUserAgent(),
            req);
        if (SUCCESS_CHAKAD_CODE.equals(response.getMassageCode()))
            return chakadMapper.toInquiryResponse(response.getData());
        else
            throw new ChakadClientException(response.getMessage());


    }

}
