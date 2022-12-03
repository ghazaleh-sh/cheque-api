package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * dto for guarantors
 */
@Data
public class GuarantorDto {

    /**
     * نام مشتري ضمانت كننده
     * require
     */
    private String name;

    /**
     * كد شناسايي ضمانت كننده
     * با توجه بهidentifierType اعتبار سنجي مي شود؛
     * مشتري حقيقي :كد ملي (١٠رقم)
     * مشتري حقوقي :شناسه ملي شركت (١١رقم)
     * اتباع بيگانه حقيقي و حقوقي: شماره فراگير: (٨
     * الي١٥ رقم)
     * require
     */
    private String identifier;

    /**
     * نوع كد شناسايي:
     * ١ :مشتري حقيقي
     * ٢ :مشتري حقوقي
     * ٣ :اتباع بيگانه حقيقي
     * ٤ :اتباع بيگانه حقوقي
     * require
     */
    private Integer identifierType;
}
