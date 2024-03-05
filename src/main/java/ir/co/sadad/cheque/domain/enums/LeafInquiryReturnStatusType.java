package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * ساختار وضعیت برگشتی چک
 */
@Getter
@AllArgsConstructor
public enum LeafInquiryReturnStatusType {

    HAS_TRACKING_CODE(1, "دارای کد رهگیری"),
    FIXED_BAD_EFFECT(2, "رفع سوء اثر"),
    WAITING_FOR_FIXED_BAD_EFFECT(3, "منتظر رفع سوء"),
    RETURN_WITHOUT_TRACKING_CODE(4, "برگشت بدون رهگیری"),
    WAITING_FOR_CORRECTION_RETURN_CHEQUE(5, "منتظر اصلاح چک برگشتی"),
    UNKNOWN(0, "ناشناخته");
    private final Integer code;
    private final String description;

    public static LeafInquiryReturnStatusType getByCode(Integer code) {
        for (LeafInquiryReturnStatusType e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
