package ir.co.sadad.cheque.domain.dto;

import ir.co.sadad.cheque.validation.NationalCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@NationalCode(value = {"idCodeCustomer", "idTypeCustomer"})
public class ChakadDeactivationRequestDto {


    @NotNull(message = "chakad.error.code.customer.is.mandatory")
    private String idCodeCustomer;

    @NotNull(message = "chakad.error.type.customer.is.mandatory")
    private String idTypeCustomer;

    private String idCodeOrganization;

    private Integer idTypeOrganization;

    private Integer legalStamp;
}
