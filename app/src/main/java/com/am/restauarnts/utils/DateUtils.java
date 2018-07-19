package com.am.restauarnts.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatDate(Date date){
        if(date==null) return null;
        //yyyy-MM-dd hh:mm:ss a
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
        return  dateFormat.format(date);
    }
    public static Date parseDate(String time) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            final Date dateObj = sdf.parse(time);
            return  dateObj;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}
