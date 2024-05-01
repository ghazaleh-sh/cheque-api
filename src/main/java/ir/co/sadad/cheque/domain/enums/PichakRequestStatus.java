package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PichakRequestStatus {
    REQUESTED(1, "ثبت درخواست"),
    BRANCH_REJECTED(2, "رد درخواست توسط شعبه"),
    BOUNCED_CHEQUE(3, "عدم صلاحیت – دارای چک برگشتی"),
    DEBT(4, "عدم صلاحیت – دارای بدهی معوق"),
    JUDICIAL_DECREE(5, "عدم صلاحیت – دارای ممنوعیت قضایی"),
    OTHER_ACCOUNT_CHEQUE(6, "عدم صلاحیت – دارای دسته چک از حساب دیگر"),
    APPROVED(7, "تایید درخواست"),
    PRINTED(8, "چاپ دسته چک"),
    BRANCH_PENDING(9, "منتظر مراجعه مشتری به شعبه"),
    DISQUALIFIED_UNKNOWN(0, "عدم صلاحیت - سایر موارد"),
    DISQUALIFIED_EXCEEDED_USAGE(10, "عدم صلاحیت - عدم استفاده بیشتر از 80% دسته چک قبلی");

    private final int code;
    private final String description;

    public static PichakRequestStatus getByCode(int code) {
        for (PichakRequestStatus e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return DISQUALIFIED_UNKNOWN;
    }
}
