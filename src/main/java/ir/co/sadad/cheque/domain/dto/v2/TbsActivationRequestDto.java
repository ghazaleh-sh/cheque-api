package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * this Dto is used to provide some parameters of user tbs
 */
@Data
public class TbsActivationRequestDto {

    private String firstName;
    private String lastName;

    @NotBlank(message = "chakad.error.mobile.number.is.mandatory")
//    @Pattern(regexp = "(^0[0-9]{10})|(^98[0-9]{10})$", message = "chakad.error.mobile.number.not.valid")
    private String mobileNumber;

    private String shahabId;
}
