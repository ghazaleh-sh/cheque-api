package ir.co.sadad.cheque.web.rest.errors;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

import java.util.Date;

@Getter
public class ShahabException extends AbstractThrowableProblem {


    private final String code;
    private final String message;
    private final Date timestamp;
    private final String extraData;

    public ShahabException(StatusType statusType, String detail, String code, String message, Date timestamp, String extraData) {
        super(ErrorConstants.DEFAULT_TYPE, statusType.getReasonPhrase(), statusType, detail);
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.extraData = extraData;
    }
}
