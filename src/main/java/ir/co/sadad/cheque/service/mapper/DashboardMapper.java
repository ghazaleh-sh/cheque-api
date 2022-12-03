package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeSigner;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CartableRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.IssueChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.TransferChequeRequestDto;
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
     * <pre>
     *     tokenType of sign is always "1"
     * </pre>
     *
     * @param cartableRequest request of wrapper
     * @return request for eternal service
     */
    @Mapping(constant = "52", target = "sign.tokenType")
    @Mapping(source = "cartableRequest.customerInfo.idCode", target = "inquiryCartable.idCode")
    @Mapping(source = "cartableRequest.customerInfo.idType", target = "inquiryCartable.idType")
    CartableRequestDto toCartableReqDto(ChakadCartableRequestDto cartableRequest);

    /**
     * used for map response of external service of cartable to response of wrapper
     *
     * @param cartable response of external service
     * @return cartable response
     */
    ChakadCartableResponseDto toCartableResDto(CartableDto cartable);

    /**
     * used for map reason entity to dto
     *
     * @param reasons list of entities
     * @return list of dto
     */
    List<ReasonResponseDto> toReasonResponse(List<ChequeReason> reasons);

    @Mapping(constant = "52", target = "sign.tokenType")
    IssueChequeRequestDto toIssueCheque(ChakadIssueRequestDto issueRequestDto);

    @Mapping(constant = "52", target = "sign.tokenType")
    TransferChequeRequestDto toTransferCheque(ChakadTransferRequestDto transferRequestDto);

    ChakadTransferResponseDto transferResponse(TransferChequeResponseDto result);

    ChequeIssue toIssueEntity(ChakadChequeInfoDto chequeInfoDto);

    List<ChequeSigner> toSignerEntity(List<ChakadSignerDto> signerDtos);

    @Mapping(source = "acceptTransfer", target = "acceptTransfer", qualifiedByName = "acceptTransferConvert")
    @Mapping(source = "giveBack", target = "giveBack", qualifiedByName = "giveBackConvert")
    ChequeTransfer toTransferEntity(ChakadTransferInfoDto transfer);

    @Named("acceptTransferConvert")
    default Boolean acceptTransferConvert(Integer acceptTransfer) {
        return acceptTransfer == 1;
    }

    @Named("giveBackConvert")
    default Boolean giveBackConvert(Integer giveBack) {
        return giveBack == 1;
    }
}
