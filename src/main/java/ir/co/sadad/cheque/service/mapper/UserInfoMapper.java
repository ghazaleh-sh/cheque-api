package ir.co.sadad.cheque.service.mapper;

import ir.co.sadad.cheque.domain.dto.v2.OrganizationInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeUserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserInfoMapper {

    ChequeUserInfo toEntity(UserInfoDto userInfo);

    ChequeUserInfo toEntity(OrganizationInfoDto organizationInfo);

    UserInfoResponseDto toDto(ChequeUserInfo chequeUserInfo);
}
