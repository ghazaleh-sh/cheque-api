package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChakadAcceptRequestDto {

    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    @Schema(title = "شناسه صیاد چک" , required = true)
    String sayadId;

    /**
     * false: رد چک
     * true: قبول چک
     */
    @NotNull(message = "chakad.error.accept.is.mandatory")
    @Schema(title = "وضعیت تایید چک" , required = true)
    Boolean accept;

    /**
     * 1: مشتری حقیقی
     * 2: مشتری حقوقی
     * 3: تباع بیگانه حقیقی
     * 4: اتباع بیگانه حقوقی
     */
    @NotNull(message = "chakad.error.receiver.id.type.is.mandatory")
    @Schema(title = "نوع شناسه تایید کننده" , required = true)
    Integer identifierType;

    /**
     * با توجه به identifierType اعتبار سنجی می شود.
     * مشتری حقیقی: کد ملی (10رقم)
     * مشتری حقوقی: شناسه ملی شرکت (11رقم)
     * اتباع بیگانه: شماره فراگیر (8 الی15 رقم)
     */
    @NotNull(message = "chakad.error.receiver.id.type.is.mandatory")
    @Schema(title = "کد ملی/ شناسه ملی/ اتباع خارجی تایید کننده" , required = true)
    String identifier;

    /**
     * 1- مشتری حقیقی
     * 3-اتباع بیگانه حقیقی
     */
    @Schema(title = "نوع شناسه  ثبت کننده تایید")
    Integer agentType;

    @Schema(title = "کد ملی/ شناسه ملی/ اتباع خارجی ثبت کننده تایید")
    String agentIdentifier;

    @Schema(title = "توضیح وضعیت چک")
    String description;

}
