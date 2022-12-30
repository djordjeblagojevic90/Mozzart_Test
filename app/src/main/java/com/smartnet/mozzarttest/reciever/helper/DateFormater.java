package com.smartnet.mozzarttest.reciever.helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormater {

    private static DateFormater instance;


    public static DateFormater getInstance() {
        if (instance == null) {
            instance = new DateFormater();
        }
        return instance;
    }


    /**
     * Helper to format date from log value what we got from server
     *
     * @return formated date
     */
    public String provideFormatedDrawTimeDate(long drawTime) {
        Date date = new Date(drawTime);
        Format format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }


    public String provideFormatedResultTime(long drawTime){
        Date date = new Date(drawTime);
        Format format = new SimpleDateFormat("MM.dd  hh:mm");
        return format.format(date);
    }

    /**
     * Helper to get reaming time for draw
     *
     * @return formated reaming time
     */
    public String provideFormatedReamingDrawTimeDate(long drawTime) {
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        Date dateReamaining = new Date(drawTime - currentTime.getTime());

        long oneHourTimeinMills = 60 * 60 * 1000;
        long oneSecTimeInMills = 1000;

        if (dateReamaining.getTime() - oneHourTimeinMills > 1) {
            dateReamaining.setTime(dateReamaining.getTime() - oneHourTimeinMills);
            Format format = new SimpleDateFormat("hh:mm:ss");
            return format.format(dateReamaining);
        } else {
            if (dateReamaining.getTime() - oneSecTimeInMills >= 0) {
                Format format = new SimpleDateFormat("mm:ss");
                return format.format(dateReamaining);
            } else {
                return "Times out";
            }
        }
    }
}
