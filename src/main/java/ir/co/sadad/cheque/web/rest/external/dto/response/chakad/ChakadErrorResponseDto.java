package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * response of chakad services
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChakadErrorResponseDto {

    private boolean succeeded;
    private String code;
    private String message;
    private List<ErrorDetails> errorDetails;

    @Data
    public static class ErrorDetails {
        private String code;
        private String message;
    }
}
