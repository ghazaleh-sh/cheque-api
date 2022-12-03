package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.SayadMapper;
import ir.co.sadad.cheque.web.rest.errors.SayadClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquirySheetResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.SayadReportResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * {@inheritDoc}
 */
@Service
@Log4j2
public class SayadServiceImpl extends SayadService {

    private final ChakadClient chakadClient;
    private final SayadMapper sayadMapper;

    public SayadServiceImpl(HttpServletRequest httpServletRequest,
                            SsoClientTokenManager ssoClientTokenManager,
                            ChakadClient chakadClient,
                            SayadMapper sayadMapper) {

        super(httpServletRequest, ssoClientTokenManager);
        this.chakadClient = chakadClient;
        this.sayadMapper = sayadMapper;
    }


    /**
     * {@inheritDoc}
     *
     * @param chequeReportRequest request
     * @return
     */
    @Override
    public SayadChequeReportResponseDto reportIssuedCheque(SayadChequeReportRequestDto chequeReportRequest) {

        SayadReportResponseDto sayadReportResponse =
            chakadClient.sayadReport(getToken(), sayadMapper.toRequestOfReportDto(chequeReportRequest));

        if (sayadReportResponse.getMessageCode() == SUCCESS_SAYAD_CODE)
            return sayadMapper.toResponseOfReport(sayadReportResponse);
        else
            throw new SayadClientException(sayadReportResponse.getMessageCode());
    }

    /**
     * {@inheritDoc}
     *
     * @param sayadChequeRequest request for cheque
     * @return
     */
    @Override
    public SayadChequeRequestResDto requestCheque(SayadChequeRequestDto sayadChequeRequest) {

        SayadRequestResponseDto sayadRequestResponseDto =
            chakadClient.sayadRequest(getToken(), sayadMapper.toRequestChequeRequestDto(sayadChequeRequest));

        if (sayadRequestResponseDto.getMessageCode() == SUCCESS_SAYAD_CODE) {
            SayadChequeRequestResDto response = new SayadChequeRequestResDto();
            response.setDone(true);
            return response;
        }
        throw new SayadClientException(sayadRequestResponseDto.getMessageCode());
    }

    @Override
    public SayadChequeInquiryBatchResponseDto batchInquiry(SayadChequeInquiryBatchRequestDto chequeInquiryBatchRequest) {
        //todo creator and userName
        ChequeInquiryBatchResponseDto response =
            chakadClient.batchInquiry(getToken(),
                1,
                "",
                sayadMapper.toRequestDtoOfBatchService(chequeInquiryBatchRequest));

        if (response.getMessageCode() == SUCCESS_SAYAD_CODE) {
            return sayadMapper.toResponseDtoOfBatchService(response);
        }
        throw new SayadClientException(response.getMessageCode());
    }

    @Override
    public SayadChequeInquirySheetResponseDto sheetInquiry(SayadChequeInquirySheetRequestDto chequeInquirySheetRequest) {
        //todo creator and userName

        ChequeInquirySheetResponseDto response = chakadClient.sheetInquiry(getToken(),
            1,
            "",
            sayadMapper.toRequestDtoOfSheetService(chequeInquirySheetRequest));

        if (response.getMessageCode() == SUCCESS_SAYAD_CODE) {
            return sayadMapper.toResponseDtoOfSheetService(response);
        }
        throw new SayadClientException(response.getMessageCode());
    }
}
