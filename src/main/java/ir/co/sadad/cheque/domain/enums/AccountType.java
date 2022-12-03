package ir.co.sadad.cheque.domain.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    CURRENT("01\\d{11}"),
    SHORTTERM("02\\d{11}"),
    SAVING("03\\d{11}"),
    LONGTERM("04\\d{11}"),
    GEVERNMENT("07\\d{11}"),
    GEVERNMENTSHARE("(211\\d{10})|(217\\d{10})"),
    SCHOOL("4\\d{12}"),
    LOAN_BENOVOLENT("61\\d{11}"),
    LOAN_JUALAH("62\\d{11}"),
    LOAN_INSTALLMENT_SALES("63\\d{11}"),
    LOAN_HIRE_PURCHASE("64\\d{11}"),
    LOAN_MUDARABAH("65\\d{11}"),
    LOAN_MUSHARAKAH("66\\d{11}");

    private final String pattern;

    public static AccountType getAccountType(String account) {
        for (AccountType accountType : AccountType.values()) {
            Pattern pattern = Pattern.compile(accountType.pattern);
            Matcher matcher = pattern.matcher(account);
            if (matcher.matches()) {
                return accountType;
            }
        }
        return null;
    }
}
