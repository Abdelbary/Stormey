package com.ntouch.stormey.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ntouch.stormey.R;
import com.ntouch.stormey.Weather.HourlyWeather;
import com.ntouch.stormey.adapters.HourlyAdapter;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HourlyForcastActivity extends Activity {

    private HourlyWeather[] mHours ;

  @InjectView(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forcast);
        ButterKnife.inject(this);

        Intent intent = getIntent();

        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHours = (HourlyWeather[]) Arrays.copyOf(parcelables, parcelables.length,HourlyWeather [].class);

        HourlyAdapter adapter = new HourlyAdapter(this , mHours);

        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setHasFixedSize(true);
    }






}
