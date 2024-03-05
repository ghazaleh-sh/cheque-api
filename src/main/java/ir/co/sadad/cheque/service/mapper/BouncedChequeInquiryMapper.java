package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.BouncedChequeResponseDto;
import ir.co.sadad.cheque.domain.enums.BouncedReason;
import ir.co.sadad.cheque.domain.enums.CustomerType;
import ir.co.sadad.cheque.web.rest.external.dto.request.BouncedChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.BouncedChequeInquiryResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.BouncedReasonDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeItem;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeItemDto;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BouncedChequeInquiryMapper {

    @Mapping(source = "ssn", target = "customerInfo.nationalIdentifier")
    @Mapping(constant = "1", target = "customerInfo.customerType") // set static for real customer
    @Mapping(expression = "java(new  java.util.Date().getTime()+ssn )", target = "requestIdentifier")
    BouncedChequeRequestDto mapToRequest(String ssn);

    @Mapping(
        expression = "java(ir.co.sadad.cheque.domain.enums.CustomerType.getByCode(chequeCustomer.getCustomerType()))",
        target = "customerInfo.customerType"
    )
    @Mapping(source = "customerInfo.customerType", target = "customerInfo.customerTypeCode")
    BouncedChequeResponseDto mapToResponse(BouncedChequeInquiryResponseDto bouncedChequeInquiryResponseDto);

    @Mapping(
        expression = "java(ir.co.sadad.cheque.domain.enums.ChannelType.getByCode(chequeItem.getChannelType()))",
        target = "channelType"
    )
    @Mapping(
        expression = "java(ir.co.sadad.cheque.domain.enums.ChequeOwnerType.getByCode(chequeItem.getChequeOwnerType()))",
        target = "chequeOwnerType"
    )
    @Mapping(source = "chequeItem.channelType", target = "channelTypeCode")
    @Mapping(source = "chequeItem.chequeOwnerType", target = "chequeOwnerTypeCode")
    @Mapping(target = "bouncedReasons", source = "chequeItem.bouncedReasons")
    ChequeItemDto mapChequeInfo(ChequeItem chequeItem);


    default List<BouncedReasonDto> mapBouncedReasonDtoList(List<Integer> list) {
        if ( list == null ) {
            return null;
        }

        List<BouncedReasonDto> list1 = new ArrayList<>( list.size() );
        for ( Integer integer : list ) {
            BouncedReasonDto bouncedReasonDto = new BouncedReasonDto();
            bouncedReasonDto.setBouncedReason(BouncedReason.getByCode(integer));
            bouncedReasonDto.setBouncedReasonCode(integer);
            list1.add(bouncedReasonDto);
        }

        return list1;
    }

}
