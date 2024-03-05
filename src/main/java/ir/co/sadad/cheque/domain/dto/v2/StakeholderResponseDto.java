package ir.co.sadad.cheque.domain.dto.v2;

import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.domain.enums.StakeholderRole;
import lombok.Data;

@Data
public class StakeholderResponseDto {
    private String sayadId;
    private String name;
    private String identifier;
    private String identifierType;
    private Integer agent;
    private String grantorIdentifier;
    private String grantorName;
    private String grantorType;
    private StakeholderRole role;
    private String certificationKeyId;
    private String shahabId;
    private ChequeIssue chequeIssue;
    private ChequeTransfer chequeTransfer;
}
