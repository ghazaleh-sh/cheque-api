package ir.co.sadad.cheque.web.rest.external.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.domain.enums.ChequeErrorType;
import ir.co.sadad.cheque.web.rest.errors.ChequeException;
import ir.co.sadad.cheque.web.rest.external.dto.response.BamClientExceptionDto;
import java.io.IOException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BamClientError implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String type, Response response) {
        BamClientExceptionDto errorResponse = null;
        if (type.contains("checkIsFirstCheque")) {
            log.error("is first cheque has error : ");
            return new ChequeException(ChequeErrorType.SERVICE_HAS_ERROR);
        } else if (type.contains("getAccountList")) {
            log.error("get account list has error : ");
            return new RuntimeException(); // TODO: 12/30/2021  enter complete exception
        } else if (type.contains("InquiryRemainedChequeClient")) {
            log.error("inquiry remained cheque service has error");
            return new ChequeException(ChequeErrorType.SERVICE_CALL_HAS_ERROR);
        } else {
            try {
                Gson gson = new GsonBuilder().serializeNulls().create();
                errorResponse = gson.fromJson(response.body().asReader(), BamClientExceptionDto.class);
            } catch (Exception exp) {
                log.error("service has error : " + response.request().url() + " " + exp);
                return new ChequeException(ChequeErrorType.SERVICE_HAS_ERROR); // TODO: 12/30/2021  enter complete exception
            }
        }
        if (errorResponse == null) {
            log.error("Can't convert response correctly! : " + response.request().url());
            return new IOException("Can't convert response correctly!");
        }
        return new RuntimeException(errorResponse.getError()); // TODO: 12/30/2021  enter complete exception
    }
}
