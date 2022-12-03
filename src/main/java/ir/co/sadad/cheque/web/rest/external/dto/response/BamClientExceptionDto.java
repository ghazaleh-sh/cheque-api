package ir.co.sadad.cheque.web.rest.external.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BamClientExceptionDto {

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;
}
