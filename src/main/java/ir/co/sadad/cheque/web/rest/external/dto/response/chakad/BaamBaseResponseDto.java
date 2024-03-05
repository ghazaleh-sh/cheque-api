package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.List;

/**
 * response of baambase userCertification service
 */
@Data
public class BaamBaseResponseDto<T> {

    private String message;
    private T responseBody;
    private Integer resultCode;

}
