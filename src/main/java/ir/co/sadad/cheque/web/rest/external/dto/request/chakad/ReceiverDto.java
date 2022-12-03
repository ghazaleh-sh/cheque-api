package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * receiver dto
 * <pre>
 *     info of receiver of cheque
 * </pre>
 */
@Data
public class ReceiverDto {


    /**
     * require
     * نام مشتري دريافت كننده
     */
    private String name;

    /**
     * كد شناسايي دريافت كننده
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
