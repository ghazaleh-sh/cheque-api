package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * issue cheque dto
 */
@Data
public class IssueChequeRequestDto {

    /**
     * اطلاعات لازم براي كشيدن چك
     */
    private ChequeDto cheque;

    /**
     * اطلاعات امضاء
     */
    private Sign sign;

}
