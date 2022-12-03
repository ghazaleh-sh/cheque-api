package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "اطللاعات صاحب حساب")
@Data
public class SayadChequeAccountOwnerDto {

    @Schema(title = "نوع شخص - بصورت پیشفرض 0 در نظر گرفته شود", description = "2 )حقوقی ایرانی" +
        "1 )حقیقی غیرایرانی" +
        " 3 )حقوقی غیرایرانی" +
        " 0 )حقیقی ایرانی")
    private Integer personType;

    /**
     * با توجه به فیلد نوع متقاضی مقداردهی می گردد.
     * -برای اشخاص حقیقی ایرانی، کد ملی 10 رقم پر می شود.
     * -برای اشخاص حقوقی ایرانی، شناسه ملی 11رقم پر می شود.
     * -برای اشخاص حقیقی و حقوقی غیر ایرانی، کد فراگیر 8 تا
     * 13رقم پر می شود
     */
    @Schema(title = "کد شناسایی", description = "" +
        "-برای اشخاص حقیقی ایرانی، کد ملی 10 رقم پر می شود" +
        "برای اشخاص حقوقی ایرانی، شناسه ملی 11رقم پر می شود" +
        " -برای اشخاص حقیقی و حقوقی غیر ایرانی، کد فراگیر 8 تا\n" +
        " 13رقم پر می شود")
    private Integer identifier;

}
