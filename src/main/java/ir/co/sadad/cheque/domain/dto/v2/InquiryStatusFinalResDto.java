package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.ActivationResponseStatus;
import lombok.Data;

@Data
@Schema(title = "خروجی سرویس استعلام وضعیت")
public class InquiryStatusFinalResDto {

    /**
     * كد شناسايي
     * مشتري حقيقي: كد ملي
     * مشتري حقوقي: شناسه ملي شركت
     * اتباع بيگانه حقيقي و حقوقي: شماره فراگير
     */
    @Schema(title = "کد شناسایی")
    private String userId;

    /**
     * وضعيت فعالسازي كاربر در سامانه:
     * ١ :كاربر در سامانه چكاد فعال شده است
     * ٢ :كاربر در سامانه چكاد فعال نشده است
     */
    @Schema(title = "وضعیت فعالسازی ")
    private ActivationResponseStatus status;

    /**
     * كد شهاب مشتري
     */
    @Schema(title = "کد شهاب ")
    private String shahabId;
}
