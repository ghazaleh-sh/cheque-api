package ir.co.sadad.cheque.web.rest.external.config;

import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class BamClientConfig {

    @Bean("BamClientDecoder")
    public Decoder getDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new BamClientDecoder(objectFactory));
    }

    @Bean("BamClientError")
    public ErrorDecoder errorDecoder() {
        return new BamClientError();
    }
}
