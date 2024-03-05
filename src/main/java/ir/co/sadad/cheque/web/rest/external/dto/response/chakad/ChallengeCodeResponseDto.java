package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * challenge code response
 */
@Data
public class ChallengeCodeResponseDto {

    private Integer challengeCode;
    private String activationTicketId;
}
