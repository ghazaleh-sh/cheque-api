package ir.co.sadad.cheque.web.rest.external.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.co.sadad.cheque.web.rest.external.dto.response.AccountOwner;
import java.util.List;
import lombok.Data;

@Data
public class ChequeRegisterDto {

    @JsonProperty("IBAN")
    private String iban;

    private int accountType;
    private int sheetCount;
    private List<AccountOwner> accountOwner;
}
