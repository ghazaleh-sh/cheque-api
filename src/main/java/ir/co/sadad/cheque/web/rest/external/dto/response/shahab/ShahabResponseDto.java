package ir.co.sadad.cheque.web.rest.external.dto.response.shahab;

import lombok.Data;


@Data
public class ShahabResponseDto {
    /**
     * success response of service
     */
    private ShahabDataResponseDto response;

    private ShahabErrorType error;
}
