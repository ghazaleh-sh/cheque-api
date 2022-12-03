package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class SamatInquiryEntryDto {

    private String contractDate;
    private String requestIdentifier;
    private String bankCode;
    private String branchCode;
    private String branchName;
    private String requestType;
    private String lastDueDate;
    private String currencyCode;
    private long originalAmount;
    private long interestAmount;
    private long debtBalance;
    private long pastDueBalance;
    private long overDueBalance;
    private long doubtfulBalance;
    private Long destroyAmount;
    private long defermentPenalty;
    private long latePaymentPenalties;
    private long obligationBalance;
    private String contractType;
    private String supplySource;
    private String moratoriumType;
    private String moratoriumDate;
    private String loanTarget;
    private String consumptionPlaceCode;
    private String category;
    private String mainIndividualFirstName;
    private String mainIndividualLastNameOrLegalName;
    private String mainNationalIdentifier;
    private String mainLegalIdentifier;
    private String contraVoucherNonRegistered;
}
