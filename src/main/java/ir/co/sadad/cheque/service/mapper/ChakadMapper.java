package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.dto.v2.InquiryStatusFinalResDto;
import ir.co.sadad.cheque.domain.enums.ActivationResponseStatus;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ActivationRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.ChallengeCodeDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CustomerRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.DeactivationRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ActivationStatusDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChallengeCodeResponseDto;
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
    @Mapping(source = "customerActivation.challengeCode", target = "customerActivation.challengeCode")
    @Mapping(source = "customerActivation.requestDateTime", target = "customerActivation.requestDateTime")
    @Mapping(source = "customerActivation.activationTicketId", target = "customerActivation.activationTicketId")
    @Mapping(source = "idCodeCustomer", target = "customerActivation.customer.idCode")
    @Mapping(source = "idTypeCustomer", target = "customerActivation.customer.idType", qualifiedByName = "idTypeConvert")
    ActivationRequestDto mapToActivationRequest(ChakadActivationRequestDto baseRequestDto);

    @Mapping(constant = "1", target = "tokenType")
    @Mapping(source = "idCodeCustomer", target = "customer.idCode")
    @Mapping(source = "idTypeCustomer", target = "customer.idType", qualifiedByName = "idTypeConvert")
    DeactivationRequestDto mapToDeactivationRequest(ChakadDeactivationRequestDto deactiveRequestDto);

    @Named("idTypeConvert")
    default Integer idTypeConverter(String idTypeCustomer) {
        return CustomerIdType.valueOf(idTypeCustomer).getValue();
    }


    @Named("statusConvert")
    default ActivationResponseStatus activeStatusConverter(Integer status) {
        return ActivationResponseStatus.fromInteger(status);
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

    List<InquiryActivationStatusDto> toInquiryResponse(List<ActivationStatusDto> response);

    @Mapping(source = "idCodeCustomer", target = "customer.idCode")
    @Mapping(source = "idTypeCustomer", target = "customer.idType", qualifiedByName = "idTypeConvert")
    @Mapping(source = "idCodeOrganization", target = "organization.idCode",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "idCodeOrganization", target = "organization.idType",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(constant = "1", target = "tokenType")
    ChallengeCodeDto toChallengeRequestDto(ChakadChallengeCodeReqDto req);


    ChakadChallengeCodeResDto toChallengeResponse(ChallengeCodeResponseDto res);

    @Mapping(source = "idCode", target = "userId")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusConvert")
    InquiryStatusFinalResDto toInquiryV2Response(ActivationStatusDto response);

    @Mapping(source = "idCode", target = "customer.idCode")
    @Mapping(source = "idType", target = "customer.idType")
    @Mapping(constant = "1", target = "tokenType")
    @Mapping(constant = "0", target = "legalStamp")
    DeactivationRequestDto toDeactivationRequest(CustomerRequestDto customerRequestDto);
}
