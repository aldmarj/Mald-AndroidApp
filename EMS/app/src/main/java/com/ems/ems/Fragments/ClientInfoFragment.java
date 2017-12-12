package com.ems.ems.Fragments;

import android.content.Intent;
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

import com.ems.ems.API.APIClient;
import com.ems.ems.Activities.MapsActivity;
import com.ems.ems.API.Client;
import com.ems.ems.Adapters.ClientInfoAdapter;
import com.ems.ems.R;
import com.ems.ems.Adapters.ClickListeners.RecViewClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientInfoFragment extends Fragment implements RecViewClickListener {
    ClientInfoAdapter clientInfoAdapter = new ClientInfoAdapter();


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
        clientInfoAdapter.setClickListener(this);


        clientApiCall();


        return view;

    }

    public void clientApiCall() {

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "Sorry Chap!");
        params.put("t", token);
        apiClient.getApiService().getClient(params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                clientInfoAdapter.setItems(clients);

            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(int position, String clientLocation) {

        Intent mapsIntent = new Intent(getActivity(), MapsActivity.class);
        startActivity(mapsIntent);

    }
}
