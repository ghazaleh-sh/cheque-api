package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.Date;

/**
 * response for sheet
 */
@Data
public class ChequeInquirySheetResponseDto {


    private Integer messageCode;
    /**
     * کد درخواست دسته چک
     */
    private Integer sayadRequestId;
    /**
     * شناسه صیادی
     */
    private String sayadId;
    /**
     * شماره حساب
     */
    private String account;

    /**
     * سری سریال چک
     */
    private String chequeNumber;


    /**
     * 1-قابل استفاده 2-منتظر
     * تایید گیرنده 3-صادر شده 4-
     * نقد شده 5-برگشت شده 6 -
     * ابطال شده
     */
    private Integer status;

    /**
     * وضعیت برگشتی
     * 1-دارای کد رهگیری 2-رفع
     * سو اثر 3-منتظر رفع سوء 4 -
     * برگشت بدون رهگیری 5 -
     * منتظر اصالح چک برگشتی
     */
    private Integer returnStatus;

    /**
     * وضعیت مسدودی
     * 1-مسدود موقت 2-مسدود
     * دائم 3-رفع مسدودی 4-
     * منتظر مسدودی/ رفع
     * مسدودی
     */
    private Integer blockStatus;

    /**
     * lock و
     * خیر-2 بله-1
     */
    private Integer lock;

    /**
     * amount
     */
    private Long amount;

    /**
     * تاریخ صدور در صیاد
     */
    private Date issuanceDate;

    /**
     * تاریخ سررسید
     */
    private Date settelmentDate;

    /**
     * تاریخ مبادله
     */
    private Date depositDate;

    /**
     * وع وصول چک
     * 1-درون بانکی 2-بین
     * بانکی)چکاوک(
     */
    private Integer businessType;

    /**
     * مبلغ وصول
     */
    private Long receiptAmount;


    /**
     * شناسه چک برگشتی
     */
    private String chequeReturnCode;

    /**
     * تاریخ ابطال
     */
    private Date cancelDate;


    /**
     * تاریخ مسدودی
     */
    private Date blockDate;

    /**
     * تاریخ رفع مسدودی
     */
    private Date unblockDate;

    /**
     * تاریخ برگشت
     */
    private Date returnDate;

    /**
     * نوع رفع سو اثر
     * 1-تامین موجودی 2-الشه
     * چک 3-رضایت نامه محضری
     * 4-واریز مبلغ چک 5-حکم
     * قضایی 6-مشمول مرور زمان
     */
    private Integer documentType;

    /**
     * تاریخ رفع سو اثر
     */
    private Date ereqDate;


}
