package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.validation.ChequeReason;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Schema(title = "درخواست صدور چک", description = "ورودی های سرویس درخواست صدور چک در ورژن دوم سیستم چک الکترونیک")
@ChequeReason(value = {"reason", "amount"})
public class IssueRequestReqDto {
    @Valid
    @Schema(title = "لیست دریافت کنندگان")
    private List<UserDto> receivers;

    @Valid
    @Schema(title = "لیست ضامن ها")
    private List<UserDto> guarantors;

    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    @Schema(title = "شناسه صیاد", required = true)
    private String sayadId;

    @Pattern(regexp = "[0-9]{6,20}$", message = "chakad.error.serial.number.length.invalid")
    @NotNull(message = "chakad.error.serial.number.is.mandatory")
    @Schema(title = "شماره سریال چک", required = true)
    private String serialNumber;

    @Size(min = 4, max = 10, message = "chakad.error.seri.number.length.invalid")
    @NotNull(message = "chakad.error.seri.number.is.mandatory")
    @Schema(title = "شماره سری چک", description = "مثال ٦٥-ب", required = true)
    private String seriNumber;

    @Pattern(regexp = "[0-9]{1,16}$", message = "chakad.error.amount.length.invalid")
    @NotNull(message = "chakad.error.amount.is.mandatory")
    @Schema(title = "مبلغ چک", required = true)
    private String amount;

//    @Size(min = 8, max = 8, message = "chakad.error.settlement.date.length.invalid")
    @NotNull(message = "chakad.error.settlement.date.is.mandatory")
    @Schema(title = "تاریخ خورشیدی سررسید چک", required = true)
    private String settlementDate;

    @Size(max = 250, message = "chakad.error.description.length.invalid")
    @NotNull(message = "chakad.error.description.is.mandatory")
    @Schema(title = "توضیحات", required = true)
    private String description;

    @Schema(title = "بابت", description = "فیلد بابت متناسب با مبلغ چک میشود", required = true)
    private String reason;
}
