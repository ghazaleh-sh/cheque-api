package ir.co.sadad.cheque.web.rest.external.dto.response;

import ir.co.sadad.cheque.domain.enums.BouncedReason;
import ir.co.sadad.cheque.domain.enums.ChannelType;
import ir.co.sadad.cheque.domain.enums.ChequeOwnerType;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ChequeItemDto implements Serializable {

    private Long amount;
    private Integer bankCode;
    private Long bouncedAmount;
    private String bouncedBranchName;
    private String bouncedDate;
    private List<BouncedReasonDto> bouncedReasons;
    private String bouncedBranchCode;
    private String originBranchCode;
    private String channelType;
    private Integer channelTypeCode;
    private String currencyCode;
    private Float currencyRate;
    private String chequeOwnerType;
    private Integer chequeOwnerTypeCode;
    private String dueDate;
    private String iban;
    private Long chequeIdentifier;
    private String originBranchName;
    private String chequeSerial;
}
