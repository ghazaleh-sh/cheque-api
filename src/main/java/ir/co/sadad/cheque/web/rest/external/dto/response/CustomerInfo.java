package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerInfo {

    private int customerType;
    private String nationalIdentifier;
}
