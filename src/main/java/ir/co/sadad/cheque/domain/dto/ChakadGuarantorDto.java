package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * داده هاي ضمانت كنندگان چک
 */
@Data
public class ChakadGuarantorDto {
    /**
     * نام مشتري ضمانت كننده
     * require
     */
    @Size(max = 250, message = "{name.max.size}")
    @NotNull
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
    @NotNull
    private String identifier;

    /**
     * نوع كد شناسايي:
     * ١ :مشتري حقيقي
     * ٢ :مشتري حقوقي
     * ٣ :اتباع بيگانه حقيقي
     * ٤ :اتباع بيگانه حقوقي
     * require
     */
    @NotNull
    private Integer identifierType;
}
