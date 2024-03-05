package ir.co.sadad.cheque.web.rest.errors;

import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.BaambaseErrorResponseDto;
import org.zalando.problem.Status;

import java.util.List;

public class BaambaseClientException extends GeneralException {

    public List<BaambaseErrorResponseDto.SubErrorDto> extraData;
    public BaambaseClientException(String responseCode) {

        super(Status.BAD_REQUEST);
        this.message = exceptionTemplate + responseCode;
        this.code = responseCode;
    }

    public BaambaseClientException(String message, Status status) {

        super(status);
        this.message = message;
        this.code = String.valueOf(status.getStatusCode());
    }


    public BaambaseClientException(Status statusType, String code, List<BaambaseErrorResponseDto.SubErrorDto> extraData) {
        this.statusType = statusType;
        this.code = code;
        this.message = exceptionTemplate + code;
        this.extraData = extraData;
    }

}
