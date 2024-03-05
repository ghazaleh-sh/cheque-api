package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignatureCertificateDto {
    private Integer productUid;
    private String certificateKeyId;
}
