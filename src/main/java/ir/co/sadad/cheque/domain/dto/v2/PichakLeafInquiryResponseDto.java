package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.*;
import lombok.Data;

@Data
@Schema(title = "ابجکت خروجی استعلام برگ چک")
public class PichakLeafInquiryResponseDto {

    @Schema(title = " شناسه صیادی")
    private String sayadId;

    @Schema(title = " کد درخواست دسته چک")
    private Long chequeIssueId;

    @Schema(title = " نوع چک")
    private InquiryChequeType chequeType;

    private String localizedChequeType;

    @Schema(title = " تاریخ مبادله")
    private String depositDate;

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
    private LeafInquiryStatusType statusType;
    private String localizedStatusType;

    @Schema(title = " وضعیت برگشتی", description = " 1-دارای کد رهگیری 2-رفع سو اثر" +
        " 3-منتظر رفع سوء" +
        "4 - برگشت بدون رهگیری" +
        " 5 -  منتظر اصالح چک برگشتی")
    private Integer bounceStatus;
    private LeafInquiryReturnStatusType bounceStatusType;
    private String localizedBounceStatusType;

    @Schema(title = "  وضعیت مسدودی", description = " 1-مسدود موقت 2-مسدود دائم" +
        " 3-رفع مسدودی" +
        " 4-  منتظر مسدودی/ رفع مسدودی")
    private Integer blockStatus;
    private LeafInquiryBlockStatus blockStatusType;
    private String localizedBlockStatusType;

    @Schema(title = " lock و", description = " خیر-2 بله-1")
    private boolean lock;

    @Schema(title = " مبلغ")
    private Long amount;

    @Schema(title = " تاریخ صدور در صیاد")
    private String issueDate;
    private String issueDateUtc;

    @Schema(title = " تاریخ سررسید")
    private String settlementDate; //UTC

    @Schema(title = "وع وصول چک", description = " 1-درون بانکی 2-بین  بانکی)چکاوک(")
    private LeafInquiryBusinessType businessType;
    private String localizedBusinessType;

    @Schema(title = " مبلغ وصول")
    private Long cashingAmount;

    @Schema(title = " شناسه چک برگشتی")
    private String bounceCode;

    @Schema(title = " تاریخ ابطال")
    private String cancelDate;

    @Schema(title = " تاریخ مسدودی")
    private String blockStatusDate;

    @Schema(title = " تاریخ برگشت")
    private String bounceDate;

    @Schema(title = " نوع رفع سو اثر")
    private Integer clearCode;
    private LeafInquiryEreqType clearType;
    private String localizedClearType;

    @Schema(title = " تاریخ رفع سو اثر")
    private String clearDate;

//    @Schema(title = "ماهیت چک")
//    private boolean chequeMedia;
}
