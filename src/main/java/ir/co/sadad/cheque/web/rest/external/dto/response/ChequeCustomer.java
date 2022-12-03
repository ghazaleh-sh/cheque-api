package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class ChequeCustomer {

    private String firstName;
    private String lastName;
    private String nationalIdentifier;
    private Integer customerType;
}
