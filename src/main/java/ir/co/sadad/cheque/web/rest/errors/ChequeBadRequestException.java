package ir.co.sadad.cheque.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@Getter
public class ChequeBadRequestException extends AbstractThrowableProblem {

    private final String errorKey;
    private final String extraData;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "Asia/Tehran")
    private final Date timestamp;

    public ChequeBadRequestException(String detail, String errorKey, String extraData) {
        super(ErrorConstants.DEFAULT_TYPE, "Bad Request", Status.BAD_REQUEST, detail);
        this.errorKey = errorKey;
        this.extraData = extraData;
        this.timestamp = new Date();
    }
}
