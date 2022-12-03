package ir.co.sadad.cheque.web.rest.external.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.ChequeApiError;
import ir.co.sadad.cheque.web.rest.errors.FeignGeneralException;
import ir.co.sadad.cheque.web.rest.external.dto.response.SadadErrorsResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.Status;

@Log4j2
public class SadadClientError implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();

    @SneakyThrows
    @Override
    public Exception decode(String type, Response response) {
        SadadErrorsResponseDto errorResponse = null;
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            errorResponse = gson.fromJson(response.body().asReader(), SadadErrorsResponseDto.class);
            String extraData = errorResponse.getError().getErrors() != null
                ? gson.toJson(errorResponse.getError().getErrors())
                : StringUtils.EMPTY;

            return new FeignGeneralException(
                Status.valueOf(response.status()),
                errorResponse.getError().getMessage(),
                ChequeApiError.API_SERVICE_NOT_WORKING_CORRECTLY.getErrorCode(),
                extraData
            );
        } catch (Exception exp) {
            return errorDecoder.decode(type, response);
        }
    }
}
