package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * داده هاي امضاكننده چك مبتني بر شرايط برداشت از حساب
 */
@Data
public class ChakadSignerDto {
    /**
     * نام امضاكننده حقيقي
     * require
     */
    @Size(max = 250, message = "chakad.error.signer.name.length.invalid")
    @NotNull(message = "chakad.error.signer.name.is.mandatory")
    private String name;

    /**
     * نوع شناسه امضاكننده حقيقي
     * ١ :مشتري حقيقي
     * ٣ :اتباع بيگانه حقيقي
     * require
     */
    @NotNull(message = "chakad.error.signer.id.type.is.mandatory")
    private Integer identifierType;


    /**
     * كد ملي/ اتباع خارجي امضاكننده حقيقي
     * با توجه به identifierType اعتبار سنجي مي شود،
     * مشتري حقيقي: كد ملي (١٠رقم)
     * اتباع بيگانه: شماره فراگير (٨ الي١٥ رقم)
     * require
     */
    @NotNull(message = "chakad.error.signer.id.code.is.mandatory")
    private String identifier;

    /**
     * امضا به منزله مهرحقوقي مي باشد؟
     * ١ :مي باشد
     * ٠ :نمي باشد
     * هر حساب حقوقي حتما بايستي داراي يك كد ١
     * باشد
     * require
     */
    @NotNull(message = "chakad.error.legal.stamp.is.mandatory")
    private String legalStamp;

    /**
     * وكيل/ نماينده
     * ١ :بله ٢ :خير
     * require
     */
    @NotNull(message = "chakad.error.agent.is.mandatory")
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
    @Size(max = 250, message = "{granter.name.max.size}")
    private String grantorName;
}
