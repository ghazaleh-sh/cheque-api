package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.TokenType;
import lombok.Data;

@Data
@Schema(name = "خروجی سرویس تی بی اس", description = "این خروجی پس از سرویس activation_tbs تولید خواهد شد")
public class TbsActivationResponseDto {
    @Schema(name = "کلید سند کاربر")
    private String certificateKeyId;

    @Schema(name = "نام دستگاه کاربر")
    private String device;

    @Schema(name = "شناسه دستگاه کاربر")
    private String deviceId;

    @Schema(name = "اطلاعات امضا شده")
    private String dataToBeSign;

    @Schema(name = "نوع محصول - معمولا نماد است")
    private TokenType productType;

    @Schema(name = "شناسه محصول استفاده شده")
    private String productId;
}
