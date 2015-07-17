package com.ntouch.stormey.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mahmoud on 6/26/15.
 */
public class CurrentWeather {

    private double mHumidity;
    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mPrecipChance;
    private String mSummary;
    private  String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public double getHumidity() {
        return mHumidity;
    }
    public String  getFormatedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getDefault().getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }
    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }
    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getPrecipChance() {
        return Math.round(mPrecipChance*100);
    }

    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
