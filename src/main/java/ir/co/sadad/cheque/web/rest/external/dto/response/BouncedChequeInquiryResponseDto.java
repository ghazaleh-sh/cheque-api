package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class BouncedChequeInquiryResponseDto {

    private List<ChequeItem> chequeInfo;
    private ChequeCustomer customerInfo;
    private Date requestDateTime;
    private String requestIdentifier;
}
