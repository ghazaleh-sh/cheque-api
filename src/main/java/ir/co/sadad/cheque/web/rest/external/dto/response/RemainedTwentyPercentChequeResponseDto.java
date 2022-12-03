package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.Date;
import lombok.Data;

@Data
public class RemainedTwentyPercentChequeResponseDto {

    private String alertCode;
    private String MSGNumber;
    private String description;
    private String branchCode;
    private String firstChequeNumber;
    private String lastChequeNumber;
    private Long issueDateTime;
    private String notPresentedChequeNo;
    private String accountno;
    private int status;
}
