package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * ساختار وضعیت مسدودی چک
 */
@Getter
@AllArgsConstructor
public enum LeafInquiryBlockStatus {
    TEMPORARY_BLOCKING(1, "مسدود موقت"),
    PERMENT_BLOCKING(2, "مسدود دائم"),
    FIXED_BLOCKING(3, "رفع مسدودی"),
    WAITING_BLOCKING(4, "منتظر مسدودی/رفع مسدودی"),
    UNKNOWN(0, "ناشناخته");

    private final Integer code;
    private final String description;

    public static LeafInquiryBlockStatus getByCode(Integer code) {
        for (LeafInquiryBlockStatus e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
