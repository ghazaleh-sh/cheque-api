package ir.co.sadad.cheque.web.rest.external.dto.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class ChequebookResponseDto {

    private int id;
    private long issueDateTime;
    private List<Object> chequeHistory;
    private String customerProfileId;
    private String firstChequeNumber;
    private String lastChequeNumber;
    private int notPresentedChequeNo;
    private int branchCode;

    @SerializedName("chequebookid")
    private int chequeBookId;

    private String accountNo;
    private String name;
}
