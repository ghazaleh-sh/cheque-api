package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivationDto {

    @NotNull(message = "chakad.error.request.type.is.mandatory")
    private Integer requestType;

    @NotNull(message = "chakad.error.mobile.number.is.mandatory")
    private String mobileNumber;

    private String simlessIdentifier;

    private Integer challengeCode;

    private String requestDateTime;

    private String activationTicketId;
}
