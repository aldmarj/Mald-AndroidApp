package com.ems.ems.Fragments;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.EmployeePojo.Employee;
import com.ems.ems.API.GooglePojo.GeoLocation;
import com.ems.ems.API.WeatherAPIClient;
import com.ems.ems.API.WeatherPojo.Weather;
import com.ems.ems.API.WeatherPojo.WeatherCard;
import com.ems.ems.R;
import com.ems.ems.Utils.DateUtils;
import com.github.pavlospt.CircleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 15/11/2017.
 */

public class DashboardFragment extends Fragment {

    //Private API Client
    private APIClient apiClient = new APIClient();
    private WeatherAPIClient weatherApiClient = new WeatherAPIClient();
    private Map<String, String> params = new HashMap<>();

    //Views
    private TextView city;
    private TextView wind;
    private TextView humidity;
    private CircleView weatherResult;
    private CircleView weatherResultDescription;
    private ListView firstNameList, surNameList, hoursWorkedList;

    //Top Employee Array Lists
    private ArrayList<String> firstNameArray = new ArrayList<>();
    private ArrayList<String> surNameArray = new ArrayList<>();
    private ArrayList<String> hoursWorkedArray = new ArrayList<>();

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
        firstNameList = view.findViewById(R.id.top_employee_firstname);
        surNameList = view.findViewById(R.id.top_employee_surname);
        hoursWorkedList = view.findViewById(R.id.top_employee_hours_worked);

        weatherForecast();
        topEmployee();

        return view;

    }

    private void topEmployee() {
        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");

        long startMonth = DateUtils.getStartOfCurrentMonthInMillis();
        long endMonth = DateUtils.getEndOfCurrentMonthInMillis();

        params.put("t", token);

        apiClient.getApiService().getTopEmployees(businessTag, startMonth, endMonth, params).enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> employees = response.body();
                if (employees.size() != 0) {
                    for (Employee employee : employees) {
                        firstNameArray.add(employee.getFirstName());
                        surNameArray.add(employee.getSurName());
                        hoursWorkedArray.add(String.valueOf(employee.getHoursWorked()));
                    }

                    populateTopEmployeeList();

                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }

    private void populateTopEmployeeList() {
        ArrayAdapter<String> firstNameListArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, firstNameArray);
        firstNameList.setAdapter(firstNameListArrayAdapter);

        ArrayAdapter<String> surNameListArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, surNameArray);
        surNameList.setAdapter(surNameListArrayAdapter);

        ArrayAdapter<String> hoursWorkedListArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, hoursWorkedArray);
        hoursWorkedList.setAdapter(hoursWorkedListArrayAdapter);
    }

    private void weatherForecast() {

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
