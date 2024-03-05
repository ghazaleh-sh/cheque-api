package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * error response of Baambase services
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaambaseErrorResponseDto {
    private String errorCode;
    private String errorDesc;
    private List<SubErrorDto> subErrors;

    @Data
    public static class SubErrorDto {
        private String code;
        private String message;
    }

}
