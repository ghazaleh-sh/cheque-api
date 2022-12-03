package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestInputType {
    BRANCH(1),
    BAAM(2);

    private int code;

    public static RequestInputType valueOfResultCode(int code) {
        for (RequestInputType e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return BAAM;
    }
}
