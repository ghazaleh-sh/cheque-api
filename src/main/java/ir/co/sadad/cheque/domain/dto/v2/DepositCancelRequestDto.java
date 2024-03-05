package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DepositCancelRequestDto {

    @Schema(title = "شناسه صیاد")
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;

//    private String checkoutRequestId;
}
