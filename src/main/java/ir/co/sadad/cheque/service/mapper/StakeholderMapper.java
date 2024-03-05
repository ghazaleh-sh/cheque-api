package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.v2.StakeholderDto;
import ir.co.sadad.cheque.domain.dto.v2.StakeholderResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StakeholderMapper {

    ChequeStakeholder toEntity(StakeholderDto stakeholderDto);

    List<StakeholderResponseDto> toDto(List<ChequeStakeholder> stakeholder);
}
