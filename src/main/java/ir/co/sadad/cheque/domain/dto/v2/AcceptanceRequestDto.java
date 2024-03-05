package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AcceptanceRequestDto {

    @Schema(title = "نوع عملیات", required = true)
    @Pattern(regexp = "APPROVE|REJECT", message = "acceptance.action.not.valid")
    private String action;

    @Schema(title = "توضیحات")
    @Size(max = 25, message = "acceptance.description.length.invalid")
    private String description;
}
