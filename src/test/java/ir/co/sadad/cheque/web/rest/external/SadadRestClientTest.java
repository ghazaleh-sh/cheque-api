package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.web.rest.external.dto.request.GuaranteeInquiryRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class SadadRestClientTest {

    @Autowired
    SsoClientTokenManager ssoClientTokenManager;

    @Autowired
    SadadRestClient sadadRestClient;

    @Test
    void guaranteeInquiry() {
        sadadRestClient.guaranteeInquiry(
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJncmFudCI6IkNMSUVOVCIsImlzcyI6Imh0dHA6Ly9hcGkuYm1pLmlyL3NlY3VyaXR5IiwiYXVkIjoia2V5IiwiZXhwIjoxNjYxNzU1NTQyODk5LCJuYmYiOjE2NjE2NjkxNDI4OTksInJvbGUiOiIiLCJzZXJpYWwiOiI4ZGNhNzU1Ny0zMDg3LTM2Y2ItYjlhOC03MjUyYWFkOWQ1OGIiLCJzc24iOiIxMjMiLCJjbGllbnRfaWQiOiIxMjMiLCJzY29wZXMiOlsic3ZjLW1nbXQtZ3V0ci1ndWFyLWlucSJdfQ==.3BuI6368-PnRxfVE8RiHYMIyxxp8_PkUbO7J98YvKMQ",
            new GuaranteeInquiryRequestDto("REAL", "0061645753")
        );
    }
}
