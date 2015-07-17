package com.ntouch.stormey.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mahmoud on 6/30/15.
 */
public class HourlyWeather implements Parcelable{
    private long mTime;
    private String mSummary;
    private String mIcon;
    private  String mTimeZone;
    private double mTemperature;

    public  HourlyWeather(){}


    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }


    public String getHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(mTime * 1000);

        return formatter.format(date);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeLong(mTime);
      dest.writeString(mIcon);
      dest.writeString(mSummary);
      dest.writeString(mTimeZone);
      dest.writeDouble(mTemperature);

    }

    private     HourlyWeather(Parcel in ){
      mTime = in.readLong();
      mIcon = in.readString();
      mSummary = in.readString();
      mTimeZone = in.readString();
      mTemperature = in.readDouble();
    }


    public static final Creator<HourlyWeather> CREATOR = new Creator<HourlyWeather>() {
        @Override
        public HourlyWeather createFromParcel(Parcel source) {
            return new HourlyWeather(source);
        }

        @Override
        public HourlyWeather[] newArray(int size) {
            return new HourlyWeather[size];
        }
    };









}
