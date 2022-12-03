package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ChakadChequeInfoDto {
    /**
     * ليست دريافت كنندگان چك
     * require
     */
    @Valid
    @NotNull(message = "chakad.error.receivers.is.mandatory")
    private List<ChakadReceiverDto> receivers;

    /**
     * ليست امضاكنندگان چك
     * require
     */
    @Valid
    @NotNull(message = "chakad.error.signers.is.mandatory")
    private List<ChakadSignerDto> signers;

    /**
     * ليست ضمانت كنندگان چك
     * require
     */
    private List<ChakadGuarantorDto> guarantors;

    /**
     * شناسه صياد چك مورد نظر
     * require
     */
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;


    /**
     * شماره سريال چك؛ حداكثر٢٠ رقم
     * require
     */
    @Pattern(regexp = "[0-9]{6,20}$", message = "chakad.error.serial.number.length.invalid")
    @NotNull(message = "chakad.error.serial.number.is.mandatory")
    private String serialNumber;


    /**
     * حداكثر ١٠ كاراكتر، مثال ٦٥-ب
     * require
     */
    @Size(min = 4, max = 10, message = "chakad.error.seri.number.length.invalid")
    @NotNull(message = "chakad.error.seri.number.is.mandatory")
    private String seriNumber;

    /**
     * مبلغ چك،
     * حداكثر ١٦ رقم فقط شامل اعداد
     * require
     */
    @Pattern(regexp = "[0-9]{1,16}$", message = "chakad.error.amount.length.invalid")
    @NotNull(message = "chakad.error.amount.is.mandatory")
    private String amount;

    /**
     * تاريخ خورشيدي سررسيد چك
     * رشته كاركتري به طول ٨ در ساختار yyyyMMdd
     * require
     */
    @Size(min = 8, max = 8, message = "chakad.error.settlement.date.length.invalid")
    @NotNull(message = "chakad.error.settlement.date.is.mandatory")
    private String settlementDate;

    /**
     * شرح چك
     * require
     */
    @Size(max = 250, message = "chakad.error.description.length.invalid")
    @NotNull(message = "chakad.error.description.is.mandatory")
    private String description;

    /**
     * بابت
     * اين فيلد متناسب با مبلغ چك تعيين مي شود.
     */
    private String reason;
}
