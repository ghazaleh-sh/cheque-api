package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChequeIssueRequestDto {
    @NotBlank(message = "chakad.error.cms.is.mandatory")
    private String cms;

    private String signatureValue;

    private String certificateKeyID;

    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;
}
