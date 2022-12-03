package ir.co.sadad.cheque.web.rest.errors;

public enum ChequeApiError {
    GENERAL_INTERNAL_ERROR("general.internal.error", "1001CH", 500),
    FILL_MANDATORY_PARAMETERS("general.missing.mandatory.input", "1002CH", 400),
    GENERAL_MISMATCH_INPUT("general.mismatch.input", "1003CH", 400),
    GENERAL_MISMATCH_INPUT_TYPE("general.mismatch.input.type", "1004PO", 400),

    EXTERNAL_SERVICE_RESPONSE_DECODING_ERROR("feign.exception.decoder", "1101CH", 424),
    ISC_INTEGRATION_SERVICE_NOT_WORKING_CORRECTLY("isc.integration.service.error", "1102CH", 424),
    SSO_SERVICE_NOT_WORKING_CORRECTLY("sso.service.error", "1103CH", 424),
    EB_SERVICE_NOT_WORKING_CORRECTLY("eb.service.error", "1104CH", 424),
    SMS_SERVICE_NOT_WORKING_CORRECTLY("sms.service.error", "1105CH", 424),
    API_SERVICE_NOT_WORKING_CORRECTLY("eb.service.error", "1106CH", 424),
    AUTHORIZATION_CODE_IS_NOT_VALID("tan.authorization.failed", "1107CH", 429),
    PAYMENT_ORDER_SERVICE_NOT_WORKING_CORRECTLY("payment.order.service.error", "1108CH", 424),

    INACTIVE_SERVICE("inactive.service", "2012CH", 405);

    private final String errorCode;
    private final int status;
    private final String message;

    ChequeApiError(String message, String errorCode, int status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }
}
