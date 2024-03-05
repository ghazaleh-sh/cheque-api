package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.TokenType;
import ir.co.sadad.cheque.domain.enums.UserCertificationStatus;
import lombok.Data;

@Data
@Schema(name = "خروجی سرویس استعلام گواهی کاربر", description = "این خروجی قبل از سرویس صدور چک فراخوانی خواهد شد")
public class UserCertificationResponseDto {

    @Schema(name = "کلید سند کاربر")
    private String certificateKeyId;

    @Schema(name = "نام دستگاه کاربر")
    private String device;

    @Schema(name = "شناسه دستگاه کاربر")
    private String deviceId;

    @Schema(name = "نوع محصول - معمولا نماد است")
    private TokenType productType;

    @Schema(name = "شناسه محصول استفاده شده")
    private String productId;

    @Schema(name = "وضعیت اطلاعات گواهی")
    private UserCertificationStatus status;
}
