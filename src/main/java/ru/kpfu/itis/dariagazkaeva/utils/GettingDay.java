package ru.kpfu.itis.dariagazkaeva.utils;

import java.util.Calendar;

public class GettingDay {

    public String getDayOfCurrrentMonth(boolean first) {

        Calendar calendar = Calendar.getInstance();

        String year = calendar.get(Calendar.YEAR) + "";
        while (year.length() < 4) {
            year = "0" + year;
        }

        String month = calendar.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }

        String day;
        if (first) {
            day = "01";
        } else {
            day = calendar.get(Calendar.DATE) + "";
            if (day.length() < 2) {
                day = "0" + day;
            }
        }

        return year + "-" + month + "-" + day;
    }
}
