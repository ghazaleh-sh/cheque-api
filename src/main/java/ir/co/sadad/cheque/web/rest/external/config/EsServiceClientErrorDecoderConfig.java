package ir.co.sadad.cheque.web.rest.external.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import ir.co.sadad.cheque.web.rest.errors.SayadInquiresClientException;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchResponseDto;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.Status;

import java.io.InputStream;

@Log4j2
public class EsServiceClientErrorDecoderConfig implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ChequeInquiryBatchResponseDto responseBody = null;
        try {
            InputStream body = response.body().asInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            responseBody = objectMapper.readValue(body, ChequeInquiryBatchResponseDto.class);
        } catch (Exception e) {
            return new GeneralException(Status.valueOf(response.status()));
        }

        if (responseBody.getErrorCodes() != null && !responseBody.getErrorCodes().isEmpty()) {
            return new SayadInquiresClientException(responseBody.getErrorCodes());
        }

        return new GeneralException(Status.valueOf(response.status()));

    }
}
