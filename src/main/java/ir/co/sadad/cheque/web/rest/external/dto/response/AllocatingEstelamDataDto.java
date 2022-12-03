package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class AllocatingEstelamDataDto implements Serializable {

    private Long chequeId;
    private Integer branchId;
    private String identifier;
    private String iban;
    private String chequeNumber;
    private Integer amount;
    private Long createDate;
    private Long settlementDate;
    private Long depositeDate;
    private Integer debtorBank;
    private Integer debtorBranch;
    private Integer chequeStatus;
    private Integer allocateStatus;
}
