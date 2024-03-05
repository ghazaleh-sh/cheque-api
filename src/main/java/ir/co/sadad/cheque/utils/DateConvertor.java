package ir.co.sadad.cheque.utils;

import ir.co.sadad.commons.calendar.DateUtil;
import ir.co.sadad.commons.calendar.JCalendar;
import ir.co.sadad.commons.calendar.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConvertor {

    private DateConvertor() {
    }

    public static Long convertJalaliToEpoch(Long jalaliDate) {
        if (jalaliDate != null && jalaliDate != 0) {
            return DateUtil.modifyDateFrom(String.valueOf(jalaliDate)).getTime();
        }
        return null;
    }

    public static Long convertEpochToJalali(Long epochDate) {
        if (epochDate != null) {
            final Date date = TimeUtil.getInstance().convertTimestampToDate(epochDate);
            String jalaliDate = JCalendar.gregorianToJalali(date);
            return Long.parseLong(jalaliDate.replace("/", ""));
        }
        return null;
    }

    public static String convertToEPOCH(Date date) {
        if (date == null) {
            date = new Date();
        }
        return String.valueOf(date.getTime());
    }

    public static Long stringDateToEpoch(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = df.parse(date);
        return convertedDate.getTime();
    }

    public static String ConvertCurrentToJalaliWithTime() {
        String jalaliDate = JCalendar.gregorianToJalali(new Date());
        String time = JCalendar.getTime(new Date());

        return jalaliDate + time;
    }

    public static String ConvertCurrentToJalali() {
        return JCalendar.gregorianToJalali(new Date());
    }

    public static String convertToUTC(String persianDate) {
        if (persianDate == null || persianDate.equals("0"))
            return null;
        try {
            Date gregorianDate = DateUtil.modifyDateFrom(persianDate);

            // Convert LocalDate to ZonedDateTime(UTC)
            ZoneId utcZone = ZoneId.of("UTC");
            ZonedDateTime utcDateTime = gregorianDate.toInstant().atZone(utcZone).toLocalDate().atStartOfDay(utcZone);

            // Format the result as a string
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return utcDateTime.format(outputFormatter);

        } catch (Exception e) {
            return persianDate;
        }
    }

    public static String convertCurrentToUTC() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return formatter.format(Instant.now().atZone(ZoneId.of("UTC")));
    }

    public static String convertISO8601ToJalali(String isoDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date convertedDate = df.parse(isoDate);
            return JCalendar.gregorianToJalali(convertedDate);
        } catch (Exception e) {
            return null;
        }
    }
}
