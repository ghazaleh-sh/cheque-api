package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.ChakadMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ActivationRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ChallengeCodeDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.InquiryStatusRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChallengeCodeResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.InquiryStatusResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public void chakadActivation(String authToken,
                                 ChakadActivationRequestDto activationRequestDto) {


        ActivationRequestDto mapped = chakadMapper.mapToActivationRequest(activationRequestDto);
        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.activationProfile(getToken(),
//            basicAuth,
            getUserAgent(),
            mapped);

        if (!SUCCESS_CHAKAD_CODE.equals(response.getCode()))
            throw new ChakadClientException(response.getCode());
    }

    @Override
    @SneakyThrows
    public void chakadDeactivation(String authToken,
                                   ChakadDeactivationRequestDto deactiveRequestDto) {

        ChakadResponseDto<ObjectUtils.Null> response = chakadClient.deactivationProfile(getToken(),
//            basicAuth,
            getUserAgent(),
            chakadMapper.mapToDeactivationRequest(deactiveRequestDto));

        if (!SUCCESS_CHAKAD_CODE.equals(response.getCode()))
            throw new ChakadClientException(response.getCode());
    }

    @Override
    @SneakyThrows
    public List<InquiryActivationStatusDto> chakadInquiryStatus(String authToken,
                                                                ChakadStatusRequestDto chakadStatusRequestDto) {
        InquiryStatusRequestDto req = new InquiryStatusRequestDto();
        req.setCustomers(chakadMapper.toCaptureListItems(chakadStatusRequestDto.getCustomers()));

        ChakadResponseDto<InquiryStatusResponseDto> response = chakadClient.inquiryStatus(getToken(),
//            basicAuth,
            getUserAgent(),
            req);
        if (SUCCESS_CHAKAD_CODE.equals(response.getCode()))
            return chakadMapper.toInquiryResponse(response.getData().getActivationStatus());
        else
            throw new ChakadClientException(response.getCode());


    }

    @Override
    @SneakyThrows
    public ChakadChallengeCodeResDto challengeCode(String authToken,
                                                   ChakadChallengeCodeReqDto chakadChallengeCodeReqDto) {

        ChallengeCodeDto mappedRequest = chakadMapper.toChallengeRequestDto(chakadChallengeCodeReqDto);
        if (chakadChallengeCodeReqDto.getIdCodeOrganization() == null)
            mappedRequest.setOrganization(null);
        ChakadResponseDto<ChallengeCodeResponseDto> response = chakadClient.challengeCode(getToken(),
//            basicAuth,
            getUserAgent(),
            mappedRequest
        );

        //todo inja response eshtebah boode , felan injoory handel shode .
        if (!response.isSucceeded())
            throw new ChakadClientException(response.getCode());

        return chakadMapper.toChallengeResponse(response.getData());
    }

}
