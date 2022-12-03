package ir.co.sadad.cheque.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeInfo {

    private String accountNo;
    private String iban;
    private String branchCode;
}
