package ir.co.sadad.cheque.domain.dto.v2;

import ir.co.sadad.cheque.domain.enums.*;
import lombok.Data;

import java.util.List;

@Data
public class ChequeCartableDto {
    /**
     * شناسه صياد چك
     */
    private String sayadId;

    /**
     * شماره سريال چك
     */
    private String serialNo;

    /**
     * شماره سري چك
     */
    private String seriesNo;

    /**
     * كد شبا حساب عهده
     */
    private String fromIban;

    /**
     * مبلغ چك
     */
    private Long amount;

    /**
     * تاريخ خورشيدي سر رسيد چك
     */
    private String dueDate;

    /**
     * تاریخ سررسید به فرمت ISO8601
     */
    private String dueDateUtc;

    /**
     * شرح چك
     */
    private String description;

    /**
     * كد بانك عهده چك
     */
    private String bankCode;

    /**
     * كد شعبه عهده چك
     */
    private String branchCode;

    /**
     * نوع ارز چك
     */
    private String currency;

    /**
     * نوع چك
     * ١ :عادي
     * ٢ :بانكي
     * ٣ :چك تضميني(رمزدار)
     * ٤ :چك موردي
     */
    private CartableChequeType chequeType;

    private String localizedChequeType;

    /**
     * وضعيت چك
     * ١ :ثبت شده
     * ٢ :نقد شده
     * ٣ :باطل شده
     * ٤ :برگشت خورده
     * ٥ :بخشي برگشت خورده
     * ٦ :در انتظار امضا ضامن
     */
    private CartableChequeStatus chequeStatus;

    private String localizedChequeStatus;

    /**
     * وضعيت ضمانت چك
     * ١ :اين چك فاقد ضمانت مي باشد
     * ٢ :فرآيند ضمانت در جريان است
     * ٣ :فرآيند ضمانت با درخواست ذينفع ناتمام خاتمه
     * يافته است
     * ٤ :فرآيند ضمانت اتمام و همه ضامن ها ضمانت
     * كرده اند
     * ٥ :فرآيند ضمانت اتمام و برخي ضامن ها ضمانت را
     * رد كرده اند
     */
    private CartableGuaranteeStatus guaranteeStatus;

    private String localizedGuaranteeStatus;
    /**
     * ٠ :چك مسدود نشده است
     * ١ :مسدود موقت ميباشد
     * ٢ :مسدود دائم ميباشد
     * ٣ :چك رفع مسدودي شده است
     */
    private GuaranteeBlockStatus blockStatus;

    /**
     * true :چك lock مي باشد.
     */
    private Boolean locked;

    /**
     * false :ذينفع ديگري ندارد.
     * true :چك ذينفع ديگري نيز دارد.
     */
    private Boolean shared;

    private String branchName;
    private String branchAddress;

//    private String checkoutRequestId;

    private String reason;
    private String localizedReason;
    private String toIban;
    private List<ChequeOwner> chequeOwner;

    @Data
    public static class ChequeOwner {
        private String idCode;
        private Integer idType;
        private String name;
    }
}
