package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * info for cartable
 */
@Data
public class CartableDto {

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
    private Integer currency;

    /**
     * نوع چك
     * ١ :عادي
     * ٢ :بانكي
     * ٣ :چك تضميني(رمزدار)
     * ٤ :چك موردي
     */
    private Integer chequeType;

    /**
     * ١ :چك كاغذي
     * ٢ :چك ديجيتال
     */
    private Integer chequeMedia;

    /**
     * وضعيت چك
     * ١ :ثبت شده
     * ٢ :نقد شده
     * ٣ :باطل شده
     * ٤ :برگشت خورده
     * ٥ :بخشي برگشت خورده
     * ٦ :در انتظار امضا ضامن
     */
    private Integer chequeStatus;

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
    private Integer guaranteeStatus;

    /**
     * ٠ :چك مسدود نشده است
     * ١ :مسدود موقت ميباشد
     * ٢ :مسدود دائم ميباشد
     * ٣ :چك رفع مسدودي شده است
     */
    private Integer blockStatus;

    /**
     * ٠ :lockنقد كردن بر روي چك وجود ندارد.
     * ١ :چك lock مي باشد.
     */
    private Integer locked;

    /**
     * false :ذينفع ديگري ندارد.
     * true :چك ذينفع ديگري نيز دارد.
     */
    private Boolean shared;
}
