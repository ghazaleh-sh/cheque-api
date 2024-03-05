package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * dto for batch inquiry cheques
 * <pre>
 *     for more info : GR-MNG-001.pdf
 * </pre>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeInquiryBatchRequestDto {

    /**
     * شماره حساب
     * require
     */
    private String accountNumber;

    /**
     * نوع حساب
     * ١-انفرادي ٢-اشتراكي ٣-حقوقي ٠-همه موارد
     */
    private Integer accountType;

    /**
     * ماهیت شناسه
     * -1حقیقی ایرانی -2حقوقی ایرانی -3حقیقی
     * غیرایرانی -4حقوقی غیر ایرانی
     */
    private Integer IdentifierType;

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
    private String ChequeIssueId;
    /**
     * تعداد برگ چك
     * -٤ برگي ١٠٠ -٣ برگي ٥٠ -٢ برگي ٢٥-١
     * برگي ١٠٠*٢ -٥ برگي ٥٠*٤
     * موارد همه-٠ برگي ٢ -٧ برگي ١ -٦
     */
    private Integer sheetCount;

    /**
     * وضعیت درخواست
     */
    private Integer State;

    /**
     * وضعیت دسته چک
     */
    private Integer StatusCode;

    /**
     * تاريخ صدور از
     */
    private Date IssuanceDateFrom;

    /**
     * تاريخ صدور ت
     */
    private Date IssuanceDateTo;

    /**
     * تاریخ تحویل از
     */
    private Date deliveryDateFrom;

    /**
     * تاریخ تحویل تا
     */
    private Date deliveryDateTo;


//    private Integer typePersonality;

}
