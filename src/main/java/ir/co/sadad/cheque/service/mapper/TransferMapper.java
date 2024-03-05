package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.v2.TransferRequestDto;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.TransferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TransferMapper {

//    @Mapping(source = "giveBack", target = "giveBack", qualifiedByName = "giveBackConvert")
    ChequeTransfer toEntity(TransferRequestDto transferDto);

//    IssueResponseDto toDto(ChequeIssue chequeIssue);

//    @Named("giveBackConvert")
//    default Boolean giveBackConvert(Integer giveBack) {
//        return giveBack == 1;
//    }

}
