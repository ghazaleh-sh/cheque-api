package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import java.util.List;

/**
 * dot for sayad request
 * <pre>
 *     this dto used for getting cheques
 *     for more info see : Cheque-Sayad-Request.pdf
 * </pre>
 *
 */
@Data
public class SayadRequestDto {

    /**
     * شماره شبا
     * حساب جاری بانک ملی
     * require - 26 char
     */
    private String IBAN;


    /**
     * نوع حساب:
     * 0-انفرادی
     * 1-اشتراکی
     * 2-حقوقی
     * کد 1 و 2 درحال حاضر غیرفعال می
     * باشد
     */
    private Integer accountType;

    /**
     * تعداد برگ چک:
     * برگی 25 )1
     * برگی 50 )2
     * برگی 100 )3
     * به صورت پیش فرض کد 1 درنظر گرفته می شود.
     * کد 2 و 3 در حال حاضر غیرفعال می باشد.
     * require
     */
    private Integer sheetCount;

    /**
     * نوع دسته چک:
     * 1 )کاغذی
     * 2 )دیجیتال
     * require
     */
    private Integer mediaType;

    /**
     * لیست صاحبین حساب
     */
    private List<AccountOwnerDto> accountOwner;

}
