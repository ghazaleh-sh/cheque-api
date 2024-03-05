package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.Date;

/**
 * data of batch inquiry
 */
@Data
public class ChequeInquiryBatchDataResponseDto {
    /**
     * شماره حساب
     */
    private String accountNumber;

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
    private Integer ChequeIssueId;

    /**
     * نوع چک
     * 1عادی
     * -2رمزدار
     * -3تضمینی
     *  -4موردی
     */
    private Integer ChequeType;

    /**
     * ماهیت چک
     * true کاغذی
     * False دیجیتال
     */
    private Boolean ChequeMedia;

    /**
     * سري سريال چك از - تا
     */
    private String ChequeNumbers;

    /**
     * عداد برگ چک
     * 25
     *  50
     *  100
     *  200
     * 1
     * 2
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

//    /**
//     * وضعيت دسته چك
//     * 0 : كليه وضعيت ها
//     * 4 :صدور سريال
//     * 11: ابطال دسته چك
//     * 12:اعلام به سيبا
//     * there is one more row that has not any code
//     */
//    private Integer Status;

    /**
     * كد شعبه درخواست كننده
     */
    private Integer creatorBranch;

    /**
     * تاريخ صدور دسته چك
     */
    private String IssuanceDate;

    /**
     * تاریخ تحویل در صیاد
     */
    private String deliveryDate;

}
