package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.domain.enums.*;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CartableDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.TransferChequeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * mapper for dashboard services
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DashboardMapper {

    /**
     * used for map request of wrapper to external service request
     *
     * @param cartableRequest request of wrapper
     * @return request for eternal service
     */
    @Mapping(source = "idType", target = "idType", qualifiedByName = "idTypeConvert")
    CartableRequestDto toCartableReqDto(ChakadCartableRequestDto cartableRequest);

    /**
     * used for map response of external service of cartable to response of wrapper
     *
     * @param cartable response of external service
     * @return cartable response
     */
    List<ChakadCartableResponseDto> toCartableResDto(List<CartableDto> cartable);

    /**
     * used for map reason entity to dto
     *
     * @param reasons list of entities
     * @return list of dto
     */
    List<ReasonResponseDto> toReasonResponse(List<ChequeReason> reasons);

    @Mapping(constant = "1", target = "sign.tokenType")
    @Mapping(source = "cheque.sayadId", target = "cheque.sayadId")
    IssueChequeRequestDto toIssueCheque(ChakadIssueRequestDto issueRequestDto);

    @Mapping(constant = "1", target = "sign.tokenType")
    TransferDto toTransferCheque(ChakadTransferInfoDto transferRequestDto);

    ChakadTransferResponseDto transferResponse(TransferChequeResponseDto result);

    ChequeIssue toIssueEntity(ChakadChequeInfoDto chequeInfoDto);

    List<ChequeStakeholder> toُStakeholderEntity(List<ChakadSignerDto> signerDtos);

//    @Mapping(source = "acceptTransfer", target = "acceptTransfer", qualifiedByName = "acceptTransferConvert")
    @Mapping(source = "giveBack", target = "giveBack", qualifiedByName = "giveBackConvert")
    ChequeTransfer toTransferEntity(ChakadTransferInfoDto transfer);

//    @Named("acceptTransferConvert")
//    default Boolean acceptTransferConvert(Integer acceptTransfer) {
//        return acceptTransfer == 1;
//    }

    @Named("giveBackConvert")
    default Boolean giveBackConvert(Integer giveBack) {
        return giveBack == 1;
    }

    @Named("idTypeConvert")
    default Integer idTypeConverter(String idTypeCustomer) {
        return CustomerIdType.valueOf(idTypeCustomer).getValue();
    }

    @Mapping(source = "accept", target = "accept", qualifiedByName = "acceptConvert")
    AcceptChequeRequestDto toAcceptCheque(ChakadAcceptRequestDto acceptRequestDto);

    @Named("acceptConvert")
    default Integer acceptConvert(Boolean accept) {
        if (accept) return 1;
        return 0;
    }

    @Mapping(source = "locked", target = "locked", qualifiedByName = "lockedConvert")
    @Mapping(source = "chequeType", target = "chequeType", qualifiedByName = "typeConvert")
    @Mapping(source = "guaranteeStatus", target = "guaranteeStatus", qualifiedByName = "guaranteeStatusConvert")
    @Mapping(source = "blockStatus", target = "blockStatus", qualifiedByName = "blockStatusConvert")
    @Mapping(source = "chequeStatus", target = "chequeStatus", qualifiedByName = "chequeStatusConvert")
    @Mapping(source = "currency", target = "currency", qualifiedByName = "currencyConvert")
//    @Mapping(source = "chequeOwner.idType", target = "chequeOwner.idType", ignore = true)
    ChequeCartableDto toCartableResDto(CartableDto cartableRes);

    @Named("currencyConvert")
    default String currencyConvert(Integer currency) {
        return "IRR";
    }

    @Named("lockedConvert")
    default Boolean lockedConvert(Integer locked) {
        return locked.equals(1);
    }

    @Named("typeConvert")
    default CartableChequeType cartableTypeConvert(Integer type) {
        return CartableChequeType.getByCode(type);
    }

    @Named("chequeStatusConvert")
    default CartableChequeStatus chequeStatusConvert(Integer status) {
        return CartableChequeStatus.getByCode(status);
    }

    @Named("guaranteeStatusConvert")
    default CartableGuaranteeStatus guaranteeStatusConvert(Integer guaranteeStatus) {
        return CartableGuaranteeStatus.getByCode(guaranteeStatus);
    }

    @Named("blockStatusConvert")
    default GuaranteeBlockStatus blockStatusConvert(Integer blockStatus) {
        return GuaranteeBlockStatus.getByCode(blockStatus);
    }

    @Mapping(constant = "1", target = "acceptTransfer")
    @Mapping(source = "giveBack", target = "giveBack", qualifiedByName = "giveBackConvert2")
    TransferDto toTransferChequeReq(TransferRequestDto transferRequestDto);

    @Named("giveBackConvert2")
    default Integer giveBackConvert2(Boolean giveBack) {
        if (giveBack) return 1;
        return 0;
    }

    TransferResponseDto toTransferResponse(TransferRequestDto transferRequestDto);

    @Mapping(source = "bounceCheque", target = "bounceCheque", qualifiedByName = "bounceChequeConvert")
    DepositRegisterDto toDepositRegister(DepositRegisterRequestDto depositRegisterRequestDto);

    @Named("bounceChequeConvert")
    default Integer bounceChequeConvert(Boolean bounceCheque) {
        if (bounceCheque) return 1;
        return 0;
    }

    List<UserDto> toUserReceiverDto(List<ReceiverDto> receivers);

    List<UserDto> toUserSignerDto(List<SignerDto> receivers);
}
