package com.ems.ems.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.Client;
import com.ems.ems.API.WorkLog;
import com.ems.ems.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 17/11/2017.
 */

public class LogWorkFragment extends Fragment {
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_work,
                container, false);

        FloatingActionButton myFab = view.findViewById(R.id.add_work_entry);
        myFab.setOnClickListener(v -> addWorkEntry());

        return view;
    }



    private void addWorkEntry() {
        WorkLog worklog = new WorkLog();
        worklog.setClientId("1");
        worklog.setDescription("Hard days graft!");
        worklog.setStartTime("0");
        worklog.setEndTime("1");
        worklog.setUserName("ciusername");
        worklog.setBusinessTag("cibusinesstag");

        Context context = getActivity();

        String token = PreferenceManager.getDefaultSharedPreferences(context).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(context).getString("BUSINESS_TAG", "Sorry Chap!");

        Gson gson = new Gson();
        String body = gson.toJson(worklog);



        params.put("t", token);

        apiClient.getApiService().postWork(businessTag, params, body ).enqueue(new Callback<String>() {
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

    public void showDatePickerDialog(View view) {
    }

    public void showTimePickerDialog(View view){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(this.getFragmentManager(), "timePicker");
    }
}