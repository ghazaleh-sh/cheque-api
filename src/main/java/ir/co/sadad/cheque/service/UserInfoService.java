package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.OrganizationInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoDto;
import ir.co.sadad.cheque.domain.dto.v2.UserInfoResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeUserInfo;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;

import java.util.Optional;

/**
 * service for user info
 * <pre>
 *     this service is for manage user info in other business
 * </pre>
 */
public interface UserInfoService {

    /**
     * create user info for RequestType of ACTIVE
     *
     * @param userInfo info of user
     * @return savedUserInfo
     */
    UserInfoResponseDto createActiveRequestUser(UserInfoDto userInfo);

    /**
     * create user info for RequestType of updateProfile
     *
     * @param userInfo info of user
     * @return savedUserInfo
     */
    UserInfoResponseDto createUpdateProfileRequestUser(UserInfoDto userInfo);

    /**
     * create or update user info
     *
     * @param userInfo info of user
     * @return savedUserInfo
     */
    void createOrUpdateProfileRequestUser(UserInfoDto userInfo);

    /**
     * create org info for RequestType of Active
     *
     * @param organizationInfo info of organization
     * @return savedOrgInfo
     */
    UserInfoResponseDto createActiveRequestOrganization(OrganizationInfoDto organizationInfo);

    /**
     * create org info for RequestType of UpdateProfile
     *
     * @param organizationInfo info of organization
     * @return savedOrgInfo
     */
    UserInfoResponseDto createUpdateProfileRequestOrganization(OrganizationInfoDto organizationInfo);

    /**
     * add transactionId to user info , can find userInfo based on userId
     * <pre>
     *     'cause of userId is for Users and organization has orgId , then must determine which field is
     *     good for fetch data
     * </pre>
     *
     * @param userId        userId of user - ssn
     * @param transactionId transactionId
     * @param isOrgNeeded   is user organization ?
     * @return updated UserInfo
     */
    UserInfoResponseDto addTransactionId(String userId, String transactionId, boolean isOrgNeeded);


    /**
     * set status of activation for user based on ssn ,
     *
     * @param userId user id of user - ssn
     * @return activeUserInfo
     */
    UserInfoResponseDto activeUserBy(String userId, boolean isOrgNeeded);

    /**
     * deactivated user and update userInfo
     *
     * @param userId user id of user - ssn
     */
    void deactivateUserBy(String userId, boolean isOrgNeeded);

    /**
     * get info of user based on userId ,
     * <pre>
     *     Attention : userId here is SSN or organizationId od user .
     * </pre>
     *
     * @param userId      user id
     * @param isOrgNeeded
     * @return info of user
     */
    UserInfoResponseDto getBy(String userId, boolean isOrgNeeded);

    /**
     * get userInfo by id
     *
     * @param id if of db
     * @return info of user
     */
    UserInfoResponseDto getBy(Long id);


    /**
     * method for getting user Type enum based on user ssn
     * <pre>
     *     10 digit : individual
     *     11 digits : Corporate
     *     12-16: foreign_individual
     * </pre>
     *
     * @param ssn ssn of user
     * @return enum for IdType
     */
    CustomerIdType getUserType(String ssn);

    Optional<ChequeUserInfo> getByUserIdOptional(String userId, boolean isOrgNeeded);
}
