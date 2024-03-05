package ir.co.sadad.cheque.domain.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@Getter
public enum CustomerType {
    IRANIAN_REAL(1, "iranian.real"),
    IRANIAN_LEGAL(2, "iranian.legal"),
    NON_IRANIAN_REAL(3, "non.iranian.real"),
    NON_IRANIAN_LEGAL(4, "non.iranian.legal");

    private static final Locale LOCALE_FA = new Locale("fa");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.messages",
        LOCALE_FA);

    @Autowired
    private MessageSource messageSource;

    private static final Map<Integer, String> CUSTOMER_TYPE_MAP = new HashMap<>();

    static {
        for (CustomerType type : values()) {
            CUSTOMER_TYPE_MAP.put(type.getCode(), resourceBundle.getString("cheque-api.customer-type."
                + type.getDescription()));
        }
    }

    private Integer code;
    private String description;

    CustomerType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getByCode(Integer code) {
        return CUSTOMER_TYPE_MAP.get(code);
    }

}
