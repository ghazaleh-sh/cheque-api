package ir.co.sadad.cheque.web.rest.external.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.BaambaseClientException;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.BaambaseErrorResponseDto;
import org.zalando.problem.Status;

import java.io.IOException;
import java.io.InputStream;

public class BaambaseClientErrorDecoderConfig implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        BaambaseErrorResponseDto responseBody = null;
        try {
            InputStream body = response.body().asInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            responseBody = objectMapper.readValue(body, BaambaseErrorResponseDto.class);
        } catch (IOException e) {
            return new GeneralException(Status.valueOf(response.status()));
        }

        if (responseBody.getSubErrors() != null && !responseBody.getSubErrors().isEmpty()) {
            return new BaambaseClientException(Status.valueOf(response.status()), responseBody.getErrorCode(), responseBody.getSubErrors());
        }

        return new GeneralException(Status.valueOf(response.status()), responseBody.getErrorCode());

    }
}
