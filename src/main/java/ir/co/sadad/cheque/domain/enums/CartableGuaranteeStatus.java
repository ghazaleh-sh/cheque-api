package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;

@Getter
public enum CartableGuaranteeStatus {
    NO_GUARRANTY(1, "این چک فاقد ضمانت می باشد"),
    GAURANTY_INPROGRESS(2, "فرآیند ضمانت در جریان است"),
    GUARANTY_CANCELED(3, "فرآیند ضمانت با درخواست ذینفع ناتمام خاتمه یافته است"),
    SUCCESS_GUARANTIED(4, "فرآیند ضمانت اتمام و همه ضامن ها ضمانت کرده اند"),
    FAILED_GUARANTIED(5, "فرآیند ضمانت اتمام و برخی ضامن ها ضمانت را رد کرده اند");

    private final Integer code;
    private final String description;

    CartableGuaranteeStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CartableGuaranteeStatus getByCode(Integer code) {
        for (CartableGuaranteeStatus e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
