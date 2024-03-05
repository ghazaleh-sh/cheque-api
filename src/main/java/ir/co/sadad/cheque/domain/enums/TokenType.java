package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * tokenType
 */
@Getter
@RequiredArgsConstructor
public enum TokenType {
    NAMAD(1);

    private final Integer value;
}
