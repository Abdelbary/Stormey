package com.ntouch.stormey.Ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ntouch.stormey.R;
import com.ntouch.stormey.Weather.CurrentWeather;
import com.ntouch.stormey.Weather.Day;
import com.ntouch.stormey.Weather.Forecast;
import com.ntouch.stormey.Weather.HourlyWeather;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";

    private Forecast mForecast ;
    @InjectView(R.id.timeLabel)
    TextView mTimeLabel;
    @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
    @InjectView(R.id.humidityValue) TextView mHumidityValue;
    @InjectView(R.id.precipValue) TextView mPrecipValue;
    @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
    @InjectView(R.id.iconImageView) ImageView mIconImageView;
    @InjectView(R.id.refreshButton) ImageView mRefreshImageView;

    @InjectView(R.id.progressBar) ProgressBar mProgressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);


        mProgressBar.setVisibility(View.INVISIBLE);

        final double latitude =  30.798603;
        final double longitude = 30.998772;

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(latitude,longitude);
            }
        });

        
        Log.d(TAG,"the program load sucssfully");

        getForecast(latitude,longitude);

    }

    private void getForecast(double latitude ,double longitude) {
        String apiKey = "0adbc2516c11966673e7d0a5195a536d";

        String forecastUrl = "https://api.forecast.io/forecast/"+ apiKey
                +"/" +latitude + "," + longitude;


        if(NetworkIsAvailable()){
            toggleRefresh() ;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {


                @Override
                public void onFailure(Request request, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh() ;
                        }
                    });
                    alertUserAboutError();


                }



                @Override
                public void onResponse(Response response) throws IOException {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           toggleRefresh() ;
                       }
                   });

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()){

                            mForecast = getForecastDetails(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });


                        }else{
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v(TAG ,"Exception caught" ,e);
                    }catch(JSONException e){
                        Log.v(TAG ,"Exception caught" ,e);
                    }


                }
            });

        }else{
            Toast.makeText(this, getString(R.string.Network_unavailable_Message), Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
    }
    //new method


    private void updateDisplay() {
        CurrentWeather currentWeather = mForecast.getCurrentWeather();
        mTemperatureLabel.setText(currentWeather.getTemperature() + "");
        mTimeLabel.setText("At " + currentWeather.getFormatedTime() + " it will be");
        mHumidityValue.setText(currentWeather.getHumidity() + "");
        mPrecipValue.setText(currentWeather.getPrecipChance() + "%");
        mSummaryLabel.setText(currentWeather.getSummary());
       Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
        mIconImageView.setImageDrawable(drawable);

    }

    private Forecast getForecastDetails(String jsonData) throws  JSONException{

        Forecast forecast = new Forecast();

        forecast.setCurrentWeather(getCurrentForecast(jsonData));
        forecast.setHourlyWeathers(getHourlyForecast(jsonData));
        forecast.setDays(getDailyForecast(jsonData));

        return forecast;
    }



    private HourlyWeather[] getHourlyForecast(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");
        HourlyWeather[] hourlyWeathers = new HourlyWeather[data.length()];



        for (int i=0 ; i<data.length() ; i++){
            JSONObject jsonHour = data.getJSONObject(i);
            HourlyWeather hourlyWeather = new HourlyWeather();

            hourlyWeather.setTimeZone(timeZone);
            hourlyWeather.setTemperature(jsonHour.getDouble("temperature"));
            hourlyWeather.setTime(jsonHour.getLong("time"));
            hourlyWeather.setIcon(jsonHour.getString("icon"));
            hourlyWeather.setSummary(jsonHour.getString("summary"));

            hourlyWeathers[i] = hourlyWeather;
        }

        return hourlyWeathers ;
    }



    private Day[] getDailyForecast(String jsonData)throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
          JSONObject daily = forecast.getJSONObject("daily");
          JSONArray data = daily.getJSONArray("data");



        Day[] days = new Day[data.length()];

        for (int i=0 ; i <data.length() ; i++){
            JSONObject  jsonDay = data.getJSONObject(i);
            Day day = new Day();

            day.setTimeZone(timeZone);
            day.setTemperature(jsonDay.getDouble("temperatureMax"));
            day.setTime(jsonDay.getLong("time"));
            day.setIcon(jsonDay.getString("icon"));
            day.setSummary(jsonDay.getString("summary"));

            days[i] = day;
        }

        return days ;

    }




    private CurrentWeather getCurrentForecast(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("currently");
        String timeZone = forecast.getString("timezone");
        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setTimeZone(timeZone );

        Log.d(TAG ,currentWeather.getFormatedTime());

         Log.v(TAG,"From JSON" + timeZone);

        return currentWeather;
    }

    private boolean NetworkIsAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");

    }

    @OnClick(R.id.dailyWeatherButton)
    public void startDailyActivity(View view){
        Intent intent = new Intent(this,DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST,mForecast.getDays());
        startActivity(intent);
    }

    @OnClick(R.id.HourlyWeatherButton)
    public void startHourlyActivity(View view){
        Intent intent = new Intent(this,HourlyForcastActivity.class);
        intent.putExtra(HOURLY_FORECAST,mForecast.getHourlyWeathers());
        startActivity(intent);
    }

}
