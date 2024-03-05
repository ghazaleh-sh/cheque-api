package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * request dto for cartable service
 * <pre>
 *     contains sign data and inquiry data
 * </pre>
 * <pre>
 *     update in : 19/10/1401
 *     remove sign data
 * </pre>
 */
@Data
public class CartableRequestDto {

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
