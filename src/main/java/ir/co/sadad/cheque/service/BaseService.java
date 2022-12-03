package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.management.SsoClientTokenManager;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * base service
 * <pre>
 *     contains :
 *     - section for tokens
 *     - section for getting userAgent
 * </pre>
 */

@RequiredArgsConstructor
public abstract class BaseService {

    private static final String ANDROID_AGENT = "CxpMobileAndroid";
    private static final String IOS_AGENT = "CxpMobileiOS";
    private static final String USER_AGENT = "User-Agent";

    protected static final String SUCCESS_CHAKAD_CODE = "CHK100";
    protected static final int SUCCESS_SAYAD_CODE = 100;
    protected static final Long AMOUNT_FOR_REASON = 100000000L;


    private final HttpServletRequest httpServletRequest;
    private final SsoClientTokenManager ssoClientTokenManager;

    /**
     * get token based on client credential
     *
     * @return token
     */
    protected final String getToken() {
        String clientToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJncmFudCI6IkNMSUVOVCIsImlzcyI6Imh0dHA6Ly9hcGkuYm1pLmlyL3NlY3VyaXR5IiwiYXVkIjoiYm1pLWxvYW5jcmVkZW50aWFscy1jbGllbnQiLCJleHAiOjE2Njk4ODEyMTE4NTUsIm5iZiI6MTY2OTc5NDgxMTg1NSwicm9sZSI6IiIsInNlcmlhbCI6IjQ5YmE1NzlmLTE2YTgtMzJkNy1iMTU3LWE2MDg4ZjkyOTcwYSIsInNzbiI6IjEyOTYiLCJjbGllbnRfaWQiOiIxMjk2Iiwic2NvcGVzIjpbImNvbXBvc2l0ZS1tb25leS10cmFuc2ZlciIsInNlbmQtbWVzc2FnZSIsImNoZXF1ZS1hbGxvY2F0aW5nIiwiYWNjb3VudCIsImNoZXF1ZS1hbGxvY2F0aW5nLWVzdGVsYW0iLCJsb2FuIl19.mLwFEZ8tDKNBQrz-NSQemGe5TlqFmsZ7n1EX9nOcWyY";
        return clientToken;
//        return ssoClientTokenManager.getClientToken();
    }

    /**
     * make user agent for chakad services
     * <pre>
     *     based on user agent of headers
     * </pre>
     *
     * @return return 004 for android and 003 for IOS
     * @throws NotFoundException when not found userAgent header throws exceptions
     */
    protected final String getUserAgent() throws NotFoundException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = null;
        try {
            httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (NullPointerException ex) {
            // it doesn't come from http request
        }
        String userAgent;
        userAgent = httpServletRequest.getHeader(USER_AGENT);
        if (Strings.isBlank(userAgent)) {
            throw new NotFoundException("");
        }
        if (userAgent.startsWith(ANDROID_AGENT))
            return "004";
        if (userAgent.startsWith(IOS_AGENT))
            return "003";
        return "BRANCH";
    }


}
