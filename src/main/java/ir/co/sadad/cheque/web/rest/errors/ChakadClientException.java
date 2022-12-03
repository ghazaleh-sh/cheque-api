package ir.co.sadad.cheque.web.rest.errors;

import org.zalando.problem.Status;

/**
 * exception for chakad services
 * <pre>
 *     use this exeption for translate exception code of external service to persian/english message
 * </pre>
 */
public class ChakadClientException extends GeneralException {

    public ChakadClientException(String responseCode) {

        super(Status.UNAVAILABLE_FOR_LEGAL_REASONS);
        String exceptionTemplate = "server.external.exception.";
        this.message = exceptionTemplate + responseCode;
        this.code = responseCode;
    }

    public ChakadClientException(String message, Status status) {

        super(status);
        this.message = message;
        this.code = String.valueOf(status.getStatusCode());
    }

}
