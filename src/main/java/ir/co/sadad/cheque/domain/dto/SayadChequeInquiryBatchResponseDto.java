package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.BookInquiryAccountType;
import ir.co.sadad.cheque.domain.enums.InquiryChequeType;
import ir.co.sadad.cheque.domain.enums.BookInquiryState;
import ir.co.sadad.cheque.domain.enums.BookInquiryStatusCode;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "آبجکت خروجی استعلام دسته چک")
public class SayadChequeInquiryBatchResponseDto {

    @Schema(title = " كد درخواست دسته چك")
    private Integer chequeIssueId;

    @Schema(title = " شماره حساب")
    private String accountNumber;

    @Schema(title = " نوع حساب", description = " ٠ .انفرادي" +
        " ١ .مشترك" +
        " ٢ .حقوقي")
    private BookInquiryAccountType accountType;
    private String localizedAccountType;

    @Schema(title = " نوع چك", description = " عادی BANS .١" +
        " الكترونيكيCHD .٢" +
        " موردي CHS .٣" +
        " تضميني CHT .٤")
    private InquiryChequeType chequeType;
    private String localizedChequeType;

//    @Schema(title = " ماهیت چک", description = "true کاغذی\n" +
//        "  False دیجیتال")
//    private Boolean chequeMedia;

    @Schema(title = " وضعيت دسته چك")
    private Integer state;
    private BookInquiryState stateType;
    private String localizedstateType;

    @Schema(title = "  وضعیت دسته چک")
    private BookInquiryStatusCode status;
    private String localizedStatus;

    @Schema(title = " كد شعبه درخواست كننده")
    private Integer creatorBranch;

    @Schema(title = " سري سريال چك از - تا")
    private List<String> chequeNumbers;

//    @Schema(title = " وضعيت دسته چك", description = "" +
//        " 0 : كليه وضعيت ها" +
//        "4 :صدور سريال" +
//        " 11: ابطال دسته چك" +
//        " 12:اعلام به سيبا")
//    private Integer status;

    @Schema(title = "تاريخ صدور دسته چك")
    private String issuanceDate;

    @Schema(title = " تاریخ تحویل در صیاد")
    private String deliveryDate;

    private String branchName;

    private String branchAddress;

    @Schema(title = " تعداد برگ چك", description = " -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١" +
        " برگي ١٠٠*٢ -٥ برگي ٥٠*٤" +
        "موارد همه-٠ برگي ٢ -٧ برگي ١ -٦")
    private Integer sheetCount;
}
