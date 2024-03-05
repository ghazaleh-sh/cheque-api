package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookInquiryAccountType {
    INDIVIDUAL(1, "انفرادی"),
    COMMON(2, "اشتراکی"),
    LEGAL(3, "حقوقی"),
    ALL(0, "همه");

    private final int code;
    private final String description;

    public static BookInquiryAccountType getByCode(int code) {
        for (BookInquiryAccountType e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }
}
