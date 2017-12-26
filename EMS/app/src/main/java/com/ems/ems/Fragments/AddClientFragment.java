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
import com.ems.ems.API.Locations;
import com.ems.ems.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldma on 26/12/2017.
 */

public class AddClientFragment extends Fragment {
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_client,
                container, false);

        FloatingActionButton myFab = view.findViewById(R.id.add_client_fab);
        myFab.setOnClickListener(v -> addClient());

        return view;
    }

    private void addClient() {
        Client client = new Client();
        Locations location = new Locations();
        client.setClientName("Sally Cooper");
        client.setBusinessTag("cibusinesstag");
        location.setDescription("Office Stationary Supplier");
        location.setPostcode("PL4 8HY");

        Context context = getActivity();

        Gson gson = new Gson();
        String body = gson.toJson(client.getBusinessTag() + client.getClientName() + location.getDescription() + location.getPostcode());

        String token = PreferenceManager.getDefaultSharedPreferences(context).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(context).getString("BUSINESS_TAG", "Sorry Chap!");

        params.put("t", token);

        apiClient.getApiService().postClient(businessTag, params, body ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    CharSequence text = "New Client Added";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (response.code() == 401) {
                    Log.d("401 response: ", String.format("Sorry Pal cant post anything today"));
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
}
