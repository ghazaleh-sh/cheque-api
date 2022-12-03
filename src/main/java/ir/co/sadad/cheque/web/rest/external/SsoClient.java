package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.SsoBasicAuthentication;
import ir.co.sadad.cheque.web.rest.external.config.SsoClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.response.SsoTokenDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "SsoClient", url = "${feign.client.sso-url}", configuration = { SsoClientConfig.class, SsoBasicAuthentication.class })
public interface SsoClient {
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/identity/oauth2/auth/token",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    SsoTokenDto geToken(@RequestBody Map<String, ?> form);
}
