package ir.co.sadad.cheque.web.rest.external.dto.request;

import ir.co.sadad.cheque.web.rest.external.dto.response.CustomerInfo;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BouncedChequeRequestDto implements Serializable {

    private CustomerInfo customerInfo;
    private String requestIdentifier;
}
