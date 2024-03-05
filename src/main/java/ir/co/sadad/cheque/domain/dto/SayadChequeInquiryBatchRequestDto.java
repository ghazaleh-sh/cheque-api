package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Schema(title = "ایجکت درخواست استعلام دسته چک")
@Data
public class SayadChequeInquiryBatchRequestDto {


    @Schema(title = "شماره حساب ")
    private String account;

    @Schema(title = " نوع حساب ", description = "١-انفرادي" +
        " ٢-اشتراكي" +
        "  ٣-حقوقي" +
        " ٠-همه موارد")
    @Range(min = 0, max = 3, message = "chakad.error.sayad.account.bank.type.length.invalid")
    private Integer accountType;


    @Schema(title = "ماهیت شناسه \n" +
        "-1حقیقی ایرانی -2حقوقی ایرانی -3حقیقی \n" +
        "غیرایرانی -4حقوقی غیر ایرانی")
    private Integer identifierType;


    @Schema(title = "شماره ملی - برای کاربران حقیقی 10 رقم- برای کاربران حقوقی 11 رقم - برای اتباع 8 تا 15 رقم")
    private String identifier;


    @Schema(title = " كد درخواست دسته چك", description = " به ازاي هر دسته چك، يك كد منحصر به فرد  وجود دارد")
    private String chequeIssueId;

    @Schema(title = " تعداد برگ چك", description = " -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١" +
        " برگي ١٠٠*٢ -٥ برگي ٥٠*٤" +
        "  موارد همه-٠ برگي ٢ -٧ برگي ١ -٦")
    @Range(min = 0, max = 7, message = "chakad.error.sayad.sheet.count.length.invalid")
    private Integer sheetCount;

    @Schema(title = " تاريخ صدور از")
    private Date issuanceDateFrom;

    @Schema(title = " تاریخ صدور تا")
    private Date issuanceDateTo;

    @Schema(title = " تاریخ تحویل از")
    private Date deliveryDateFrom;

    @Schema(title = " تاریخ تحویل تا")
    private Date deliveryDateTo;

    @Schema(title = "وضعیت درخواست")
    private Integer state;

    @Schema(title = "وضعیت دسته چک")
    private Integer statusCode;

}
