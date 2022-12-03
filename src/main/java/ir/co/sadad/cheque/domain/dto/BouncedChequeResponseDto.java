package ir.co.sadad.cheque.domain.dto;

import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeCustomer;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeItem;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class BouncedChequeResponseDto implements Serializable {

    private List<ChequeItem> chequeInfo;
    private ChequeCustomer customerInfo;
    private Date requestDateTime;
    private String requestIdentifier;
}
