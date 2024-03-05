package ir.co.sadad.cheque.utils;

import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import org.zalando.problem.Status;

public class ConverterHelper {

    public static Integer setUserType(String ssn) {
        switch (ssn.length()) {
            case 10:
                return 1;
            case 11:
                return 2;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return 3;
            default:
                throw new ChakadClientException("ssn.length.not.valid", Status.BAD_REQUEST);
        }
    }

    public static Integer sheetCount(Integer code) {
        switch (code) {
            case 0:
                return 10;
            case 1:
                return 25;
            case 2:
                return 50;
            case 3:
                return 100;
            default:
                throw new ChakadClientException("sheet.number.not.valid", Status.BAD_REQUEST);
        }
    }
}
