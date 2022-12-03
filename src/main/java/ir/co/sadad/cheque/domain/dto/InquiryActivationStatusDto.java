package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

@Data
public class InquiryActivationStatusDto {
    /**
     * وضعيت فعالسازي كاربر در سامانه:
     * ١ :كاربر در سامانه چكاد فعال شده است
     * ٢ :كاربر در سامانه چكاد فعال نشده است
     */
    private Integer status;

    /**
     * كد شهاب مشتري
     */
    private String shahabId;

    /**
     * كد شناسايي
     * مشتري حقيقي: كد ملي
     * مشتري حقوقي: شناسه ملي شركت
     * اتباع بيگانه حقيقي و حقوقي: شماره فراگير
     */
    private String idCode;


    /**
     * نوع كد شناسايي :
     * ١ :مشتري حقيقي
     * ٢ :مشتري حقوقي
     * ٣ :اتباع بيگانه حقيقي
     * ٤ :اتباع بيگانه حقوقي
     */
    private Integer idType;
}
