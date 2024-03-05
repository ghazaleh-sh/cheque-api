package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sign {

    @NotNull(message = "chakad.error.cms.is.mandatory")
    private String cms;

    @NotNull(message = "chakad.error.signing.status.is.mandatory")
    private Integer signingStatus;

    @NotNull(message = "chakad.error.signer.id.code.is.mandatory")
    private String signerIdCode;

    @NotNull(message = "chakad.error.signer.id.type.is.mandatory")
    private Integer signerIdType;

    private Integer tokenType;
    private String signerOrganizationIdCode;
    private Integer signerOrganizationIdType;
    private Integer legalStamp;
    private String signDate;
}
