package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.validation.NationalCode;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
//@NationalCode(value = {"idCode", "idType"})
@Schema(title = "آبجکت اطلاعات مشتری")
public class CustomerDto {

    @NotBlank(message = "chakad.error.code.customer.is.mandatory")
    @Schema(title = "شماره ملی - برای کاربران حقیقی 10 رقم- برای کاربران حقوقی 11 رقم - برای اتباع 8 تا 15 رقم" , required = true)
    private String idCode;

    @NotNull(message = "chakad.error.type.customer.is.mandatory")
    @Schema(title = "نوع شخص - 1 حقیقی - 2 حقوقی - 3 حقیقی غیر ایرانی - 4 حقوقی غیر ایرانی" , required = true)
    private String idType;
}
