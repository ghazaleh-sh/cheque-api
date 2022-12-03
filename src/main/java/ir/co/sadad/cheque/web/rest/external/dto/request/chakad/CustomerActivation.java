package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerActivation {

    private Integer tokenType;

    @NotNull(message = "chakad.error.request.type.is.mandatory")
    private Integer requestType;

    private CustomerRequestDto customer;
    private OrganizationDto organization;
    private Integer legalStamp;

    @NotNull(message = "chakad.error.mobile.number.is.mandatory")
    private String mobileNumber;

    private String simlessIdentifier;

}
