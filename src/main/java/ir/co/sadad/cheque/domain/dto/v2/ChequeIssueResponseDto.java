package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * response of issue service
 */
@Data
public class ChequeIssueResponseDto {
    private List<UserDto> guarantors;
    private String sayadId;
    private String serialNumber;
    private String seriNumber;
    private String amount;
    private String settlementDate;
    private String responseTime;
    private String description;
    private String reason;
    private List<UserDto> receivers;
    private List<UserDto> signers;

}
