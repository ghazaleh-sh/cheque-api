package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * داده های دریافت کنندگان چک
 */
@Data
public class ChakadReceiverDto {

    /**
     * require
     * نام مشتري دريافت كننده
     */
    @Size(max = 250, message = "chakad.error.receiver.name.length.invalid")
    @NotNull(message = "chakad.error.receiver.name.is.mandatory")
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
    @NotNull(message = "chakad.error.receiver.id.code.is.mandatory")
    private String identifier;

    /**
     * نوع كد شناسايي:
     * ١ :مشتري حقيقي
     * ٢ :مشتري حقوقي
     * ٣ :اتباع بيگانه حقيقي
     * ٤ :اتباع بيگانه حقوقي
     * require
     */
    @NotNull(message = "chakad.error.receiver.id.type.is.mandatory")
    private Integer identifierType;

}
