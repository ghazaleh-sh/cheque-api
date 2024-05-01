package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * determine type of request that user sent to chakad services .
 */
@AllArgsConstructor
@Getter
public enum RequestType {
    ACTIVATION(1),
    UPDATE_PROFILE(2);

    private final Integer code;
}
