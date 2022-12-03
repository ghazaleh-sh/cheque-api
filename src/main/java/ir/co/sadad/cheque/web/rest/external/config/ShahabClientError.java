package ir.co.sadad.cheque.web.rest.external.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.ShahabException;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.Status;

@Log4j2
public class ShahabClientError implements ErrorDecoder {


    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();

    @SneakyThrows
    @Override
    public Exception decode(String type, Response response) {
        ShahabResponseDto errorResponse = null;
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            errorResponse = gson.fromJson(response.body().asReader(), ShahabResponseDto.class);
            String extraData = errorResponse.getError().getErrors() != null
                ? gson.toJson(errorResponse.getError().getErrors())
                : StringUtils.EMPTY;

            return new ShahabException(
                Status.valueOf(response.status()),
                null,
                errorResponse.getError().getCode(),
                errorResponse.getError().getMessage(),
                errorResponse.getError().getTimestamp(),
                extraData
            );
        } catch (Exception exp) {
            return errorDecoder.decode(type, response);
        }
    }
}
