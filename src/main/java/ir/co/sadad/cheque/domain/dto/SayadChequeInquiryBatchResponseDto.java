package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(title = "آبجکت خروجی استعلام دسته چک")
public class SayadChequeInquiryBatchResponseDto {

    @Schema(title = " شماره حساب")
    private String account;

    @Schema(title = " نوع حساب", description = " ٠ .انفرادي" +
        " ١ .مشترك" +
        " ٢ .حقوقي")
    private Integer accountType;

    @Schema(title = " كد درخواست دسته چك")
    private Integer sayadRequestId;

    @Schema(title = " نوع چك", description = " عادی BANS .١" +
        " الكترونيكيCHD .٢" +
        " موردي CHS .٣" +
        " تضميني CHT .٤")
    private Integer mediaType;

    @Schema(title = " سري سريال چك از - تا")
    private String chequeNumberFromTo;

    @Schema(title = " وضعيت دسته چك", description = "١-جاري ٢ -بايگاني")
    private Integer batchStatus;

    @Schema(title = "درخواست كننده")
    private Integer creator;


    @Schema(title = " تعداد برگ چك", description = " -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١" +
        " برگي ١٠٠*٢ -٥ برگي ٥٠*٤" +
        "  موارد همه-٠ برگي ٢ -٧ برگي ١ -٦")
    private Integer sheetCount;

    @Schema(title = " وضعيت دسته چك")
    private Integer Status;

    @Schema(title = " كد شعبه درخواست كننده")
    private Integer creatorBranch;

    @Schema(title = "تاريخ صدور دسته چك")
    private Date IssuanceDate;

    @Schema(title = " تاريخ فعال سازي در صياد")
    private Date activeDate;


    @Schema(title = " تاريخ بايگاني (نهايي)")
    private Date archiveDate;
}
