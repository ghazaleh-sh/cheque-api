package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PichakRequestChequeReqDto {

    @Schema(title = "حساب جاری بانک ملی - شماره شبا", required = true)
    @NotBlank(message = "chakad.error.sayad.iban.is.mandatory")
    @Length(min = 26, max = 26, message = "chakad.error.sayad.iban.length.invalid")
    private String iban;

    @Schema(title = "تعداد برگ چک", required = true, description = " برگی 25 )1" +
        " برگی 50 )2" +
        " برگی 100 )3" +
        "به صورت پیش فرض کد 1 درنظر گرفته می شود." +
        "کد 2 و 3 در حال حاضر غیرفعال می باشد.")
    @Range(min = 0, max = 3, message = "chakad.error.sayad.sheet.count.length.invalid")
    @NotNull(message = "chakad.error.sayad.sheet.count.is.mandatory")
    private Integer sheetCountId;
}
