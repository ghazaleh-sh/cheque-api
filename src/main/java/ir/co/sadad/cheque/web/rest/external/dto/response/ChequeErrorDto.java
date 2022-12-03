package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ChequeErrorDto {

    private String number;
    private String code;
    private String message;
    private String timestamp;
    private List<ErrorDataDto> errors;
}
