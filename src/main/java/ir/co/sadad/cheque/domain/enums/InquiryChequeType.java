package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InquiryChequeType {
    NORMAL(1, "عادی"),
    CODED(2, "رمزدار"),
    GUARANTEED(3, "تضمینی"),
    CASE(4, "موردی"),
    BANK(5, "بانکی");

    private final int code;
    private final String description;

    public static InquiryChequeType getByCode(int code) {
        for (InquiryChequeType e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }
}
