package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.BouncedChequeResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.BouncedChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.BouncedChequeInquiryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BouncedChequeInquiryMapper {
    @Mapping(source = "ssn", target = "customerInfo.nationalIdentifier")
    @Mapping(constant = "1", target = "customerInfo.customerType") // set static for real customer
    @Mapping(expression = "java(new  java.util.Date().getTime()+ssn )", target = "requestIdentifier")
    BouncedChequeRequestDto mapToRequest(String ssn);

    BouncedChequeResponseDto mapToResponse(BouncedChequeInquiryResponseDto bouncedChequeInquiryResponseDto);
}
