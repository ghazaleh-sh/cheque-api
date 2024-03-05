package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.AccessTokenProcessor;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CustomerRequestDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * base service
 * <pre>
 *     contains :
 *     - section for tokens
 *     - section for getting userAgent
 * </pre>
 */

@RequiredArgsConstructor
public abstract class BaseService implements AccessTokenProcessor {

    private static final String ANDROID_AGENT = "CxpMobileAndroid";
    private static final String IOS_AGENT = "CxpMobileiOS";
    private static final String USER_AGENT = "User-Agent";
    protected static final String basicAuth = "Basic SU5nZW5EUmFOVEltRWxkRWxpQ0E6Sm12WWJUUWtHY0ZFc3FUR1JNREo=";
    protected static final String SUCCESS_CHAKAD_CODE = "CHK100";
    protected static final int SUCCESS_SAYAD_CODE = 100;
    protected static final int ACCOUNT_DOES_NOT_HAVE_CHEQUE = 426;
    protected static final String TRANSFER_OTP_SENT = "412";
    protected static final String SUCCESS_SAYAD_INQUIRY_CODE = "100";
    protected static final String SUCCESS_PICHAK_CODE = "CHQ100";
    protected static final String INVALID_CERTIFICATION = "CHB801";
    protected static final String VALID_CERTIFICATION_OTHER_DEVICE = "CHB802";
    protected static final String ACCEPT_SERVICE_USERNAME = "root";

    public static final Integer DIGITAL_MEDIA_TYPE = 2;

    //TODO: both temporary
//    protected String prodTokenForTest = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJncmFudCI6IlBBU1NXT1JEIiwiaXNzIjoiaHR0cDovL2FwaS5ibWkuaXIvc2VjdXJpdHkiLCJhdWQiOiJrZXkiLCJleHAiOjE3MDQ4Njk2NDc2MjQsIm5iZiI6MTcwNDc4MzI0NzYyNCwicm9sZSI6ImludGVybmV0IGJhbmstY3VzdG9tZXIiLCJzZXJpYWwiOiIwZDU3NDlkZC02ODBkLTM3NTItYTAxNy1mYzQ1ZTI5MmFmMDUiLCJzc24iOiIyMjEwMDUyMzE5IiwiY2xpZW50X2lkIjoiMTIzIiwic2NvcGVzIjpbImFjY291bnQtc3VwZXIiLCJzc28tbWFuYWdlci1jdXN0b21lciIsImN1c3RvbWVyLXN1cGVyIiwiaGFtYmFtLXB1c2gtbm90aWZpY2F0aW9uLXNlY3VyZSJdfQ==.QgmTp72PuZvMaPqOMj_-QF5qfv7zxo30sb9AIdYqgpw";
//    protected String pichakTokenProd_clientCredential = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJncmFudCI6IkNMSUVOVCIsImlzcyI6Imh0dHA6Ly9hcGkuYm1pLmlyL3NlY3VyaXR5IiwiYXVkIjoibWljcm9zZXJ2aWNlLWJhYW0tY2hlcXVlLWNsaWVudCIsImV4cCI6MTcwNDk1OTY0NjQwNiwibmJmIjoxNzA0ODczMjQ2NDA2LCJyb2xlIjoiIiwic2VyaWFsIjoiNzg1NTQzOGUtYTA2ZC0zZTQxLTlkNDktZjlmYjk0NzRjOGI1Iiwic3NuIjoiMTQ5MiIsImNsaWVudF9pZCI6IjE0OTIiLCJzY29wZXMiOlsiY2hlcXVlLXNheWFkLXJlcG9ydCIsInN2Yy1tZ210LXNhbWF0LWlucS1tYWluIiwic3ZjLW1nbXQtc2FtYS1jaGVxLWJvdW4tY3VzdC1pbmZvLWlucSIsImNoZXF1ZS1jaGFrYWQtaXNzdWUiLCJjaGVxdWUtY2hha2FkLWFjdGl2YXRpb24iLCJzYXlhZGNoZXF1ZS1jaGVxdWVib29raW5xdWlyeSIsImNvcmVjaGVxdWUtY2hlcXVlbGVhZmlucXVpcnkiLCJjaGVxdWUtY2hha2FkLWRlYWN0aXZhdGlvbiIsImNoZXF1ZS1jaGFrYWQtY2hhbGxlbmdlY29kZSIsImNoZXF1ZS1zYXlhZC1yZXF1ZXN0IiwiY2hlcXVlLWNoYWthZC1jYXJ0YWJsZSIsImNoZXF1ZS1jaGFrYWQtdHJhbnNmZXIiLCJjaGVxdWUtY2hha2FkLWlucXVpcnlzdGF0dXMiLCJjaGVxdWUtYWxsb2NhdGluZy1lc3RlbGFtIiwic3ZjLW1nbXQtcGljaGFrIl19.jB8HrBuv45xHVchXVS-mXLyCqO2-axfvOqq95LdF8nA";


    @Value("${chakad.amount-for-reason}")
    protected static final Long AMOUNT_FOR_REASON = 0L;

    private final HttpServletRequest httpServletRequest;
    private final SsoClientTokenManager ssoClientTokenManager;

    @Value(value = "${chakad.namad-product-uid}")
    protected Integer namadProductUid;

    @Value(value = "${chakad.legalStamp}")
    protected String legalStamp;

    @Value(value = "${chakad.requestType}")
    protected String requestType;

    @Value(value = "${chakad.sign-request}")
    protected Boolean coreSignRequest;

    /**
     * get ssn of user password-type token
     *
     * @return token
     */
    protected final String getSsn() {
        return getCurrentUserInfo().getBmiOAuth2User().getSsn();
    }

    /**
     * get token based on client credential
     *
     * @return token
     */
    protected final String getToken() {
        return ssoClientTokenManager.getClientToken();
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
//        if (userAgent.startsWith(ANDROID_AGENT))
//            return "004";
//        if (userAgent.startsWith(IOS_AGENT))
//            return "003";
        return " 001|mobile";
    }

    protected CustomerRequestDto getCustomerFromToken(String userSsn, Integer userType) {

        CustomerRequestDto userReq = new CustomerRequestDto();
        userReq.setIdCode(userSsn);
        userReq.setIdType(userType);
        return userReq;
    }


}
