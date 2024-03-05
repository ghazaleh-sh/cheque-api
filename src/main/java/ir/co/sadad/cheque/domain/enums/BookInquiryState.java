package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum BookInquiryState {
    REGISTERED(165, "ثبت شده"),
    AWAITING_SEND(166, "در انتظار ارسال به بانک مرکزی"),
    AWAITING_RESPONSE(167, "در انتظار دریافت پاسخ از بانک مرکزی"),
    WITHOUT_CHEQUE_NUMBER(170, "فاقد شماره چک"),
    PROCESSING(171, "در انتظار تعیین تکلیف"),
    DONE(172, "اعلام نتیجه شده"),
    ARCHIVED(174, "بایگانی شده"),
    AWAITING_PRINT(177, "در اننظار چاپ"),
    PRINTED(178, "چاپ شده"),
    REJECTED(179, "رد شده"),
    SUCCESS(180, "سریال ارسال شده موفق"),
    UNSUCCESSFUL(181, "سریال ارسال شده ناموفق"),
    AWAITING_CANCEL(360, "در انتظار لغو"),
    AWAITING_REVOKED(361, "در انتظار ابطال"),
    DELIVERED(362, "تحویل داده شده به مشتری"),
    UNKNOWN(0, "نامشخص");

    private final Integer code;
    private final String description;

    public static BookInquiryState getByCode(Integer code) {
        if (code == null)
            return UNKNOWN;
        for (BookInquiryState e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return null;
    }

}
