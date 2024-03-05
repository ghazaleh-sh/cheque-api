package ir.co.sadad.cheque.web.rest.errors;

import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadErrorResponseDto;
import org.zalando.problem.Status;

import java.util.List;
import java.util.Objects;

import static org.zalando.problem.Status.PRECONDITION_FAILED;

/**
 * exception for chakad services
 * <pre>
 *     use this exeption for translate exception code of external service to persian/english message
 * </pre>
 */
public class ChakadClientException extends GeneralException {

    public List<ChakadErrorResponseDto.ErrorDetails> extraData;

    public ChakadClientException(String responseCode) {
        super(Status.BAD_REQUEST);
        if (Objects.equals(responseCode, "412"))
            this.statusType = PRECONDITION_FAILED;

        this.message = exceptionTemplate + responseCode;
        this.code = responseCode;
    }

    public ChakadClientException(String message, Status status) {

        super(status);
        this.message = message;
        this.code = String.valueOf(status.getStatusCode());
    }


    public ChakadClientException(Status statusType, String code, List<ChakadErrorResponseDto.ErrorDetails> extraData) {
        this.statusType = statusType;
        this.code = code;
        this.message = exceptionTemplate + code;
        this.extraData = extraData;
    }

}
