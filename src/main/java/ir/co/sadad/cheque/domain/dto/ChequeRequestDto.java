package ir.co.sadad.cheque.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChequeRequestDto implements Serializable {

    @JsonProperty
    @Pattern(regexp = "01\\d{11}", message = "cheque-api.error.invalid-account-type")
    @NotNull
    private String accountNo;
}
