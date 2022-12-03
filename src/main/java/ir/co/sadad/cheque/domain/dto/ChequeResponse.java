package ir.co.sadad.cheque.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeResponse implements Serializable {

    private String accountNo;
    private String iban;
    private String branchCode;
    private String identifier;
    private List<ChequeData> chequeDataList;
}
