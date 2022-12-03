package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import java.util.Date;

/**
 * dto for batch inquiry cheques
 * <pre>
 *     for more info : GR-MNG-001.pdf
 * </pre>
 */
@Data
public class ChequeInquiryBatchRequestDto {

    /**
     * شماره حساب
     * require
     */
    private Integer account;

    /**
     * نوع حساب
     * ١-انفرادي ٢-اشتراكي ٣-حقوقي ٠-همه موارد
     */
    private Integer  accountType;

    /**
     * ماهيت شناسه
     * ١-حقيقي ايراني ٢-حقوقي ايراني ٣-حقيقي
     * غيرايراني ٤-حقوقي غير ايراني
     * require-if
     */
    private Integer typePersonality;

    /**
     * شناسه شخص
     * كدملي ١٠ رقم – شناسه حقوقي
     * ١١ رقم- كد فراگير ١٢ رقم
     * require-if
     */
    private String identifier;

    /**
     * كد درخواست دسته چك
     * به ازاي هر دسته چك، يك كد منحصر به فرد
     * وجود دارد.
     * require-if
     */
    private String sayadRequestId;

    /**
     * وضعيت دسته چك
     * ١-جاري ٢ -بايگاني
     */
    private Integer batchStatus;

    /**
     * درخواست كننده
     * مطابق جدول كاربري سند (جدول الف)
     */
    private Integer  creator;

    /**
     * تعداد برگ چك
     * -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١
     *  برگي ١٠٠*٢ -٥ برگي ٥٠*٤
     *  موارد همه-٠ برگي ٢ -٧ برگي ١ -٦
     */
    private Integer sheetCount;

    /**
     * تاريخ صدور از
     */
    private Date IssuanceDateFrom;

    /**
     * تاريخ صدور ت
     */
    private Date IssuanceDateTo;

    /**
     * تاريخ فعال سازي در صياد از
     */
    private Date activeDateFrom;

    /**
     * تاريخ فعال سازي در صياد تا
     */
    private Date activeDateTo;
}
