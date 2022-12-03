package ir.co.sadad.cheque.web.rest.external.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.BadRequestAlertException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GeneralClientError implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String type, Response response) {
        log.error("service has error: status " + response.status());
        return new BadRequestAlertException(response.reason(), type, "service has error");
    }
}
