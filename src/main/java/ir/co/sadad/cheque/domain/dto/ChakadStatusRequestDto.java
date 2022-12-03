package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ChakadStatusRequestDto {

    @Valid
    private List<CustomerDto> customers;

}
