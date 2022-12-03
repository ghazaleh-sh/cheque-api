package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * request item of cheques in sayad report
 * <pre>
 *     for more info see : Cheque-Sayad-Report.pdf
 * </pre>
 */
@Data
public class RequestItemDto {

    /**
     * تعداد برگ چک
     * گی 25 )1
     *  برگی 50 )2
     *  برگی 100 )3
     * به صورت پیش فرض کد 1 درنظر گرفته می شود.
     * کد 2 و 3 در حال حاضر غیرفعال می باشد.
     */
    private Integer sheetCount;

    /**
     *  تاریخ ثبت درخواست
     */
    private Integer requestDate;

    /**
     *1 )ثبت درخواست
     * 2 )رد درخواست توسط شعبه
     * 3 )عدم صالحیت –دارای چک برگشتی
     * 4 )عدم صالحیت –دارای بدهی معوق
     * 5 )عدم صالحیت –دارای ممنوعیت قضایی
     * 6 )عدم صالحیت –دارای دسته چک از حساب دیگر
     * 7 )تایید درخواست
     * 8 )چاپ دسته چک
     * 9 )منتظر مراجعه مشتری به شعبه )در حال حاضر غیرفعال(
     */
    private Integer requestStatus;

    /**
     * تاریخ آخرین وضعیت رکورد را نمایش می دهد.
     */
    private Integer updateDate;

    /**
     * 1 )شعبه
     * 2 )بام
     */
    private Integer requestInput;

    /**
     *  شماره چک )از-تا(
     * درصورتیکه وضعیت درخواست"چاپ دسته چک" باشد، اولین و آخرین شماره برگه
     * چک اعالم می گردد. بعنوان نمونه 9920330575-9
     *
     */
    private String chequeNumber;

    /**
     * 1 )کاغذی
     * 2 )دیجیتا
     */
    private Integer mediaType;


}
