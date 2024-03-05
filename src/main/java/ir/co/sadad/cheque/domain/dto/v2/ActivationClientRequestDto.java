package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ActivationClientRequestDto {

    @NotBlank(message = "chakad.error.cms.is.mandatory")
    private String cms;
    private String signatureValue;
    private String certificateKeyID;
    private String dataToBeSign;

    @NotBlank(message = "chakad.error.request.type.is.mandatory")
    private String requestType;
}
