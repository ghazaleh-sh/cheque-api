package ir.co.sadad.cheque.domain.enums;

import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zalando.problem.Status;

/**
 * types of reason
 */
@AllArgsConstructor
@Getter
public enum ReasonType {
    ISSUE("1", "ISSUE"),
    TRANSFER("2", "TRANSFER");

    private final String value;
    private final String description;

    public String getValue() {
        return value;
    }

    public static ReasonType getReason(String value) {
        for (ReasonType e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new ChakadClientException("reason.not.find", Status.BAD_REQUEST);
    }

    public static ReasonType getReasonByDescription(String desc) {
        for (ReasonType e : values()) {
            if (e.getDescription().equals(desc)) {
                return e;
            }
        }
        throw new ChakadClientException("reason.not.find", Status.BAD_REQUEST);
    }
}
