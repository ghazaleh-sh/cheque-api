package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Schema(title = "ابجکت درخواست دسته چک")
@Data
public class SayadChequeRequestDto {

    @Schema(title = "حساب جاری بانک ملی - شماره شبا", required = true)
    @NotBlank(message = "chakad.error.sayad.iban.is.mandatory")
    @Length(min = 26, max = 26, message = "chakad.error.sayad.iban.length.invalid")
    private String IBAN;

    @Schema(title = "نوع حساب - کد 1 و 2 در حال حاضر غیر فعال است", required = false, description = "0-انفرادی" +
        " 1-اشتراکی" +
        " 2-حقوقی")
    @Range(min = 0, max = 2, message = "chakad.error.sayad.account.type.length.invalid")
    private Integer accountType;

    @Schema(title = "تعداد برگ چک", required = true, description = " برگی 25 )1" +
        " برگی 50 )2" +
        " برگی 100 )3" +
        "به صورت پیش فرض کد 1 درنظر گرفته می شود." +
        "کد 2 و 3 در حال حاضر غیرفعال می باشد.")
    @Range(min = 1, max = 3, message = "chakad.error.sayad.sheet.count.length.invalid")
    @NotNull(message = "chakad.error.sayad.sheet.count.is.mandatory")
    private Integer sheetCount;

    @Schema(title = "نوع دسته چک", required = true, description = " 1 )کاغذی" +
        " 2 )دیجیتال")
    @Range(min = 1, max = 2, message = "chakad.error.sayad.media.type.length.invalid")
    @NotNull(message = "chakad.error.sayad.media.type.is.mandatory")
    private Integer mediaType;

    @Schema(title = "لیست صاحبین حساب")
    @NotNull(message = "chakad.error.sayad.account.owner.is.mandatory")
    private List<SayadChequeAccountOwnerDto> accountOwner;

}
