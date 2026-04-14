package com.gknust.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateMath {
    public static long DateToUnix(String date){
        return LocalDate.parse(date)
                .atStartOfDay(ZoneId.of("UTC"))
                .toInstant()
                .getEpochSecond();
    }

    public static String UnixToDate(long unix){
        Instant time = Instant.ofEpochSecond(unix);
        return LocalDate.ofInstant(time, ZoneId.of("UTC")).toString();
    }
}
