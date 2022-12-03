package ir.co.sadad.cheque.web.rest.external.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class GeneralChequeExceptionDto {

    private ChequeErrorDto error;
    private SamatInquiryMainResponseDto response;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
