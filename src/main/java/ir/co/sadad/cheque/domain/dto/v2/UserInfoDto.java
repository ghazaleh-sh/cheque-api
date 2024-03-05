package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Builder;
import lombok.Data;

/**
 * Data for communication with UserInfo Service
 * <pre>
 *     just user in
 *  <code> UserInfoService </code>
 * </pre>
 */
@Data
@Builder
public class UserInfoDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String shahabId;
    private String mobileNumber;
    private Integer challengeCode;
    private String activationTicket;
    private String certificateKeyId;
    private String transactionId;
    private Integer createHttpStatus;
    private Integer signHttpStatus;
    private String requestDateTime;
}
