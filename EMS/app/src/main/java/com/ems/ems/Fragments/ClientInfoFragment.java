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
import com.ems.ems.API.GooglePojo.GeoLocation;
import com.ems.ems.API.GoogleAPIClient;
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
    private GoogleAPIClient googleApiClient = new GoogleAPIClient();
    private Map<String, String> params = new HashMap<>();

    String latLng;
    String lat;
    String lng;



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
        String businessTag = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BUSINESS_TAG", "Sorry Chap!");

        params.put("t", token);

        apiClient.getApiService().getClient(businessTag, params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                    clientInfoAdapter.setItems(clients);
                for(Client client : clients)
                {
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString(client.getClientID(), client.getClientName()).apply();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(int position, String clientLocation) {

        googleLatLong(clientLocation);

        Intent mapsIntent = new Intent(getActivity(), MapsActivity.class);
        startActivity(mapsIntent);

    }

    private void googleLatLong(String clientLocation) {


        googleApiClient.getApiService().getLatLong().enqueue(new Callback<GeoLocation>() {
            @Override
            public void onResponse(Call<GeoLocation> call, Response<GeoLocation> response) {
                Log.d("Google API Success: ", "We're In");
                GeoLocation geoLocation = response.body();
                lat = String.valueOf(geoLocation.getResults().get(0).getGeometry().getLocation().getLat());
                lng = String.valueOf(geoLocation.getResults().get(0).getGeometry().getLocation().getLng());;
                latLng = lat + lng;
            }

            @Override
            public void onFailure(Call<GeoLocation> call, Throwable t) {
                Log.d("Google API error: ", t.getMessage());
            }
        });
    }
}
