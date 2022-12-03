package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
public class ChakadTransferRequestDto {

    /**
     * اطلاعات لازم براي انتقال چك
     */
    @Valid
    private ChakadTransferInfoDto transfer;

    /**
     * اطلاعات امضاء
     */
    @Valid
    private ChakadSignDto sign;
}
