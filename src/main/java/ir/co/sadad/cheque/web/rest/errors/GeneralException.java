package ir.co.sadad.cheque.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.Date;

import static org.zalando.problem.Status.UNAUTHORIZED;


/**
 * general exception for chakad services
 */
@Getter
public class GeneralException extends AbstractThrowableProblem {

    protected String exceptionTemplate = "server.external.exception.";
    protected Status statusType;
    protected String message;
    protected String code;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "Asia/Tehran")
    protected Date timestamp;

    public GeneralException() {
        this.timestamp = new Date();
    }


    public GeneralException(Status statusType) {
        this.statusType = statusType;

        if (this.statusType.getStatusCode() == UNAUTHORIZED.getStatusCode()) {
            this.message = "server.external.exception.UNAUTHORIZED";
            this.code = "CHB401";
        }
        this.timestamp = new Date();
    }

    public GeneralException(Status statusType, String code) {
        this.statusType = statusType;
        this.code = code;
        if (this.statusType.getStatusCode() == UNAUTHORIZED.getStatusCode()) {
            this.message = "server.external.exception.UNAUTHORIZED";
            this.code = "CHB401";
        }
        else this.message = exceptionTemplate + code;
        this.timestamp = new Date();
    }
}
