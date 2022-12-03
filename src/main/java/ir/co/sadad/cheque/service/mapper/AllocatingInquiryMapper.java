package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.AllocatingInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.AllocatingEstelamRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AllocatingInquiryMapper {
    @Mapping(
        expression = "java(ir.co.sadad.cheque.utils.DateConvertor.convertEpochToJalali(allocatingInquiryRequestDto.getCreateDateFrom()))",
        target = "createDateFrom"
    )
    @Mapping(
        expression = "java(ir.co.sadad.cheque.utils.DateConvertor.convertEpochToJalali(allocatingInquiryRequestDto.getCreateDateTo()))",
        target = "createDateTo"
    )
    @Mapping(
        expression = "java(ir.co.sadad.cheque.utils.DateConvertor.convertEpochToJalali(allocatingInquiryRequestDto.getSettlementDateFrom()))",
        target = "settlementDateFrom"
    )
    @Mapping(
        expression = "java(ir.co.sadad.cheque.utils.DateConvertor.convertEpochToJalali(allocatingInquiryRequestDto.getSettlementDateTo()))",
        target = "settlementDateTo"
    )
    AllocatingEstelamRequestDto mapToRequest(AllocatingInquiryRequestDto allocatingInquiryRequestDto);
}
