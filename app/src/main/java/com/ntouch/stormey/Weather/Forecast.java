package com.ntouch.stormey.Weather;

import com.ntouch.stormey.R;

/**
 * Created by mahmoud on 6/30/15.
 */
public class Forecast {
    private CurrentWeather mCurrentWeather;
    private Day[] mDays;
    private HourlyWeather[] mHourlyWeathers;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public Day[] getDays() {
        return mDays;
    }

    public void setDays(Day[] days) {
        mDays = days;
    }

    public HourlyWeather[] getHourlyWeathers() {
        return mHourlyWeathers;
    }

    public void setHourlyWeathers(HourlyWeather[] hourlyWeathers) {
        mHourlyWeathers = hourlyWeathers;
    }

    public static int getIconId(String iconString) {
        int iconId = R.drawable.clear_day;

        if (iconString.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        } else if (iconString.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        } else if (iconString.equals("rain")) {
            iconId = R.drawable.rain;
        } else if (iconString.equals("snow")) {
            iconId = R.drawable.snow;
        } else if (iconString.equals("sleet")) {
            iconId = R.drawable.sleet;
        } else if (iconString.equals("wind")) {
            iconId = R.drawable.wind;
        } else if (iconString.equals("fog")) {
            iconId = R.drawable.fog;
        } else if (iconString.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        } else if (iconString.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        } else if (iconString.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
return  iconId;
    }
}
