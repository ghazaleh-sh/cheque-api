package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
public class ChakadIssueRequestDto {
    /**
     * اطلاعات لازم براي كشيدن چك
     */
    @Valid
    private ChakadChequeInfoDto cheque;

    /**
     * اطلاعات امضاء
     */
    @Valid
    private ChakadSignDto sign;
}
