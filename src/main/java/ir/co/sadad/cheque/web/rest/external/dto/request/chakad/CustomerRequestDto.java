package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * customer info for requests
 */
@Data
public class CustomerRequestDto {
    /**
     * identity for user
     * <pre>
     *     Real : 10 number
     *     legal : 11 number
     *     No Iranian user - legal or real : 8-15 number
     * </pre>
     * require
     */
    private String idCode;

    /**
     * type of user
     * <pre>
     *     1 = real
     *     2 = legal
     *     3 = Non Iranian Real
     *     4 = Non Iranian Legal
     * </pre>
     * require
     */
    private Integer idType;
}
