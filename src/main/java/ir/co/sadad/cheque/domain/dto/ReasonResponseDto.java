package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(title = "خروجی آبجکت بابت")
public class ReasonResponseDto implements Serializable {

    @Schema(title = "شناسه بابت")
    private Long id;

    @Schema(title = "نوع بابت")
    private String reasonType;

    @Schema(title = "کد بابت")
    private String reasonCode;

    @Schema(title = "عنوان بابت")
    private String reasonTitle;

    @Schema(title = "اولویت")
    private Integer priority;

}
