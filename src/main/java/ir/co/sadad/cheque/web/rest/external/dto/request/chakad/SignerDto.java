package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * dto for signer of cheques
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignerDto {
    /**
     * نام امضاكننده حقيقي
     * require
     */
    private String name;

    /**
     * نوع شناسه امضاكننده حقيقي
     * ١ :مشتري حقيقي
     * ٣ :اتباع بيگانه حقيقي
     * require
     */
    private Integer identifierType;


    /**
     * كد ملي/ اتباع خارجي امضاكننده حقيقي
     * با توجه به identifierType اعتبار سنجي مي شود،
     * مشتري حقيقي: كد ملي (١٠رقم)
     * اتباع بيگانه: شماره فراگير (٨ الي١٥ رقم)
     * require
     */
    private String identifier;

    /**
     * امضا به منزله مهرحقوقي مي باشد؟
     * ١ :مي باشد
     * ٠ :نمي باشد
     * هر حساب حقوقي حتما بايستي داراي يك كد ١
     * باشد
     * require
     */
    private String legalStamp;

    /**
     * وكيل/ نماينده
     * ١ :بله ٢ :خير
     * require
     */
    private Integer agent;

    /**
     * نوع شناسه مجوز دهنده براي امضا
     * درصورتيكه فيلد agent با "بله" مقداردهي شود پر
     * كردن اطلاعات مجوزدهنده اجباري مي باشد.
     * ١ :مشتري حقيقي ٢ :مشتري حقوقي
     * ٣ :اتباع بيگانه حقيقي ٤ :اتباع بيگانه حقوقي
     */
    private Integer grantorType;

    /**
     * كد ملي/ شناسه ملي/ شناسه اتباع بيگانه مجوزدهنده
     * مشتري حقيقي :كد ملي (١٠رقم)
     * مشتري حقوقي :شناسه ملي شركت (١١رقم)
     * اتباع بيگانه: شماره فراگير (٨ الي١٥ رقم)
     */
    private String grantorIdentifier;

    /**
     * نام مجوز دهنده براي امضا
     */
    private String grantorName;


}
