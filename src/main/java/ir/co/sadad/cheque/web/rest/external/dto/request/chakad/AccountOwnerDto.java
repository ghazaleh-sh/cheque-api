package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * owner of account dto
 */
@Data
public class AccountOwnerDto {

    /**
     * 0 )حقیقی ایرانی
     * 1 )حقیقی غیرایرانی
     * 2 )حقوقی ایرانی
     * 3 )حقوقی غیرایرانی
     * به صورت پیش فرض کد 0 درنظر گرفته می شود.
     * کد 1 در حال حاضر غیرفعال می باشد.
     */
    private Integer personType;

    /**
     * با توجه به فیلد نوع متقاضی مقداردهی می گردد.
     * -برای اشخاص حقیقی ایرانی، کد ملی 10 رقم پر می شود.
     * -برای اشخاص حقوقی ایرانی، شناسه ملی 11رقم پر می شود.
     * -برای اشخاص حقیقی و حقوقی غیر ایرانی، کد فراگیر 8 تا
     * 13رقم پر می شود
     */
    private String identifier;

}
