package ir.co.sadad.cheque.domain.dto;

import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeCustomerDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeItemDto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class BouncedChequeResponseDto implements Serializable {

    private List<ChequeItemDto> chequeInfo;
    private ChequeCustomerDto customerInfo;
    private Date requestDateTime;
    private String requestIdentifier;
}
