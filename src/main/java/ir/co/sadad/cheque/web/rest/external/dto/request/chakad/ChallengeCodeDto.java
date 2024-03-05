package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * request for challenge code
 */
@Data
public class ChallengeCodeDto {
    private Integer requestType;
    private Integer tokenType;
    private CustomerRequestDto customer;
    private OrganizationDto organization;
    private Integer legalStamp;
    private String mobileNumber;
//    private String requestDateTime;
    private String simlessIdentifier;
//    private String bankCode;
}
