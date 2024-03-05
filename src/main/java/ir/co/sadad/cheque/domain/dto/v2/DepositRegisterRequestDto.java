package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * request of deposit register service
 */
@Data
public class DepositRegisterRequestDto {

    @Schema(title = "گواهی عدم پرداخت در صورت نیاز صادر شود؟")
    private Boolean bounceCheque;

    @Schema(title = "شماره حساب واگذارنده")
    @Size(min = 13, max = 13, message = "chakad.error.sayad.account.number.length.invalid")
    private String holderAccountNumber;

    @Schema(title = "شناسه صیاد")
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;

}
