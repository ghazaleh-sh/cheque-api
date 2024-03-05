package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * ساختار وضعیت برگشتی چک
 */
@Getter
@AllArgsConstructor
public enum LeafInquiryEreqType {

    STOCK_SUPPLY(1, "تامین موجودی"),
    HONORED_CHEQUE(2, "لاشه چک"),
    LETTER_OF_SATISFACTION(3, "رضایت نامه محضری"),
    DEPOSIT_AMOUNT_CHEQUE(4, "واریز مبلغ چک"),
    JUDICIAL_DECREE(5, "حکم قضایی"),
    SUBJECT_TO_REVIEW_OVER_TIME(6, "مشمول مرور زمان"),
    UNKNOWN(0, "ناشناخته");

    private final Integer code;
    private final String description;

    public static LeafInquiryEreqType getByCode(Integer code) {
        for (LeafInquiryEreqType e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
