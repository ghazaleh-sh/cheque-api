package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.v2.IssueDto;
import ir.co.sadad.cheque.domain.dto.v2.IssueResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ChequeIssueMapper {

    ChequeIssue toEntity(IssueDto issueDto);

    IssueResponseDto toDto(ChequeIssue chequeIssue);
}
