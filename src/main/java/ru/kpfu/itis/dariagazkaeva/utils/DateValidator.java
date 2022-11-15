package ru.kpfu.itis.dariagazkaeva.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

//public class DateValidator {
//
//    public List<String> validateDate(String startDate, String endDate) {
//
//        List<String> errors = new ArrayList<>();
//
//        String pattern = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
//
//        if (!startDate.matches(pattern) || !endDate.matches(pattern)) {
//            errors.add("Дата должна быть в формате дд-мм-гггг");
//        }
//
//        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
//            errors.add("Дата должна быть заполнена");
//        }
//        return errors;
//    }
//}

public class DateValidator {

    public boolean validateDateRange(String startDate, String endDate) {

        if (!validateDate(startDate) || !validateDate(endDate)) return false;

        int startYear = Integer.parseInt(startDate.substring(0, 4));
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int startMonth = Integer.parseInt(startDate.substring(5, 7));
        int endMonth = Integer.parseInt(endDate.substring(5, 7));
        int startDay = Integer.parseInt(startDate.substring(8, 10));
        int endDay = Integer.parseInt(endDate.substring(8, 10));

        Calendar startCalendar = new GregorianCalendar(startYear, startMonth - 1, startDay);
        Calendar endCalendar = new GregorianCalendar(endYear, endMonth - 1, endDay);

        return (startCalendar.getTime().before(endCalendar.getTime()));
    }

    public boolean validateDate(String date) {

        String pattern = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
        return (date.matches(pattern));
    }

}
