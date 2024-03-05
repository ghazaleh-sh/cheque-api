package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * ساختار وضعیت چک
 */
@Getter
@AllArgsConstructor
public enum LeafInquiryStatusType {
    USABLE(1, "قابل استفاده"),
    WAITING_FOR_RECIEVER_CONFIRMATION(2, "منتظر تایید گیرنده"),
    ISSUED(3, "صادر شده"),
    CASHED(4, "نقد شده"),
    BOUNCED(5, "برگشت شده"),
    CANCELDED(6, "ابطال شده"),
    UNKNOWN(0, "ناشناخته");

    private final Integer code;
    private final String description;

    public static LeafInquiryStatusType getByCode(Integer code) {
        for (LeafInquiryStatusType e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
