package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

/**
 * response of data in inquiry sheet sayad
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChequeInquirySheetDataResponseDto {

    private Long chequeIssueId;

    /**
     * شناسه صیادی
     */
    private String sayadId;

    /**
     * ماهیت چک
     * 1کاغذی -0دیجیتال
     */
    private boolean chequeMedia;

    /**
     * نوع چک
     * 1 عادی
     * 2 رمز دار
     * 3 تضمینی
     * 4 موردی
     */
    private Integer chequeType;

    /**
     * تاریخ مبادله
     */
    private String depositDate;

    /**
     * شماره حساب
     */
    private String accountNumber;

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
    private Integer bounceStatus;

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
    private boolean lock;

    /**
     * amount
     */
    private Long amount;

    /**
     * تاریخ صدور در صیاد
     */
    private String issueDate;

    /**
     * تاریخ سررسید
     */
    private String settlementDate;

    /**
     * وع وصول چک
     * 1-درون بانکی 2-بین
     * بانکی)چکاوک(
     */
    private Integer businessType;

    /**
     * مبلغ وصول
     */
    private Long cashingAmount;


    /**
     * شناسه چک برگشتی
     */
    private String bounceCode;

    /**
     * تاریخ ابطال
     */
    private String cancelDate;

    /**
     * تاریخ مسدودی/ رفع مسدودی
     */
    private String blockStatusDate;

    /**
     * تاریخ برگشت
     */
    private String bounceDate;

    /**
     * تاریخ رفع سو اثر
     */
    private String clearDate;

    /**
     * نوع رفع سو اثر
     * 1تامین موجودی -2الشه
     * چک -3رضایت نامه محضری
     * -4واریز مبلغ چک -5حکم
     * قضایی -6مشمول مرور زمان
     */
    private Integer clearType;

}
