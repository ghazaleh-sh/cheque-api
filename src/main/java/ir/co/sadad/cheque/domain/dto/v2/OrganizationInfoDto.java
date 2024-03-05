package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Builder;
import lombok.Data;

/**
 * Dto for save and manage org s
 * <pre>
 *     just user in
 *  <code> UserInfoService </code>
 * </pre>
 */
@Data
@Builder
public class OrganizationInfoDto {
    private String organizationIdCode;
    private String organizationShahabId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Integer challengeCode;
    private String activationTicket;
    private String certificateKeyId;
    private String transactionId;
    private Integer createHttpStatus;
    private Integer signHttpStatus;
}
