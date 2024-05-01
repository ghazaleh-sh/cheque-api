package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;

@Getter
public enum CartableChequeStatus {

    REGISTERED(1, "ثبت شده"),

    CASHED(2, "نقد شده"),

    CANCELDED(3, "باطل شده"),

    BOUNCED(4, "برگشت خورده"),

    PARTIAL_BOUNCED(5, "بخشی برگشت خورده"),

    WAITING_SIGNATURE(6, "در انتظار امضا ضامن"),

    WAITING_CONFIRM_REJECT(7, "منتظر تایید و رد"),

    CHECKOUT_PROCESSING(9, "در حال وصول");

    private final Integer code;
    private final String description;

    CartableChequeStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CartableChequeStatus getByCode(Integer code) {
        for (CartableChequeStatus e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}

