package ir.co.sadad.cheque.domain.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@Getter
public enum BouncedReason {
    INVENTORY_DEDUCTION(402, "inventory.deduction"),
    OUT_OF_STOCK(403, "out.of.stock"),
    MISMATCH_SIGNATURE(404, "mismatch.signature"),
    DEFECT_SIGNATURE(405, "defect.signature"),
    CONTRADICTION_DATE(406, "contradiction.date"),
    DAMAGED_SIGNATURE(407, "damaged.signature"),
    DAMAGED_DETAIL(408, "damaged.detail"),
    CONTRADICTION_AMOUNT(409, "contradiction.amount"),
    CLOSED_ACCOUNT(410, "closed.account"),
    BLOCKED_ACCOUNT(411, "blocked.account"),
    BLOCKED_CHEQUE(412, "blocked.cheque");

    private static final Locale LOCALE_FA = new Locale("fa");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.messages",
        LOCALE_FA);

    @Autowired
    private MessageSource messageSource;

    private static final Map<Integer, String> BOUNCED_REASON_MAP = new HashMap<>();

    static {
        for (BouncedReason type : values()) {
            BOUNCED_REASON_MAP.put(type.getCode(),
                resourceBundle.getString("cheque-api.bounced-reason."
                    + type.getDescription()));
        }
    }

    private Integer code;
    private String description;

    BouncedReason(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getByCode(Integer code) {
        return BOUNCED_REASON_MAP.get(code);
    }

}
