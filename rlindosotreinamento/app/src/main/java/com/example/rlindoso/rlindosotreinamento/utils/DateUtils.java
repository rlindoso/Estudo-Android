package com.example.rlindoso.rlindosotreinamento.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class DateUtils {
    public static final String DATE_FORMAT = "YYYY-MM-DD";

    public static DateTime strToDate(String strDate) {
        try{
            DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
            return format.parseDateTime(strDate);
        } catch  (Exception e) {
            return DateTime.now();
        }
    }

    public static String dateToStr(DateTime date) {
        try {
            return date.toString(DATE_FORMAT);
        } catch  (Exception e) {
            return "1989-01-01";
        }

    }
}
