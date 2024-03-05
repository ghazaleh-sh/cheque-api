package ir.co.sadad.cheque.domain.dto.v2;

import ir.co.sadad.cheque.domain.enums.ActivationResponseStatus;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.domain.enums.RequestType;
import ir.co.sadad.cheque.domain.enums.TokenType;
import lombok.Data;

import java.util.Date;

/**
 * response for CRUD service of userInfo
 * <pre>
 *       just user in
 *    <code> UserInfoService </code>
 *   </pre>
 */
@Data
public class UserInfoResponseDto {
    private String userId;
    private String firstName;
    private String lastName;
    private CustomerIdType idType;
    private String shahabId;
    private String mobileNumber;
    private TokenType tokenType;
    private Integer challengeCode;
    private String activationTicket;
    private String organizationIdCode;
    private CustomerIdType organizationIdType;
    private String organizationShahabId;
    private RequestType requestType;
    private String requestDateTime;
    private ActivationResponseStatus activationResponseStatus;
    private String certificateKeyId;
    private String transactionId;
    private Integer createHttpStatus;
    private Integer signHttpStatus;
}
