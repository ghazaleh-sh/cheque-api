package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChakadTransferTotalResponseDto {
    /**
     * errorDetails of response
     */
    private String errorDetails;

    /**
     * includes code of error and success response
     */
    private String message;

    private String data;

    /**
     * false if service has error
     */
    private boolean succeeded;

    /**
     * if otp is sent, is 412, otherwise is null
     */
    private String code;
}
