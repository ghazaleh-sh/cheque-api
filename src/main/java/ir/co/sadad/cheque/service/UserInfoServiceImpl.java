package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.OrganizationInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeUserInfo;
import ir.co.sadad.cheque.domain.enums.ActivationResponseStatus;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.domain.enums.RequestType;
import ir.co.sadad.cheque.domain.enums.TokenType;
import ir.co.sadad.cheque.repository.ChequeUserInfoRepository;
import ir.co.sadad.cheque.service.mapper.UserInfoMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.Date;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final ChequeUserInfoRepository userInfoRepository;
    private final UserInfoMapper mapper;

    @Override
    public UserInfoResponseDto createActiveRequestUser(UserInfoDto userInfo) {
        checkExistenceOfUser(userInfo.getUserId(), false);
        ChequeUserInfo mustSaveUserInfo = getChequeUserInfo(userInfo);
        mustSaveUserInfo.setRequestType(RequestType.ACTIVATION);
        return mapper.toDto(userInfoRepository.saveAndFlush(mustSaveUserInfo));
    }

    @Override
    public UserInfoResponseDto createUpdateProfileRequestUser(UserInfoDto userInfo) {
        checkExistenceOfUser(userInfo.getUserId(), false);
        ChequeUserInfo mustSaveUserInfo = getChequeUserInfo(userInfo);
        mustSaveUserInfo.setRequestType(RequestType.UPDATE_PROFILE);
        return mapper.toDto(userInfoRepository.saveAndFlush(mustSaveUserInfo));
    }

    @Override
    public void createOrUpdateProfileRequestUser(UserInfoDto userInfo) {
        Optional<ChequeUserInfo> user = getByUserIdOptional(userInfo.getUserId(), false);
        if (user.isEmpty()) {
            ChequeUserInfo mustSaveUserInfo = getChequeUserInfo(userInfo);
            mustSaveUserInfo.setRequestType(RequestType.ACTIVATION);
            userInfoRepository.saveAndFlush(mustSaveUserInfo);
        }
    }

    @Override
    public UserInfoResponseDto createActiveRequestOrganization(OrganizationInfoDto organizationInfo) {
        checkExistenceOfUser(organizationInfo.getOrganizationIdCode(), true);
        ChequeUserInfo mustSaveUserInfo = getChequeUserInfo(organizationInfo);
        mustSaveUserInfo.setRequestType(RequestType.ACTIVATION);
        return mapper.toDto(userInfoRepository.saveAndFlush(mustSaveUserInfo));
    }

    @Override
    public UserInfoResponseDto createUpdateProfileRequestOrganization(OrganizationInfoDto organizationInfo) {
        checkExistenceOfUser(organizationInfo.getOrganizationIdCode(), true);
        ChequeUserInfo mustSaveUserInfo = getChequeUserInfo(organizationInfo);
        mustSaveUserInfo.setRequestType(RequestType.UPDATE_PROFILE);
        return mapper.toDto(userInfoRepository.saveAndFlush(mustSaveUserInfo));
    }

    @Override
    public UserInfoResponseDto addTransactionId(String userId, String transactionId, boolean isOrgNeeded) {
        ChequeUserInfo savedUserInfo = this.getByUserId(userId, isOrgNeeded);
        savedUserInfo.setTransactionId(transactionId);
        return mapper.toDto(userInfoRepository.saveAndFlush(savedUserInfo));
    }

    @Override
    public UserInfoResponseDto activeUserBy(String userId, boolean isOrgNeeded) {
        ChequeUserInfo savedUserInfo = this.getByUserId(userId, isOrgNeeded);
        savedUserInfo.setActivationResponseStatus(ActivationResponseStatus.ACTIVE);
        return mapper.toDto(userInfoRepository.saveAndFlush(savedUserInfo));
    }

    @Override
    public void deactivateUserBy(String userId, boolean isOrgNeeded) {
        ChequeUserInfo savedUserInfo = this.getByUserId(userId, isOrgNeeded);
        savedUserInfo.setActivationResponseStatus(ActivationResponseStatus.INACTIVE);
        userInfoRepository.saveAndFlush(savedUserInfo);
    }

    @Override
    public UserInfoResponseDto getBy(String userId, boolean isOrgNeeded) {
        return mapper.toDto(this.getByUserId(userId, isOrgNeeded));
    }

    @Override
    public UserInfoResponseDto getBy(Long id) {
        return mapper.toDto(userInfoRepository.getById(id));
    }


    @Override
    public CustomerIdType getUserType(String ssn) {
        switch (ssn.length()) {
            case 10:
                return CustomerIdType.INDIVIDUAL;
            case 11:
                return CustomerIdType.CORPORATE;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return CustomerIdType.FOREIGN_INDIVIDUAL;
            default:
                throw new ChakadClientException("ssn.length.not.valid", Status.BAD_REQUEST);
        }
    }

    private ChequeUserInfo getChequeUserInfo(UserInfoDto userInfo) {
        ChequeUserInfo mustSaveUserInfo = mapper.toEntity(userInfo);
        mustSaveUserInfo.setTokenType(TokenType.NAMAD);
        mustSaveUserInfo.setIdType(getUserType(userInfo.getUserId()));
//        mustSaveUserInfo.setRequestDateTime(new Date());
        return mustSaveUserInfo;
    }

    private ChequeUserInfo getChequeUserInfo(OrganizationInfoDto organizationInfo) {
        ChequeUserInfo mustSaveUserInfo = mapper.toEntity(organizationInfo);
        mustSaveUserInfo.setTokenType(TokenType.NAMAD);
        mustSaveUserInfo.setOrganizationIdType(getUserType(organizationInfo.getOrganizationIdCode()));
//        mustSaveUserInfo.setRequestDateTime(new Date());
        return mustSaveUserInfo;
    }

    private ChequeUserInfo getByUserId(String userId, boolean isOrgNeeded) {
        if (isOrgNeeded)
            return userInfoRepository.findByOrganizationIdCode(userId).orElseThrow(() -> new ChakadClientException("CHQB.NOT.FOUND.900", Status.NOT_FOUND));
        else
            return userInfoRepository.findByUserId(userId).orElseThrow(() -> new ChakadClientException("CHQB.NOT.FOUND.900", Status.NOT_FOUND));
    }

    /**
     * method for check existence of user
     * <pre>
     *     this method will check user in order of :
     *     1 . check for user or organization
     *     2 . if user exist , then must check activationStatus
     *     2.1 . if user was active , then throws exception
     *     2.2 . if user was inactive , then delete user and ready for create new profile for user
     *     3 . is user wad not found , then nothing happens , must proceed to create user
     * </pre>
     *
     * @param userId      id of user
     * @param isOrgNeeded is userId used for organization or user
     */
    private void checkExistenceOfUser(String userId, boolean isOrgNeeded) {
        Optional<ChequeUserInfo> savedOrg = isOrgNeeded ? userInfoRepository.findByOrganizationIdCode(userId) :
            userInfoRepository.findByUserId(userId);
        if (savedOrg.isPresent()) {
            if (savedOrg.get().getActivationResponseStatus() == ActivationResponseStatus.ACTIVE) {
                throw new ChakadClientException("CHQB.USER.IS.ACTIVE.901", Status.BAD_REQUEST);
            } else {
                userInfoRepository.delete(savedOrg.get());
            }
        }
    }

    @Override
    public Optional<ChequeUserInfo> getByUserIdOptional(String userId, boolean isOrgNeeded) {
        if (isOrgNeeded)
            return userInfoRepository.findByOrganizationIdCode(userId);
        else
            return userInfoRepository.findByUserId(userId);
    }

}
