package ir.co.sadad.cheque.web.rest.external.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class ChakadClientConfig {

    @Bean("ChakadClientDecoder")
    public Decoder getDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return objectMapper;
    }

    @Bean
    public Client feignClient() {
        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    private SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            return sslContext.getSocketFactory();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean("ChakadClientErrorDecoderConfig")
    public ErrorDecoder errorDecoder() {
        return new ChakadClientErrorDecoderConfig();
    }
}
