package com.ntouch.stormey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntouch.stormey.R;
import com.ntouch.stormey.Weather.Day;

/**
 * Created by mahmoud on 7/1/15.
 */
public class DayAdapter extends BaseAdapter {

    private Context mContext ;
    private Day[] mDays;



    public DayAdapter(Context context, Day[] days){
        mContext = context;
        mDays = days;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // not going to use this....used to tag items for easier referance
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            //brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item,null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel= (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel= (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];

        holder.iconImageView.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperature() + "");
        holder.dayLabel.setText(day.getDayOfTheWeek());

        return convertView;
    }

    private  static class ViewHolder{


            ImageView iconImageView;
            TextView dayLabel;
            TextView temperatureLabel;

    }








}
