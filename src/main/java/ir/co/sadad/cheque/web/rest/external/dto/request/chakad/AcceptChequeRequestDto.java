package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptChequeRequestDto {
    String sayadId;

    /**
     * 0: رد چک
     * 1: قبول چک
     */
    Integer accept;
    Integer identifierType;
    String identifier;
    Integer agentType;
    String agentIdentifier;
    String description;
}
