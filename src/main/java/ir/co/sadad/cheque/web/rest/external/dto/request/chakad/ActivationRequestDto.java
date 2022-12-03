package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * request dto for activation service
 * <pre>
 *     contains customer info and sign data
 * </pre>
 */
@Data
public class ActivationRequestDto {

    private CustomerActivation customerActivation;
    private Sign sign;

}
