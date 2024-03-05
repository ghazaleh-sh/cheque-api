package ir.co.sadad.cheque.web.rest.external.dto.response;

import ir.co.sadad.cheque.domain.enums.CustomerType;
import lombok.Data;

@Data
public class ChequeCustomerDto {

    private String firstName;
    private String lastName;
    private String nationalIdentifier;
    private String customerType;
    private Integer customerTypeCode;
}
