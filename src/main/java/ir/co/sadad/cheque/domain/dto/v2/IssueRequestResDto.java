package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.TokenType;
import lombok.Data;

@Schema(title = "خروجی سرویس درخواست صدور", description = "خروجی سرویس که درخواست صدور را آماده میکند تا کلاینت آن را امصا نماید .")
@Data
public class IssueRequestResDto {

    @Schema(title = "شناسه مدرک کاربر ")
    private String certificateKeyId;

    @Schema(title = "نوع گواهی")
    private TokenType productType;

    @Schema(title = "کد گواهی")
    private Integer productId;

    @Schema(title = "اطلاعات tbs که باید امضا شود ")
    private String dataToBeSign;

    @Schema(title = "شناسه صیاد")
    private String sayadId;
}
