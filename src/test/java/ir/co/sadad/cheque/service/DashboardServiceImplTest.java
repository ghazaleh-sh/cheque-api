package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ReasonResponseDto;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest()
@ActiveProfiles(profiles = {"integration"})
class DashboardServiceImplTest {

    @Autowired
    private DashboardServiceImpl service;

    @Test
    public void shouldThrowExceptionWhenInputInBlank() {
        String type = "";
        assertThrows(GeneralException.class, () -> service.getReasons(type));
    }

    @Test
    public void shouldReturnReasons()
    {
        String type = "1";
        List<ReasonResponseDto> reasons = service.getReasons(type);
        assertEquals(19, reasons.size());
    }
}
