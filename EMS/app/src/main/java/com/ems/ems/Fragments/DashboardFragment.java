package com.ems.ems.Fragments;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.GooglePojo.GeoLocation;
import com.ems.ems.API.WeatherAPIClient;
import com.ems.ems.API.WeatherPojo.Weather;
import com.ems.ems.API.WeatherPojo.WeatherCard;
import com.ems.ems.R;
import com.github.pavlospt.CircleView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 15/11/2017.
 */

public class DashboardFragment extends Fragment {
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private WeatherAPIClient weatherApiClient = new WeatherAPIClient();
    private Map<String, String> params = new HashMap<>();

    //Views
    private TextView city;
    private TextView wind;
    private TextView humidity;
    private CircleView weatherResult;
    private CircleView weatherResultDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,
                container, false);

        city = view.findViewById(R.id.city_country);
        weatherResult = view.findViewById(R.id.weather_result);
        weatherResultDescription = view.findViewById(R.id.weather_result);
        wind = view.findViewById(R.id.wind_result);
        humidity = view.findViewById(R.id.humidity_result);

        WeatherForecast();

        return view;

    }

    private void WeatherForecast() {

        weatherApiClient.getApiService().getWeather().enqueue(new Callback<WeatherCard>() {
            @Override
            public void onResponse(Call<WeatherCard> call, Response<WeatherCard> response) {

                Log.d("Weather API Success: ", "We're In");
                WeatherCard weatherCard = new WeatherCard();
                weatherCard = response.body();
                city.setText((CharSequence) weatherCard.getName());
                weatherResult.setTitleText(String.valueOf(weatherCard.getMain().getTemp()));
                weatherResultDescription.setSubtitleText(weatherCard.getWeather().get(0).getDescription());
                wind.setText(String.valueOf(weatherCard.getWind().getSpeed()));
                humidity.setText(String.valueOf(weatherCard.getMain().getHumidity()));

            }

            @Override
            public void onFailure(Call<WeatherCard> call, Throwable t) {
                Log.d("Weather API error: ", t.getMessage());
            }
        });
    }
}
