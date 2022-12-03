package ir.co.sadad.cheque.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

@Getter
public class FeignGeneralException extends AbstractThrowableProblem {

    private final String code;
    private final String extraData;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "Asia/Tehran")
    private final Date timestamp;

    public FeignGeneralException(StatusType statusType, String detail, String code, String extraData) {
        super(ErrorConstants.DEFAULT_TYPE, statusType.getReasonPhrase(), statusType, detail);
        this.code = code;
        this.extraData = extraData;
        this.timestamp = new Date();
    }
}
