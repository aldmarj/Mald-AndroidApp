package com.ems.ems;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientInfoFragment extends Fragment {
    ClientInfoAdapter clientInfoAdapter = new ClientInfoAdapter();
    List<String> items = new ArrayList<>();
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_info, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_client_info);
        recyclerView.setAdapter(clientInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        clientApiCall();

        for (int i = 0; i < 50; i++) {
            items.add("Client Info " + i);
        }

        clientInfoAdapter.setItems(items);
        return view;

    }

    public void clientApiCall() {

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        params.put("t", token);
        apiClient.getApiService().getClient(params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                Client client = response.body().get(0);
                Log.d("aldmars clients: ", String.format("clientName = %s, businessTag = %s, clientID = %s", client.getClientName(), client.getBusinessTag(), client.getClientID()));
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("aldmars clients: ", t.getMessage());
            }
        });
    }
}
