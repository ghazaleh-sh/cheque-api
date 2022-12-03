package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Schema(title = "ایجکت درخواست استعلام دسته چک")
@Data
public class SayadChequeInquiryBatchRequestDto {


    @Schema(title = "شماره حساب ")
    private Integer account;

    @Schema(title = " نوع حساب ", description = "١-انفرادي" +
        " ٢-اشتراكي" +
        "  ٣-حقوقي" +
        " ٠-همه موارد")
    @Size(min = 1, max = 1, message = "chakad.error.sayad.account.bank.type.length.invalid")
    private Integer accountType;


    @Schema(title = "نوع شخص - 1 حقیقی - 2 حقوقی - 3 حقیقی غیر ایرانی - 4 حقوقی غیر ایرانی")
    private Integer typePersonality;


    @Schema(title = "شماره ملی - برای کاربران حقیقی 10 رقم- برای کاربران حقوقی 11 رقم - برای اتباع 8 تا 15 رقم")
    private String identifier;


    @Schema(title = " كد درخواست دسته چك", description = " به ازاي هر دسته چك، يك كد منحصر به فرد  وجود دارد")
    private String sayadRequestId;


    @Schema(title = " وضعیت دسته چک", description = " ١-جاري ٢ -بايگاني")
    @Size(min = 1, max = 1, message = "chakad.error.sayad.status.cheque.length.invalid")
    private Integer batchStatus;

    @Schema(title = " درخواست كننده")
    private Integer creator;


    @Schema(title = " تعداد برگ چك", description = " -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١" +
        " برگي ١٠٠*٢ -٥ برگي ٥٠*٤" +
        "  موارد همه-٠ برگي ٢ -٧ برگي ١ -٦")
    @Size(min = 1, max = 1, message = "chakad.error.sayad.sheet.count.length.invalid")
    private Integer sheetCount;

    @Schema(title = " تاريخ صدور از")
    private Date IssuanceDateFrom;

    @Schema(title = " تاریخ صدور تا")
    private Date IssuanceDateTo;

    @Schema(title = " تاريخ فعال سازي در صياد از")
    private Date activeDateFrom;

    @Schema(title = " تاريخ فعال سازي در صياد تا")
    private Date activeDateTo;


}
