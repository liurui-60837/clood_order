package com.imoc.order.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateF(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return simpleDateFormat.format(date);
    }
}
