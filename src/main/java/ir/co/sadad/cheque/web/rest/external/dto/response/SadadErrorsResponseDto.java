package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class SadadErrorsResponseDto {

    private SadadErrors error;
    private Object response;
}
