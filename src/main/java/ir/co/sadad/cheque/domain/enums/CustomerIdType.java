package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerIdType {
    INDIVIDUAL("INDIVIDUAL", 1),
    CORPORATE("CORPORATE", 2),
    SHAHAB_FOREIGN_INDIVIDUAL("SHAHAB_FOREIGN_INDIVIDUAL", 2),
    FOREIGN_INDIVIDUAL("FOREIGN_INDIVIDUAL", 3),
    SHAHAB_CORPORATE("SHAHAB_CORPORATE", 3),
    FOREIGN_CORPORATE("FOREIGN_CORPORATE", 4);


    private final String key;
    private final Integer value;

    public static CustomerIdType getByValue(Integer value) {
        for (CustomerIdType type : values()) {
            if (type.getValue().equals(value))
                return type;
        }
        return null;
    }

}
