package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataToBeSignedDto {

    private String dataType;
    private String data;
    private Boolean isEncrypted;
    private String name;
    private String signatureImageParameter;

}
