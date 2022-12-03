package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

/**
 * dto for response of transfer cheques
 */
@Data
public class TransferChequeResponseDto {


    /**
     * نتيجه تراكنش:
     * ١ :انتقال انجام شد.
     * ٢ :انتقال ثبت شد. انجام انتقال نياز به تائيد سايرين
     * دارد.
     * ٣ :تائيد ثبت شد. با ثبت تائيد سايرين انتقال انجام
     * خواهد شد.
     * ٤ :با تائيد همه ذينفعان انتقال انجام شد.
     * ٥ :انتقال با موفقيت لغو شد. انتقال جديد نياز به
     * تائيد همه خواهد داشت.
     */
    private Integer resultCode;
}
