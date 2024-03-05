package ir.co.sadad.cheque.web.rest.external.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.Status;

import java.io.InputStream;

@Log4j2
public class ChakadClientErrorDecoderConfig implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();


    @Override
    public Exception decode(String s, Response response) {
        ChakadErrorResponseDto responseBody = null;
        try {
            InputStream body = response.body().asInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            responseBody = objectMapper.readValue(body, ChakadErrorResponseDto.class);
        } catch (Exception e) {
            return new GeneralException(Status.valueOf(response.status()));
        }

        if (responseBody.getErrorDetails() != null && !responseBody.getErrorDetails().isEmpty()) {
            return new ChakadClientException(Status.valueOf(response.status()), responseBody.getCode(), responseBody.getErrorDetails());
        }

        return new GeneralException(Status.valueOf(response.status()), responseBody.getCode());

    }
}
