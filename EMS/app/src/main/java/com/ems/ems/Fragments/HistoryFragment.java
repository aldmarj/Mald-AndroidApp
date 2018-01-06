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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.WorkLogPojo.WorkLog;
import com.ems.ems.Adapters.HistoryAdapter;
import com.ems.ems.R;
import com.ems.ems.Utils.DateUtils;

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

    long startMonth = DateUtils.getStartOfCurrentMonthInMillis();
    long endMonth = DateUtils.getEndOfCurrentMonthInMillis();
    long startWeek = DateUtils.getStartOfCurrentDayInMillis();
    long endWeek = DateUtils.getEndOfCurrentMonthInMillis();
    long startDay = DateUtils.getStartOfCurrentDayInMillis();
    long endDay = DateUtils.getEndOfCurrentDayInMillis();

    RadioGroup radioGroup;
    String thisMonth = "This Month";
    String thisWeek = "This Week";
    String thisDay = "Today";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_history);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        WorkLogApiCall(startMonth, endMonth);

        radioGroup = view.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            RadioButton rb = group.findViewById(checkedId);
            if (null != rb && checkedId > -1) {
                Toast.makeText(getActivity(), rb.getText(), Toast.LENGTH_SHORT).show();
                switch (checkedId) {
                    case R.id.radio_month:
                        WorkLogApiCall(startMonth, endMonth);
                        break;
                    case R.id.radio_week:
                        WorkLogApiCall(startMonth, endMonth);
                        break;
                    case R.id.radio_day:
                        WorkLogApiCall(startDay, endDay);
                        break;
                }


            }

        });

        return view;
    }


    public void WorkLogApiCall(long start, long end) {

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");


        params.put("t", token);

        apiClient.getApiService().getWorkLog(businessTag, start, end, params).enqueue(new Callback<List<WorkLog>>() {
            @Override
            public void onResponse(Call<List<WorkLog>> call, Response<List<WorkLog>> response) {
                List<WorkLog> workLog = response.body();
                getClientName();
                historyAdapter.setItems(workLog);
            }

            @Override
            public void onFailure(Call<List<WorkLog>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }

    public void getClientName() {
        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");

        params.put("t", token);

        apiClient.getApiService().getClient(businessTag, params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                for (Client client : clients) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(String.valueOf(client.getClientId()), client.getClientName()).apply();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }
}
