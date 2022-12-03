package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.ShahabCodeRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabSuccessResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.shahab.CodeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.shahab.ShahabInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabDataResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ShahabMapper {

    @Mapping(constant = "false", target = "online")
    ShahabInquiryRequestDto mapToInquiryRequest(ShahabRequestDto shahabRequestDto);

    ShahabSuccessResponseDto mapToResponse(ShahabDataResponseDto shahabDataResponseDto);

    CodeRequestDto mapToCodeRequest(ShahabCodeRequestDto shahabCodeRequestDto);

}
