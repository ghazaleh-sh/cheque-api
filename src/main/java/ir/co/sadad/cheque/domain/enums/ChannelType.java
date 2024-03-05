package ir.co.sadad.cheque.domain.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@Getter
public enum ChannelType {
    CHAKAVAK(1, "chakavak"), BANK(2, "bank");

    private static final Locale LOCALE_FA = new Locale("fa");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.messages",
        LOCALE_FA);

    @Autowired
    private MessageSource messageSource;

    private static final Map<Integer, String> CHANNEL_TYPE_MAP = new HashMap<>();

    static {
        for (ChannelType type : values()) {
            CHANNEL_TYPE_MAP.put(type.getCode(), resourceBundle.getString("cheque-api.channel."
                + type.getDescription()));
        }
    }

    private Integer code;
    private String description;

    ChannelType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getByCode(Integer code) {
        return CHANNEL_TYPE_MAP.get(code);
    }
}
