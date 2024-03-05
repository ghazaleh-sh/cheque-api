package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(title = "ابجکت خروجی استعلام برگ چک")
public class SayadChequeInquirySheetResponseDto {

    @Schema(title = " کد درخواست دسته چک")
    private Long sayadRequestId;

    @Schema(title = " شناسه صیادی")
    private String sayadChequeId;

    @Schema(title = " شماره حساب")
    private String accountNumber;

    @Schema(title = " سری سریال چک")
    private String chequeNumber;

    @Schema(title = " lock و", description = " 1-قابل استفاده" +
        " 2-منتظر تایید گیرنده" +
        " 3-صادر شده" +
        "  نقد شده4-" +
        " 5-برگشت شده" +
        "6 -  ابطال شده")
    private Integer status;

    @Schema(title = " وضعیت برگشتی", description = " 1-دارای کد رهگیری 2-رفع سو اثر" +
        " 3-منتظر رفع سوء" +
        "4 - برگشت بدون رهگیری" +
        " 5 -  منتظر اصالح چک برگشتی")
    private Integer returnStatus;

    @Schema(title = "  وضعیت مسدودی", description = " 1-مسدود موقت 2-مسدود دائم" +
        " 3-رفع مسدودی" +
        " 4-  منتظر مسدودی/ رفع مسدودی")
    private Integer blockStatus;

    @Schema(title = " lock و", description = " خیر-2 بله-1")
    private boolean lock;

    @Schema(title = " مبلغ")
    private Long amount;

    @Schema(title = " تاریخ صدور در صیاد")
    private Date issuanceDate;

    @Schema(title = " تاریخ سررسید")
    private Date settlementDate;

    @Schema(title = "ماهیت چک")
    private boolean chequeMedia;

    @Schema(title = " نوع چک")
    private Integer chequeType;

    @Schema(title = " تاریخ مبادله")
    private Date depositDate;

    @Schema(title = "وع وصول چک", description = " 1-درون بانکی 2-بین  بانکی)چکاوک(")
    private Integer businessType;

    @Schema(title = " مبلغ وصول")
    private Long receiptAmount;

    @Schema(title = " شناسه چک برگشتی")
    private String chequeReturnCode;

    @Schema(title = " تاریخ ابطال")
    private Date cancelDate;

    @Schema(title = " تاریخ مسدودی")
    private Date blockStatusDate;

    @Schema(title = " تاریخ برگشت")
    private Date returnDate;

    @Schema(title = " تاریخ رفع سو اثر")
    private Date ereqDate;

    @Schema(title = " نوع رفع سو اثر")
    private Integer ereqType;


}
