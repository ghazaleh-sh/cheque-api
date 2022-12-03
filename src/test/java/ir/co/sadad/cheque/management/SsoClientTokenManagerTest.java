package ir.co.sadad.cheque.management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class SsoClientTokenManagerTest {

    @Autowired
    SsoClientTokenManager ssoClientTokenManager;

    @Test
    void getClientTokenWithSuccessfulResult() {
        String result = ssoClientTokenManager.getClientToken();
        ssoClientTokenManager.setToken(null);
        String result2 = ssoClientTokenManager.getClientToken();

        assertFalse(result.isEmpty());
        assertNotEquals(result, result2);
    }

    @Test
    void getClientToken_whenTokenIsNull() {
        String resultBefore = ssoClientTokenManager.getClientToken();
        assertFalse(resultBefore.isEmpty());
        ssoClientTokenManager.setToken(null);
        String resultAfter = ssoClientTokenManager.getClientToken();
        assertFalse(resultAfter.isEmpty());
        assertFalse(resultAfter.equals(resultBefore));
    }

    @Test
    void getClientToken_whenTokenIsNotEmpty() {
        String resultBefore = ssoClientTokenManager.getClientToken();
        assertFalse(resultBefore.isEmpty());
        String resultAfter = ssoClientTokenManager.getClientToken();
        assertFalse(resultAfter.isEmpty());
        assertTrue(resultAfter.equals(resultBefore));
    }

    @Test
    void getClientToken_whenTokenIsNotExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 0, 18); //28-10-1400
        Date currentDate = calendar.getTime();
        //
        //        String resultBefore = ssoClientTokenManager.getClientToken();
        //        assertFalse(resultBefore.isEmpty());
        //        ssoClientTokenManager.setToken(null);
        //        String resultAfter = ssoClientTokenManager.getClientToken();
        //        assertFalse(resultAfter.isEmpty());
        //        assertFalse(resultAfter.equals(resultBefore));

    }
}
