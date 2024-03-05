package ir.co.sadad.cheque.domain.dto.v2;

import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.enums.IssueStatus;
import lombok.Data;
import java.util.Date;

/**
 * response for CRUD chequeIssue service
 */
@Data
public class IssueResponseDto {
    private String sayadId;
    private String serialNumber;
    private String seriNumber;
    private String amount;
    private String settlementDate;
    private String description;
    private Date issueDate;
    private String transactionId;
    private String tbs;
    private Boolean signed;
    private IssueStatus issueStatus;
    private String signatureValue;
    private ChequeReason chequeReason;
}
