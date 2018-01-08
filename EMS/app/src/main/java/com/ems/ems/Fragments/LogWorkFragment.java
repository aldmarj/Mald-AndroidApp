package com.ems.ems.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.WorkLogPojo.WorkLog;
import com.ems.ems.R;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 17/11/2017.
 */

public class LogWorkFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();


    Date startDate;
    Date endDate;
    String tempStartTime;
    String tempEndTime;
    String tempDate;
    Long startMillis;
    Long endMillis;

    //Array List for populating client spinner
    ArrayList<String> clientNames = new ArrayList<>();
    Map<String, String> clientNameAndID = new HashMap<>();

    //Input Fields
    Spinner spinner;
    EditText clientName, workDescription, postcode;
    Button startTime, endTime, date;

    //WorkLog POJO
    WorkLog worklog = new WorkLog();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_work,
                container, false);


        // Spinner element
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        //Find Input Fields By There ID
        clientName = view.findViewById(R.id.log_client_name);
        workDescription = view.findViewById(R.id.log_work_description);
        postcode = view.findViewById(R.id.log_client_postcode);
        startTime = view.findViewById(R.id.log_start_time);
        endTime = view.findViewById(R.id.log_end_time);
        date = view.findViewById(R.id.log_date);


        FloatingActionButton myFab = view.findViewById(R.id.add_work_entry);
        myFab.setOnClickListener(v -> validateWorkEntry());

        clientApiCall();

        return view;
    }

    private void validateWorkEntry() {

        String expectedPattern = "MM/dd/yyyyHH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

        tempStartTime = String.valueOf(startTime.getText());
        tempEndTime = String.valueOf(endTime.getText());
        tempDate = String.valueOf(date.getText());


        if (tempStartTime.contains("Start Time") && tempEndTime.contains("End Time") && tempDate.contains("Date")) {
            Toast toast = Toast.makeText(getActivity(), "Date and Time Error ", Toast.LENGTH_LONG);
            toast.show();
        } else {
            try {
                startDate = formatter.parse(tempDate + tempStartTime);
                endDate = formatter.parse(tempDate + tempEndTime);

                startMillis =  startDate.getTime();
                endMillis =  endDate.getTime();

                addWorkEntry();
            } catch (ParseException e) {

            }
        }



    }

    private void populateSpinner() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, clientNames);

        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);
    }

// Place the user input into  the worklog model to be serialized
    private void addWorkEntry() {

        Context context = getActivity();

        String token = PreferenceManager.getDefaultSharedPreferences(context).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(context).getString("BUSINESS_TAG", "Sorry Chap!");
        String username = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "Sorry Chap!");

        worklog.setClientId(clientNameAndID.get(spinner.getSelectedItem().toString()));
        worklog.setDescription(String.valueOf(workDescription.getText()));
        worklog.setStartTime(startMillis);
        worklog.setEndTime(endMillis);
        worklog.setUserName("ciusername");
        worklog.setBusinessTag(businessTag);

        Gson gson = new Gson();
        String body = gson.toJson(worklog);


        params.put("t", token);

        apiClient.getApiService().postWork(businessTag, params, body).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    CharSequence text = "Work Entry Logged";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (response.code() == 401) {
                    Log.d("401 response: ", String.format("Sorry Pal ant post anything today"));
                } else {
                    CharSequence text = "Why don't you like my DATA?!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });


    }

    public void clientApiCall() {

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");

        params.put("t", token);

        apiClient.getApiService().getClient(businessTag, params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                if (clients.size() != 0) {
                    for (Client item : clients) {
                        clientNames.add(item.getClientName());
                        clientNameAndID.put(item.getClientName(), String.valueOf(item.getClientId()));
                    }
                    populateSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}