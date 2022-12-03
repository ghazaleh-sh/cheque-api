package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class GuarantorGuaranteesListDto {

    private String contractDate;
    private String loanNumber;
    private String nationalIdentifier;
    private String legalIdentifier;
    private String customerType;
    private String firstName;
    private String lastName;
    private String bankCode;
    private String branchCode;
    private String branchName;
    private String contractTypeDescription;
    private String lastDueDate;
    private int originalAmount;
    private int interestAmount;
    private int installmentAmount;
    private int debtBalance;
    private int pastDueBalance;
    private int overDueBalance;
    private int dubtFullBalance;
    private int totalDelayedBalance;
    private String loanStatus;
    private int penaltyAmount;
    private int defermentPenalty;
}
