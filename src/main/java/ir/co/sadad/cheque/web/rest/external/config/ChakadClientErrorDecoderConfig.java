package ir.co.sadad.cheque.web.rest.external.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.Status;

@Log4j2
public class ChakadClientErrorDecoderConfig implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();


    @Override
    public Exception decode(String s, Response response) {
        return new GeneralException(
            Status.valueOf(response.status()));
    }
}
