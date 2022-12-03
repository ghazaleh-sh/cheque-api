package ir.co.sadad.cheque.management;

import ir.co.sadad.cheque.web.rest.external.SsoClient;
import ir.co.sadad.cheque.web.rest.external.dto.response.SsoTokenDto;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class SsoClientTokenManager {

    private final SsoClient ssoClient;

    private Date startDate;
    private volatile String token;
    private int expiration;

    @Value("${sso.scopes}")
    private String scopes;

    @Value("${sso.grant-type}")
    private String grantType;

    public String getClientToken() {
        if (this.token == null || new Date().after(this.startDate)) {
            synchronized (SsoClientTokenManager.class) {
                if (this.token == null || new Date().after(this.startDate)) {
                    this.token = getSsoAuth().getAccessToken();
                    this.expiration = getSsoAuth().getExpiresIn();
                    this.startDate = calculateExpireDate();
                }
            }
        }
        log.info("cheque client token : " + token);
        return "Bearer " + token;
    }

    private SsoTokenDto getSsoAuth() {
        try {
            return ssoClient.geToken(Map.of("scope", scopes, "grant_type", grantType));
        } catch (Exception exp) {
            return new SsoTokenDto();
        }
    }

    public void setToken(String token) {
        this.token = token;
    }

    private Date calculateExpireDate() {
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, expiration);
        return calendar.getTime();
    }
}
