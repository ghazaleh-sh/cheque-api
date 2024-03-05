package ir.co.sadad.cheque.domain.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@Getter
public enum ChequeOwnerType {
    OWNER(1, "owner"), SIGNATORY(2, "signatory"), BENEFICIARY(3, "beneficiary");

    private static final Locale LOCALE_FA = new Locale("fa");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.messages",
        LOCALE_FA);

    @Autowired
    private MessageSource messageSource;

    private static final Map<Integer, String> CHEQUE_OWNER_TYPE_MAP = new HashMap<>();
    static {
        for (ChequeOwnerType type : values()) {
            CHEQUE_OWNER_TYPE_MAP.put(type.getCode(), resourceBundle.getString("cheque-api.owner-type."
                + type.getDescription()));
        }
    }

    private Integer code;
    private String description;

    ChequeOwnerType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getByCode(Integer code) {
        return CHEQUE_OWNER_TYPE_MAP.get(code);
    }

}
