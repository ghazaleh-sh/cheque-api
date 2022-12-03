package ir.co.sadad.cheque.web.rest.external.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AllocatingEstelamRequestDto implements Serializable {

    @NotNull(message = "cheque-api.error.estelamType.field.is.mandatory")
    private String estelamType;

    private Integer pageNumber = 1;
    private String identifier;
    private Integer branchId;

    @NotNull(message = "cheque-api.error.iban.field.is.mandatory")
    private String iban;

    private String chequeId;
    private Long createDateFrom;
    private Long createDateTo;
    private Long settlementDateFrom;
    private Long settlementDateTo;
    private Integer debtorBank;
    private Integer chequeStatus;
    private Integer allocateStatus;
    private Integer pageSize;
}
