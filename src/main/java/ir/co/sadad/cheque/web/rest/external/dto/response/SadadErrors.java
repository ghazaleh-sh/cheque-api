package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class SadadErrors {

    private String domain;
    private String code;
    private String message;
    private String timestamp;
    private List<ErrorDataDto> errors;
}
