package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;

@Getter
public enum GuaranteeBlockStatus {
    NOT_BLOCKED(0, "چک مسدود نشده است"),
    TEMPORARY_BLOCKED(1, "مسدود موقت میباشد"),
    PERMANENTLY_BLOCKED(2, "مسدود دائم میباشد"),
    UNBLOCK(3, "چک رفع مسدودی شده است");

    private final Integer code;
    private final String description;

    GuaranteeBlockStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static GuaranteeBlockStatus getByCode(Integer code) {
        for (GuaranteeBlockStatus e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
