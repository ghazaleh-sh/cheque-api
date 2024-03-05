package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * نوع وصول چک
 */
@Getter
@AllArgsConstructor
public enum LeafInquiryBusinessType {

    WITHOUT_VALUE(0, "بدون مقدار"),
    INTERNAL(1, "درون بانکی"),
    CHAKAVAK(2, "بین بانکی(چکاوک)"),
    UNKNOWN(null, "ناشناخته");
    private final Integer code;
    private final String description;

    public static LeafInquiryBusinessType getByCode(Integer code) {
        for (LeafInquiryBusinessType e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
