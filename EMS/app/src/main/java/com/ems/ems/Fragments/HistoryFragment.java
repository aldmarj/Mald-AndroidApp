package com.ems.ems.Fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.Client;
import com.ems.ems.API.WorkLog;
import com.ems.ems.Adapters.HistoryAdapter;
import com.ems.ems.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 17/11/2017.
 */

public class HistoryFragment extends Fragment {
    HistoryAdapter historyAdapter = new HistoryAdapter();
    List<String> items = new ArrayList<>();

    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_history);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        WorkLogApiCall();

       /* for(int i = 0; i < 50; i++){
            items.add("History " + i);
        }

        historyAdapter.setItems(items);*/
        return view;
    }

    public void WorkLogApiCall() {

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        params.put("t", token);
        apiClient.getApiService().getWorkLog(params).enqueue(new Callback<List<WorkLog>>() {
            @Override
            public void onResponse(Call<List<WorkLog>> call, Response<List<WorkLog>> response) {
                List<WorkLog> workLog = response.body();
                historyAdapter.setItems(workLog);
            }

            @Override
            public void onFailure(Call<List<WorkLog>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }
}
