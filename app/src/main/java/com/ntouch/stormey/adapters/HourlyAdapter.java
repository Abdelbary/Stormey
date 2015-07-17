package com.ntouch.stormey.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ntouch.stormey.R;
import com.ntouch.stormey.Weather.HourlyWeather;

/**
 * Created by mahmoud on 7/3/15.
 */
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourViewHolder> {

    private Context mContext;
    private HourlyWeather[] mHourlyWeathers;

    public HourlyAdapter(Context context ,HourlyWeather[] hours){
        mHourlyWeathers = hours;
        mContext = context;
    }
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_iteam , null);

        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int i) {

        hourViewHolder.bindHour(mHourlyWeathers[i]);
    }

    @Override
    public int getItemCount() {
        return mHourlyWeathers.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder
        implements  View.OnClickListener{


        public TextView mTimeLabel;
        public TextView mTemperatureLabel;
        public TextView mSummeryLabel;
        public ImageView mIconImageView;


        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mSummeryLabel = (TextView) itemView.findViewById(R.id.summeryLabel);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.hourlyTemperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.hourlyIconImageView);

            itemView.setOnClickListener(this);
        }



        public void bindHour(HourlyWeather hour){
          mTimeLabel.setText(hour.getHour());
          mSummeryLabel.setText(hour.getSummary());
          mTemperatureLabel.setText(hour.getTemperature() + "");
          mIconImageView.setImageResource(hour.getIconId());


        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String condition = mSummeryLabel.getText().toString();
            String message = String.format("at %s it will be %s and %s " ,
                    time
                    ,temperature
                     ,condition);
            Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
        }
    }
}












