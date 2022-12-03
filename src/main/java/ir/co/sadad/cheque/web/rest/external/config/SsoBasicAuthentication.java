package ir.co.sadad.cheque.web.rest.external.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class SsoBasicAuthentication {

    @Value("${sso.client-name}")
    String clientName;

    @Value("${sso.client-password}")
    String clientPassword;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(clientName, clientPassword);
    }
}
