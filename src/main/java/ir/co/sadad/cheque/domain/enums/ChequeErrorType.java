package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * this class used for legacy migration code ,
 * don't use it for new codes
 */

@Getter
@AllArgsConstructor
public enum ChequeErrorType {
    CONFLICT_NUMBER_OF_ACCOUNTS(400, 410, "تعداد صاحبین حساب ثبت شده مغایر با نوع حساب می باشد", "E0000410"),
    DUPLICATE_REQUEST(400, 411, "درخواست ارسال شده تکراری می باشد ", "E0000411"),
    INVALID_ACCOUNT(400, 412, "نوع حساب معتبر نمی باشد", "E0000412"),
    INCORRECT_BRANCH_CODE(400, 414, "کد شعبه صحیح نمی باشد", "E0000414"),
    INCORRECT_FIRST_NAME_FORMAT(400, 415, "فرمت نام صاحب حساب صحیح نمی باشد", "E0000415"),
    INCORRECT_LAST_NAME_FORMAT(400, 416, "فرمت نام خانوادگی صاحب حساب صحیح نمی باشد", "E0000416"),
    INCORRECT_IDENTIFIER_TYPE(
        400,
        417,
        "درخواست اولین دسته چک، تنها از طریق مراجعه به شعبه افتتاح کننده حساب امکان پذیر می باشد.",
        "E0000417"
    ),
    INCORRECT_IDENTITY_INFORMATION(400, 418, "نقص اطلاعات هویتی- جهت تکمیل اطلاعات هویتی به شعبه مراجعه نمایید.", "E0000418"),
    INCORRECT_IDENTITY_ADDRESS(400, 419, "نقص اطلاعات آدرس- جهت تکمیل اطلاعات آدرس متقاضی به شعبه مراجعه نمایید.", "E0000419"),
    HAS_OVER_DUE_FACILITIES(400, 420, "کاربر گرامی به دلیل وجود تسهیلات معوقه قادر به ثبت دسته چک نمی باشید.", "E0000420"),
    HAS_RETURNED_CHEQUE(400, 421, "کاربر گرامی به دلیل وجود تسهیلات معوقه قادر به ثبت دسته چک نمی باشید.", "E0000421"),
    HAS_REMAINED_CHEQUE(400, 422, "", "E0000422"),

    INCORRECT_PERSON_TYPE(400, 453, "نوع شخص معتبر نمی باشد", "E0000453"),
    INCORRECT_IDENTIFIER_OF_PERSON_FORMAT(400, 454, "فرمت شناسه شخص معتبر نمی باشد", "E0000454"),
    INCORRECT_BIRTH_DATE_FORMAT(400, 456, "فرمت تاریخ تولد معتبر نمی باشد", "E0000456"),
    INCORRECT_IDENTIFIER_FORMAT(400, 457, "فرمت شماره شناسنامه صحیح نمی باشد", "E0000457"),
    INCORRECT_IBAN_FORMAT(400, 460, "فرمت شماره شبا معتبر نمی باشد", "E0000460"),
    INCORRECT_CHEQUE_SHEET_COUNT(400, 461, "تعداد برگ چک درخواستی نامعتبر است ", "E0000461"),
    DUPLICATE_REGISTERED_CHEQUE(400, 469, "صاحب حساب قبلا از حساب دیگری دسته چک دریافت کرده است", "E0000469"),
    INACTIVE_IBAN(400, 491, "شماره شبا در صیاد غیر فعال است", "E0000491"),
    INVALID_INPUT(400, 110, "خطای نامعلوم و ورودی نامعتبر", "E0000110"),
    IBAN_WITHOUT_CHEQUE_REQUEST(400, 426, "کاربر گرامی، درخواست دسته چک برای شما ثبت نشده است.", "E0000426"),
    INCORRECT_IBAN_FORMAT1(400, 427, "فرمت شماره شبا معتبر نمی باشد.", "E0000427"),
    WRONG_INTERVAL_TIME(400, 428, "بازه زمانی انتخاب شده صحیح نمی باشد.", "E0000428"),
    WITH_DELAYED_FACILITY(400, 400, "به دلیل وجود تسهیلات معوق قادر به ثبت درخواست نمی باشید.", "E0000429"),
    UNAUTHORIZED(401, 401, "کاربر گرامی، درخواست دسته چک برای شما ثبت نشده است.", "E0000401"),
    SERVICE_HAS_ERROR(400, 400, "خطای سرویس", "E0000010"),
    LACK_OF_ACCESS_TO_ACCOUNT(400, 400, "عدم دسترسی به حساب", "E0000010"),
    INVALID_ACCOUNT_TYPE(400, 400, "نوع حساب جاری نمی باشد.", "E0000110"),
    SERVICE_CALL_HAS_ERROR(400, 400, "خطای فراخوانی سرویس", "E0000011");

    private final int httpStatusCode;
    private final int resultCode;
    private final String description;
    private final String code;

    public static ChequeErrorType valueOfResultCode(int resultCode) {
        for (ChequeErrorType e : values()) {
            if (e.resultCode == resultCode) {
                return e;
            }
        }
        return SERVICE_HAS_ERROR;
    }
}
