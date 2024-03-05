package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcceptanceStatus {
    REJECTED(0, "REJECT"),
    APPROVED(1, "APPROVE");

    private final int code;
    private final String description;

    public static Integer getCodeByDescription(String desc) {
        for (AcceptanceStatus status : AcceptanceStatus.values()) {
            if (desc.equals(status.getDescription()))
                return status.getCode();
        }
        return null;
    }
}
