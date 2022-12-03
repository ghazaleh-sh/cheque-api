package ir.co.sadad.cheque.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeRequestResponse implements Serializable {

    @JsonProperty("STATUS")
    private int status;

    @JsonProperty("ERROR DESCRIPTION")
    private String errorDescription = "";

    @JsonProperty("REMAINED CHEQUE")
    private String remainedCheque;

    @JsonProperty("DELAYED FACILITY")
    private List<HashMap<String, Object>> delayedFacility;

    @JsonProperty("RETURNED CHEQUE")
    private List<HashMap<String, Object>> returnedCheque;

    private String iban;
    private String branchCode;

    private String accountNo;

    @JsonProperty("SUCCESS")
    private ChequeInfo success;
}
