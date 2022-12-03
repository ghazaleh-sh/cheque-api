package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * this class used for legacy migration code ,
 * don't use it for new codes
 */

@AllArgsConstructor
@Getter
public enum ChequeStatus {
    REGISTER_REQUEST(1, "ثبت درخواست"),
    DENY_REQUEST(2, "رد درخواست توسط شعبه"),
    RETURNED_CHEQUE(3, "عدم صلاحیت – دارای چک برگشتی"),
    DELAYED_DEBT(4, "عدم صلاحیت – دارای بدهی معوق"),
    JUDICIAL_BAN(5, "عدم صلاحیت – دارای ممنوعیت قضایی"),
    HAS_ANOTHER_CHEQUE(6, "عدم صلاحیت – دارای دسته چک از حساب دیگر"),
    CONFIRM_REQUEST(7, "تایید درخواست"),
    PRINT_CHEQUE(8, "چاپ دسته چک"),
    WAITING(9, "منتظر مراجعه مشتری به شعبه"),
    UNKNOWN(0, "ناشناخته");

    private final int code;
    private final String description;

    public static ChequeStatus valueOfResultCode(int code) {
        for (ChequeStatus e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return UNKNOWN;
    }
}
