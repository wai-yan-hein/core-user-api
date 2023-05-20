/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.common;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author WSwe
 */
@Slf4j
public class Util1 {
    public static String isNull(String strValue, String value) {
        if (strValue == null) {
            return value;
        } else if (strValue.isEmpty()) {
            return value;
        } else {
            return strValue;
        }
    }
    public static String getString(Object value) {
        return value == null ? null : value.toString();
    }
    public static int getInteger(Object number) {
        int value = 0;
        if (number != null) {
            if (!number.toString().isEmpty()) {
                value = Integer.parseInt(number.toString());
            }
        }
        return value;
    }
    public static String toDateStr(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String strDate = null;

        try {
            strDate = formatter.format(date);
        } catch (Exception ex) {
            System.out.println("toDateStr Error : " + ex.getMessage());
        }

        return strDate;
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().isEmpty();
    }

    public static Date toDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String strDate = f2.format(date) + " " + now.getHour() + ":"
                + now.getMinute() + ":" + now.getSecond();
        try {
            date = formatter.parse(strDate);
        } catch (ParseException ex) {
            log.error(String.format("toDateTime: %s", ex.getMessage()));
        }
        return date;
    }

}
