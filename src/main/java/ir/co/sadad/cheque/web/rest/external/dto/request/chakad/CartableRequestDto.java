package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * request dto for cartable service
 * <pre>
 *     contains sign data and inquiry data
 * </pre>
 */
@Data
public class CartableRequestDto {

    /**
     * info for cartable request
     * require
     */
    private CustomerRequestDto inquiryCartable;

    /**
     * sign data
     */
    private Sign sign;
}
