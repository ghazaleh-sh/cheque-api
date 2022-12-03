package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ChequeItem implements Serializable {

    private Long amount;
    private Integer bankCode;
    private Long bouncedAmount;
    private String bouncedBranchName;
    private String bouncedDate;
    private List<Integer> bouncedReasons;
    private String bouncedBranchCode;
    private String originBranchCode;
    private Integer channelType;
    private String currencyCode;
    private Float currencyRate;
    private Integer chequeOwnerType;
    private String dueDate;
    private String iban;
    private Long chequeIdentifier;
    private String originBranchName;
    private String chequeSerial;
}
