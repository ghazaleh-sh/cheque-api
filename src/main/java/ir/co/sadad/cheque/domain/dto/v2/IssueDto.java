package ir.co.sadad.cheque.domain.dto.v2;

import ir.co.sadad.cheque.domain.entity.ChequeReason;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueDto {
    private String sayadId;
    private String serialNumber;
    private String seriNumber;
    private String amount;
    private String settlementDate;
    private String description;
    private String transactionId;
    private String tbs;
    private String signatureValue;
    private ChequeReason chequeReason;
}
