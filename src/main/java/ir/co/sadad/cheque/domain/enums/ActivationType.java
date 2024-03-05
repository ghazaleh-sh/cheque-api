package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivationType {
    ACTIVATION(1, "ACTIVATION"),
    UPDATE(2, "UPDATE");

    private final Integer value;
    private final String description;

}
