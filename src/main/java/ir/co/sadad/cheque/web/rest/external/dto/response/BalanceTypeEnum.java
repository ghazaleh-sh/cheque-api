package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BalanceTypeEnum {
    CURRENT("CURRENT"),
    AVAILABLE("AVAILABLE"),
    USABLE("USABLE");

    private final String balanceValue;

    public static BalanceTypeEnum getByBalanceValue(String balanceValue) {
        for (BalanceTypeEnum bt : BalanceTypeEnum.values()) {
            if (bt.balanceValue.equals(balanceValue)) {
                return bt;
            }
        }
        return null;
    }
}
