package ir.co.sadad.cheque.web.rest.external.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.FeignException;
import feign.Response;
import ir.co.sadad.cheque.web.rest.external.dto.response.RemainedTwentyPercentChequeResponseDto;
import java.io.IOException;
import java.lang.reflect.Type;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

public class InquiryRemainedChequeDecoder extends SpringDecoder {

    public InquiryRemainedChequeDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public RemainedTwentyPercentChequeResponseDto decode(Response response, Type type) throws IOException, FeignException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        RemainedTwentyPercentChequeResponseDto genericResponse = null;

        if (response.status() == 202) {
            genericResponse = new RemainedTwentyPercentChequeResponseDto();
        } else {
            genericResponse = gson.fromJson(response.body().asReader(), RemainedTwentyPercentChequeResponseDto.class);
        }

        if (genericResponse == null) {
            throw new IOException("Can't convert response correctly!");
        }
        genericResponse.setStatus(response.status());
        return genericResponse;
    }
}
