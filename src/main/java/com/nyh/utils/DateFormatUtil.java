package com.nyh.utils;


import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String format(Date date){
        return format.format(date);
    }

    public long toTime(String formatString){
        try {
            return format.parse(formatString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    只取当前日期年月日的java.util.Date(时分秒清零)对象
    public Date formatYMD(Date date){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

//        System.out.println("formatdata-----"+cal1.getTime());
        return cal1.getTime();

    }

    //    只取当前日期下一天年月日的java.util.Date(时分秒清零)对象
    public Date formatYMD2(Date date){
        Calendar cal1 = Calendar.getInstance();
        date.setTime(date.getTime()+24*60*60*1000);
        cal1.setTime(date);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

//        System.out.println("formatdata-----"+cal1.getTime());
        return cal1.getTime();

    }
}
