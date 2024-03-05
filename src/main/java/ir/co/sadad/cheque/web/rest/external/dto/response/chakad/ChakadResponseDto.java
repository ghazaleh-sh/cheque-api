package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * this is general response for chakad services
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChakadResponseDto<T> {
    /**
     * message code of response
     * <pre>
     *     CHK100 = success
     * </pre>
     */
    private String messageCode;

    /**
     * just like messageCode
     */
    private String message;

    private T data;

    private boolean succeeded;

    private String code;

}
