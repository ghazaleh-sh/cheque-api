package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.service.DashboardService;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * dto for cartable request
 *
 * @see DashboardService
 */
@Data
@Schema(title = "آبجکت درخواست کارتابل چکاد")
public class ChakadCartableRequestDto {

    @NotNull(message = "chakad.error.customer.object.is.mandatory")
    @Schema(title = "آبجکت اطلاعات مشتری")
    @Valid
    private CustomerDto customerInfo;

    @NotNull(message = "chakad.error.sign.object.is.mandatory")
    @Schema(title = "آبجکت اطلاعات امضا")
    @Valid
    private ChakadSignDto sign;
}
