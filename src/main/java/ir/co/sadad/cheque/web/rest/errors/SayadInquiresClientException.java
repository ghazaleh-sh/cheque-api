package ir.co.sadad.cheque.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * exception for sayd inquires ,
 * <pre>
 *     inquiry service of sayad will return list of errors , so this must handle in exceptions
 * </pre>
 */
@Getter
public class SayadInquiresClientException extends AbstractThrowableProblem {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "Asia/Tehran")
    private final Date timestamp;
    protected Status statusType;
    private final HashMap<String, String> messages;

    public SayadInquiresClientException(List<String> codes) {
        this.timestamp = new Date();
        this.statusType = Status.BAD_REQUEST;
        this.messages = new HashMap<>();
        if (codes.size() == 0)
            this.messages.put("no_code", "server.external.exception.not.translate");
        else {
            String exceptionTemplate = "server.external.exception.";
            codes.forEach(item -> messages.put(item, exceptionTemplate + item));
        }
    }
}
