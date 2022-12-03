package ir.co.sadad.cheque.utils;

import ir.co.sadad.commons.calendar.DateUtil;
import ir.co.sadad.commons.calendar.JCalendar;
import ir.co.sadad.commons.calendar.TimeUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

    private DateConvertor() {}

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
}
