package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

@Data
public class SelectedAccountDto {

    private String accountNo;
    private String iban;
    private String branchCode;
    private String identifier;
}
