package com.ntouch.stormey.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mahmoud on 6/30/15.
 */
public class Day implements Parcelable {
    private long mTime;
    private String mSummary;
    private String mIcon;
    private String mTimeZone;
    private double mTemperature;


public Day(){}

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

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime = new Date(mTime * 1000);
        return formatter.format(dateTime);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
        dest.writeString(mTimeZone);
    }


    //private method for parcel.
    private Day(Parcel in) {
        //all items must get out in the same order.
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();

    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel source) {

            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];

        }

    };
}
