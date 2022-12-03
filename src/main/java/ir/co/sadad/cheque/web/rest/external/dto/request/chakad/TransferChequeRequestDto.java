package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import java.util.List;

/**
 * dto for send transfer request to chakad services
 */
@Data
public class TransferChequeRequestDto {


    /**
     * اطلاعات لازم براي انتقال چك
     */
    private TransferDto transfer;

    /**
     * اطلاعات امضاء
     */
    private Sign sign;
}
