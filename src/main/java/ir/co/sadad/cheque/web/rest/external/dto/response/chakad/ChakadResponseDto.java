package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * this is general response for chakad services
 */
@Data
public class ChakadResponseDto<T> {
    /**
     * message code of response
     * <pre>
     *     CHK100 = success
     * </pre>
     */
    private String massageCode;

    private String message;


    private T data;
}
