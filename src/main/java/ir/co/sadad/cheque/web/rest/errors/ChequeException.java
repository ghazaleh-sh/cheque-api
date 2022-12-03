package ir.co.sadad.cheque.web.rest.errors;

import ir.co.sadad.cheque.domain.enums.ChequeErrorType;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * this class used for legacy migration code ,
 * don't use it for new codes
 */
@Getter
public class ChequeException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorSummary;
    private final String[] errorCauses;
    private StatusType status = Status.INTERNAL_SERVER_ERROR;

    public ChequeException(ChequeErrorType chequeErrorType) {
        this.status = Status.valueOf(chequeErrorType.getHttpStatusCode());
        this.errorSummary = chequeErrorType.getDescription();
        this.errorCode = chequeErrorType.getCode();
        this.errorCauses = new String[] { chequeErrorType.name() };
    }
}
