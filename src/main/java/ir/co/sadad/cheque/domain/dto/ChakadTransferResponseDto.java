package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "خروجی انتقال موفق")
public class ChakadTransferResponseDto {

    /**
     * ١: انتقال انجام شد.
     *  ٢: انتقال ثبت شد. انجام انتقال نياز به تائيد سايرين دارد
     * ٣: تائيد ثبت شد. با ثبت تائيد سايرين انتقال انجام خواهد شد.
     * ٤: با تائيد همه ذينفعان انتقال انجام شد.
     * ٥: انتقال با موفقيت لغو شد. انتقال جديد نياز به تائيد همه خواهد داشت.
     */
    @Schema(title = "نتيجه تراكنش")
    private Integer resultCode;
}
