package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class ApiGeneralResponseDto<T> {

    private SadadErrors error;
    private T response;
}
