package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.Date;

/**
 * response of batch inquiry
 */
@Data
public class ChequeInquiryBatchResponseDto {

    private Integer messageCode;

    /**
     * شماره حساب
     */
    private String account;

    /**
     * نوع حساب
     * ٠ .انفرادي
     * ١ .مشترك
     * ٢ .حقوقي
     */
    private Integer accountType;

    /**
     * كد درخواست دسته چك
     */
    private Integer sayadRequestId;

    /**
     * نوع چك
     * عادی BANS .١
     * الكترونيكيCHD .٢
     * موردي CHS .٣
     * تضميني CHT .٤
     */
    private Integer mediaType;

    /**
     * سري سريال چك از - تا
     */
    private String chequeNumberFromTo;

    /**
     * وضعيت دسته چك
     * ١-جاري ٢ -بايگاني
     */
    private Integer batchStatus;

    /**
     * درخواست كننده
     */
    private Integer creator;

    /**
     * نوع دسته چك
     * برگي ٢٥ .١
     * برگي ٥٠ .٢
     * برگي ١٠٠ .٣
     * برگي٤*٥٠ .٤
     * برگي ١٠٠*٢ .٥
     * برگي ١ .٦
     * برگي ٢ .٧
     */
    private Integer sheetCount;

    /**
     * وضعيت دسته چك
     * 0 : كليه وضعيت ها
     * 4 :صدور سريال
     * 11: ابطال دسته چك
     * 12:اعلام به سيبا
     * there is one more row that has not any code
     */
    private Integer Status;

    /**
     * كد شعبه درخواست كننده
     */
    private Integer creatorBranch;

    /**
     * تاريخ صدور دسته چك
     */
    private Date IssuanceDate;

    /**
     * تاريخ فعال سازي در صياد
     */
    private Date activeDate;

    /**
     * تاريخ بايگاني (نهايي)
     */
    private Date archiveDate;
}
