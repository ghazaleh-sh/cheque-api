package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerIdType {
    INDIVIDUAL("INDIVIDUAL", 1),
    LEGAL("LEGAL", 2),
    SHAHAB_FOREIGN_INDIVIDUAL("SHAHAB_FOREIGN_INDIVIDUAL", 2),
    FOREIGN_INDIVIDUAL("FOREIGN_INDIVIDUAL", 3),
    SHAHAB_LEGAL("SHAHAB_LEGAL", 3),
    FOREIGN_LEGAL("FOREIGN_LEGAL", 4);


    private final String key;
    private final Integer value;

}
