package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.ReasonResponseDto;
import ir.co.sadad.cheque.utils.DateConvertor;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import ir.co.sadad.commons.calendar.JCalendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest()
@ActiveProfiles(profiles = {"integration"})
class DashboardServiceImplTest {

    @Autowired
    private DashboardServiceImpl service;

    @Test
    public void shouldThrowExceptionWhenInputInBlank() {
        String type = "";
        assertThrows(GeneralException.class, () -> service.getReasons("ISSUE"));
    }

    @Test
    public void shouldReturnReasons() {
        String type = "1";
        List<ReasonResponseDto> reasons = service.getReasons("ISSUE");
        assertEquals(19, reasons.size());
    }

    @Test
    public void shouldReturnTrue() {
        String jalaliDate = JCalendar.gregorianToJalali(new Date());
        int min = 9;

        String output = jalaliDate
            + LocalDateTime.now().getHour()
            + (min < 10 ? (0 + min) : min)
            + LocalDateTime.now().getSecond();

        assertEquals("14020912130956", output);
    }

    @Test
    public void convertDateCorrectly() {
        String inputDate = "2024-04-30T20:29:59.999Z";
        assertEquals("14021228", DateConvertor.convertISO8601ToJalali(inputDate));
    }

    @Test
    public void encodeDescription(){
        String description = "توضیحات";
        assertEquals( "2KrZiNi224zYrdin2Ko=",Base64.getEncoder().encodeToString(description.getBytes()));
    }

    @Test
    public void shouldConvertCurrentToJalaliWithTime(){
        assertEquals("14030128112648", DateConvertor.ConvertCurrentToJalaliWithTime());
    }

    @Test
    public void ShouldConvertCurrentToUTC(){
        assertEquals("2024-04-16T06:57:07.494Z", DateConvertor.convertCurrentToUTC());
    }

    @Test
    public void ShouldConvertPersianToUTC(){
        assertEquals("2023-06-01T00:00:00.000Z", DateConvertor.convertToUTC("14020312"));
    }
}
