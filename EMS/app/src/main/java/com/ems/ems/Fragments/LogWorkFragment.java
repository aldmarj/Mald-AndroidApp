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
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.Client;
import com.ems.ems.API.WorkLog;
import com.ems.ems.R;

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
        worklog.setClientID("1");
        worklog.setDescription("Hard days graft!");
        worklog.setStartTime("0");
        worklog.setEndTime("1");
        worklog.setUsername("ciusername");
        worklog.setBusinessTag("cibusinesstag");

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");


        params.put("t", token);

        apiClient.getApiService().postWork(businessTag, params, worklog).enqueue(new Callback<WorkLog>() {
            @Override
            public void onResponse(Call<WorkLog> call, Response<WorkLog> response) {
                Context context = getActivity();
                CharSequence text = "Yeeeeeeaaaaaa Boooiiii!!!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void onFailure(Call<WorkLog> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });


    }

    public void showDatePickerDialog(View view) {
    }
}
