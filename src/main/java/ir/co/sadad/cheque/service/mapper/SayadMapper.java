package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.dto.v2.DepositRegisterRequestDto;
import ir.co.sadad.cheque.domain.dto.v2.PichakLeafInquiryResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchDataResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquirySheetDataResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.RequestItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
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
    List<SayadChequeRequestItemDto> toResponseOfReport(List<RequestItemDto> sayadReportResponse);


    /**
     * map for make request body of RequestCheque service
     *
     * @param sayadChequeRequest request in out domain
     * @return request body of RequestCheque
     */
    SayadRequestDto toRequestChequeRequestDto(SayadChequeRequestDto sayadChequeRequest);


    ChequeInquiryBatchRequestDto toRequestDtoOfBatchService(SayadChequeInquiryBatchRequestDto chequeInquiryBatchRequest);


    @Mapping(source = "sayadChequeId", target = "sayadId")
    ChequeInquirySheetRequestDto toRequestDtoOfSheetService(SayadChequeInquirySheetRequestDto chequeInquirySheetRequest);

    /**
     * map response of sayad service to our service
     *
     * @param chequeInquiryBatchResponse response of sayad servie
     * @return our service
     */
    List<SayadChequeInquiryBatchResponseDto> toResponseDtoOfBatchService(List<ChequeInquiryBatchDataResponseDto> chequeInquiryBatchResponse);

    /**
     * mapper for single item of Batch inquiry
     * <pre>
     *     use for map  in list mode
     * </pre>
     *
     * @param chequeInquiryBatchDataResponse input
     * @return response
     */

    @Mapping(source = "accountType", target = "accountType", ignore = true)
    @Mapping(source = "chequeType", target = "chequeType", ignore = true)
    @Mapping(source = "chequeNumbers", target = "chequeNumbers", ignore = true)
    SayadChequeInquiryBatchResponseDto toSingleBatchResponse(ChequeInquiryBatchDataResponseDto chequeInquiryBatchDataResponse);

    /**
     * map response of sayad service to our service
     *
     * @param chequeInquirySheetResponse response of sheet service of sayad
     * @return our service
     */
    List<SayadChequeInquirySheetResponseDto> toResponseDtoOfSheetService(List<ChequeInquirySheetDataResponseDto> chequeInquirySheetResponse);

//    @Mapping(source = "sayadId", target = "sayadChequeId")
    SayadChequeInquirySheetResponseDto toSingleSheetResponse(ChequeInquirySheetDataResponseDto chequeInquirySheetDataResponse);

    @Mapping(source = "clearType", target = "clearCode")
    @Mapping(source = "chequeType", target = "chequeType", ignore = true)
    @Mapping(source = "businessType", target = "businessType", ignore = true)
    @Mapping(source = "clearType", target = "clearType", ignore = true)
    PichakLeafInquiryResponseDto toSingleLeafResponse(ChequeInquirySheetDataResponseDto chequeInquirySheetDataResponse);

}
