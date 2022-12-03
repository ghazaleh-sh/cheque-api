package ir.co.sadad.cheque.web.rest.external.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
public class AccountDto {

    private String id;
    private String type;
    private String subType;
    private String state;
    private String cif;
    private int branchCode;
    private Date openDate;
    private Date freezDate;
    private String sicCode;
    private String backupNumber;
    private long freezAmount;
    private int freezBranchCode;
    private int rate;
    private long availableBalance;
    private long usableBalance;
    private long currentBalance;
    private String profitAccountNumber;
    private Date lastTransactionDate;
    private String iban;
    private String firstName;
    private String lastName;
    private String customerType;
    private Date closeDate;
    private String prefer;
    private String ssn;
    private String branchName;
    private BalanceTypeEnum balanceType;
    private int priority;
    private CurrencyEnum currency;
    private Boolean showBalance;
    private Boolean showName;
    private String specs;

    @JsonProperty("default")
    private Boolean _default;
}
