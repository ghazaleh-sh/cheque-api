package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum BookInquiryStatusCode {
    REQUEST(1, "درخواست داده شده"),
    APPROVED(2, "پذیرفته شده"),
    AWAITING(3, "عدم صلاحیت"),
    ISSUED(4, "صدور سریال"),
    CANCELED(5, "لغو درخواست"),
    PROCESSING(10, "در انتظار مجوز صدور"),
    REVOKED(11, "ابطال شده"),
    SIBA_REQUEST(12, "اعلام به سیبا"),
    BRANCH_REJECTED(100, "عدم صلاحیت شعبه"),
    UNKNOWN(0, "نامشخص");

    private final Integer code;
    private final String description;

    public static BookInquiryStatusCode getByCode(Integer code) {
        if (code == null)
            return UNKNOWN;
        for (BookInquiryStatusCode e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return null;
    }
}
