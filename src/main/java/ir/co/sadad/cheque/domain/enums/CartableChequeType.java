package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;

@Getter
public enum CartableChequeType {

    NORMAL(1, "عادی"),
    Bank(2, "بانکی"),
    GUARANTEED(3, "چک تضمینی(رمزدار)"),
    CASE(4, "چک موردی");


    private final Integer code;
    private final String description;

    CartableChequeType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CartableChequeType getByCode(Integer code) {
        for (CartableChequeType e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
