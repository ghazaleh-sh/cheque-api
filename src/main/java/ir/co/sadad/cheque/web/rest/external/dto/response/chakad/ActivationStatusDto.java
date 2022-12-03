package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * dto for status of activation of customers
 *
 * @see InquiryStatusResponseDto
 */
@Data
public class ActivationStatusDto {

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
