package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ChequeInquiryBatchRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ChequeInquirySheetRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.SayadReportRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.SayadRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquirySheetResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.SayadReportResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SayadMapper {

    /**
     * mapper for map request of our controller to dto for request of sayad service
     * <pre>
     *     for report issued cheque
     * </pre>
     *
     * @param sayadChequeReportRequest request of our controller
     * @return request fot sayd service
     */
    SayadReportRequestDto toRequestOfReportDto(SayadChequeReportRequestDto sayadChequeReportRequest);

    /**
     * mapp response of sayad service to response of our controller
     *
     * @param sayadReportResponse response of sayad service
     * @return response of our controller
     */
    SayadChequeReportResponseDto toResponseOfReport(SayadReportResponseDto sayadReportResponse);


    /**
     * map for make request body of RequestCheque service
     *
     * @param sayadChequeRequest request in out domain
     * @return request body of RequestCheque
     */
    SayadRequestDto toRequestChequeRequestDto(SayadChequeRequestDto sayadChequeRequest);


    /**
     * map request of our service to request od batch service
     *
     * @param chequeInquiryBatchRequest request of our servcie
     * @return request of sayad service
     */
    ChequeInquiryBatchRequestDto toRequestDtoOfBatchService(SayadChequeInquiryBatchRequestDto chequeInquiryBatchRequest);

    /**
     * map request of our service to request of sheet service
     *
     * @param chequeInquirySheetRequest our request
     * @return request of sayad service
     */
    ChequeInquirySheetRequestDto toRequestDtoOfSheetService(SayadChequeInquirySheetRequestDto chequeInquirySheetRequest);

    /**
     * map response of sayad service to our service
     *
     * @param chequeInquiryBatchResponse response of sayad servie
     * @return our service
     */
    SayadChequeInquiryBatchResponseDto toResponseDtoOfBatchService(ChequeInquiryBatchResponseDto chequeInquiryBatchResponse);

    /**
     * map response of sayad service to our service
     *
     * @param chequeInquirySheetResponse response of sheet service of sayad
     * @return our service
     */
    SayadChequeInquirySheetResponseDto toResponseDtoOfSheetService(ChequeInquirySheetResponseDto chequeInquirySheetResponse);


}
