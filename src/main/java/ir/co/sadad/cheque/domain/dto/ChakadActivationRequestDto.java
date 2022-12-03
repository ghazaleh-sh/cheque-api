package ir.co.sadad.cheque.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChakadActivationRequestDto extends ChakadDeactivationRequestDto {

    /**
     * data for customer activation
     */
    private activationDto customerActivation;

    /**
     * data for sign object
     */
    private ChakadSignDto sign;

}
