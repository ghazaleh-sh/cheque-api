package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.mapper.SayadMapper;
import ir.co.sadad.cheque.web.rest.errors.SayadClientException;
import ir.co.sadad.cheque.web.rest.errors.SayadInquiresClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.EsServiceClient;
import ir.co.sadad.cheque.web.rest.external.PichakEgwClient;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquirySheetResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.SayadReportResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@Log4j2
public class SayadServiceImpl extends SayadService {

    private final ChakadClient chakadClient;

    private final EsServiceClient esServiceClient;
    private final PichakEgwClient pichakEgwClient;
    private final SayadMapper sayadMapper;

    public SayadServiceImpl(HttpServletRequest httpServletRequest,
                            SsoClientTokenManager ssoClientTokenManager,
                            ChakadClient chakadClient,
                            EsServiceClient esServiceClient, PichakEgwClient pichakEgwClient, SayadMapper sayadMapper) {

        super(httpServletRequest, ssoClientTokenManager);
        this.chakadClient = chakadClient;
        this.esServiceClient = esServiceClient;
        this.pichakEgwClient = pichakEgwClient;
        this.sayadMapper = sayadMapper;
    }


    /**
     * {@inheritDoc}
     *
     * @param chequeReportRequest request
     * @return
     */
    @Override
    public List<SayadChequeRequestItemDto> reportIssuedCheque(SayadChequeReportRequestDto chequeReportRequest) {

        SayadReportResponseDto sayadReportResponse =
            esServiceClient.sayadReport(getToken(), sayadMapper.toRequestOfReportDto(chequeReportRequest));

        if (sayadReportResponse.getMessageCode() == SUCCESS_SAYAD_CODE)
            return sayadMapper.toResponseOfReport(sayadReportResponse.getData());
        else
            throw new SayadClientException(sayadReportResponse.getMessageCode());
    }

    /**
     * {@inheritDoc}
     *
     * @param sayadChequeRequest request for cheque
     */
    @Override
    public void requestCheque(SayadChequeRequestDto sayadChequeRequest) {

        SayadRequestResponseDto sayadRequestResponseDto =
            esServiceClient.sayadRequest(getToken(), sayadMapper.toRequestChequeRequestDto(sayadChequeRequest));

        if (sayadRequestResponseDto.getMessageCode() != SUCCESS_SAYAD_CODE) {

            throw new SayadClientException(sayadRequestResponseDto.getMessageCode());
        }
    }

    @Override
    @SneakyThrows
    public List<SayadChequeInquiryBatchResponseDto> batchInquiry(SayadChequeInquiryBatchRequestDto chequeInquiryBatchRequest) {


        ChequeInquiryBatchResponseDto response =
            pichakEgwClient.bookInquiry(getToken(),
                getUserAgent(),
                "",
                sayadMapper.toRequestDtoOfBatchService(chequeInquiryBatchRequest));

        if (response.getIsSuccess()) {
            return sayadMapper.toResponseDtoOfBatchService(response.getResult());
        }
        throw new SayadInquiresClientException(response.getErrorCodes());
    }

    @Override
    @SneakyThrows
    public List<SayadChequeInquirySheetResponseDto> sheetInquiry(SayadChequeInquirySheetRequestDto chequeInquirySheetRequest) {

        ChequeInquirySheetResponseDto response = pichakEgwClient.sheetInquiry(getToken(),
            getUserAgent(),
            "",
            sayadMapper.toRequestDtoOfSheetService(chequeInquirySheetRequest));

        if (response.getIsSuccess()) {
            return sayadMapper.toResponseDtoOfSheetService(response.getResult());
        }
        throw new SayadInquiresClientException(response.getErrorCodes());
    }
}
