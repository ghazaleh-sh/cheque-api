package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.ChakadActivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadDeactivationRequestDto;
import ir.co.sadad.cheque.domain.dto.ChakadInquiryStatusResponseDto;
import ir.co.sadad.cheque.domain.dto.CustomerDto;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ActivationRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CustomerRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.DeactivationRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.InquiryStatusResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ChakadMapper {

    @Mapping(constant = "1", target = "customerActivation.tokenType")
    @Mapping(constant = "1", target = "sign.tokenType")
    @Mapping(source = "customerActivation.requestType", target = "customerActivation.requestType")
    @Mapping(source = "legalStamp", target = "customerActivation.legalStamp")
    @Mapping(source = "customerActivation.mobileNumber", target = "customerActivation.mobileNumber")
    @Mapping(source = "customerActivation.simlessIdentifier", target = "customerActivation.simlessIdentifier")
    @Mapping(source = "idCodeCustomer", target = "customerActivation.customer.idCode")
    @Mapping(source = "idTypeCustomer", target = "customerActivation.customer.idType", qualifiedByName = "idTypeConvert")
    ActivationRequestDto mapToActivationRequest(ChakadActivationRequestDto baseRequestDto);

    @Mapping(constant = "52", target = "tokenType")
    @Mapping(source = "idCodeCustomer", target = "customer.idCode")
    @Mapping(source = "idTypeCustomer", target = "customer.idType", qualifiedByName = "idTypeConvert")
    DeactivationRequestDto mapToDeactivationRequest(ChakadDeactivationRequestDto deactiveRequestDto);

    @Named("idTypeConvert")
    default Integer idTypeConverter(String idTypeCustomer) {
        return CustomerIdType.valueOf(idTypeCustomer).getValue();
    }

    @IterableMapping(qualifiedByName = "ListMapper")
    List<CustomerRequestDto> toCaptureListItems(List<CustomerDto> listCustomer);

    @Named("ListMapper")
    default CustomerRequestDto map(CustomerDto customer) {
        if (customer.getIdCode() != null && customer.getIdType() != null) {
            return this.to(customer);
        }

        return null;
    }

    default CustomerRequestDto to(CustomerDto customer) {
        CustomerRequestDto finalReq = new CustomerRequestDto();
        finalReq.setIdCode(customer.getIdCode());
        finalReq.setIdType(CustomerIdType.valueOf(customer.getIdType()).getValue());
        return finalReq;
    }

    ChakadInquiryStatusResponseDto toInquiryResponse(InquiryStatusResponseDto response);
}
