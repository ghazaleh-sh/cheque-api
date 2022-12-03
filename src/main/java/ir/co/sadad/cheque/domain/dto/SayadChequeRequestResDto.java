package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "خروجی سرویس درخواست دسته چک", description = "به دلیل اینکه این سرویس خروجی ندارد " +
    "لذا این خروجی جهت نمایش انجام دادن کار است - خود سرویس اصلی فاقد خروجی است")
@Data
public class SayadChequeRequestResDto {

    @Schema(title = "ایا عملیات درخواست چک انجام شده است")
    private boolean isDone;


}
