package ir.co.sadad.cheque.domain.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocatingInquiryRequestDto implements Serializable {

    @NotNull(message = "cheque-api.error.estelamType.field.is.mandatory")
    private String estelamType;

    private Integer pageNumber = 1;
    private String identifier;
    private Integer branchId;

    @NotNull(message = "cheque-api.error.iban.field.is.mandatory")
    private String iban;

    private String chequeId;

    @Setter(AccessLevel.NONE)
    private Long createDateFrom;

    @Setter(AccessLevel.NONE)
    private Long createDateTo;

    @Setter(AccessLevel.NONE)
    private Long settlementDateFrom;

    @Setter(AccessLevel.NONE)
    private Long settlementDateTo;

    private Integer debtorBank;
    private Integer chequeStatus;
    private Integer allocateStatus;
    private Integer pageSize;
}
