package com.gknust;

public record postWeatherMethod(
        double[]latitude,
        double[]longitude,
        String[]daily,
        String[]timezone,
        String[]start_date,
        String[]end_date
)
{
    public postWeatherMethod(double[]latitude,double[]longitude,String[]day){
        this(latitude,longitude, new String[] {"temperature_2m_mean"}, new String[] {"auto"},day,day);
    }
}
