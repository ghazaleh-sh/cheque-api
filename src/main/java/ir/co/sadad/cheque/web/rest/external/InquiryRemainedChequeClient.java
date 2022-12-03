package ir.co.sadad.cheque.web.rest.external;

import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import ir.co.sadad.cheque.web.rest.external.config.BamClientError;
import ir.co.sadad.cheque.web.rest.external.config.InquiryRemainedChequeDecoder;
import ir.co.sadad.cheque.web.rest.external.dto.response.RemainedTwentyPercentChequeResponseDto;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    value = "InquiryRemainedChequeClient",
    url = "${feign.client.bam-api}",
    configuration = { InquiryRemainedChequeClient.Config.class }
)
public interface InquiryRemainedChequeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v2/cheque/chequebookList/{accountId}/1/100")
    RemainedTwentyPercentChequeResponseDto callService(
        @RequestHeader("Authorization") String bearerToken,
        @PathVariable("accountId") String accountId
    );

    class Config {

        @Bean("InquiryRemainedChequeDecoder")
        public Decoder getDecoder() {
            HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
            ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
            return new ResponseEntityDecoder(new InquiryRemainedChequeDecoder(objectFactory));
        }

        @Bean("BamClientError")
        public ErrorDecoder errorDecoder() {
            return new BamClientError();
        }
    }
}
