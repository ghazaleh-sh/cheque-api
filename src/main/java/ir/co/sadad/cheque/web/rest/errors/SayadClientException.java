package ir.co.sadad.cheque.web.rest.errors;

import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.Status;

import static ir.co.sadad.cheque.web.rest.errors.ErrorConstants.sayadExceptions;

/**
 * exception for sayad client service
 */
public class SayadClientException extends GeneralException {

    public SayadClientException(int responseCode) {
        super(Status.BAD_REQUEST);
        this.message = sayadExceptions().get(responseCode);
        this.code = Integer.toString(responseCode);
        if (StringUtils.isBlank(message))
            message = "server.external.exception.not.translate";
    }

    public SayadClientException(String responseCode) {
        super(Status.BAD_REQUEST);
        String exceptionTemplate = "server.external.exception.";
        this.message = exceptionTemplate + responseCode;
        this.code = responseCode;
    }
}
