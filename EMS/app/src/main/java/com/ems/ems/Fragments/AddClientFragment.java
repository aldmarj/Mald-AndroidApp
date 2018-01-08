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
import android.widget.EditText;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.ClientPojo.Locations;
import com.ems.ems.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldmar on 26/12/2017.
 */

public class AddClientFragment extends Fragment {
    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();
    private Map<String, String> params = new HashMap<>();

    EditText clientNameText, clientDescriptionText, clientPostcodeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_client,
                container, false);

        clientNameText = view.findViewById(R.id.log_client_name);
        clientDescriptionText = view.findViewById(R.id.log_client_description);
        clientPostcodeText = view.findViewById(R.id.log_client_postcode);

        FloatingActionButton myFab = view.findViewById(R.id.add_client_fab);
        myFab.setOnClickListener(v -> addClient());

        return view;
    }

    private void addClient() {
        Context context = getActivity();

        Client client = new Client();
        List<Locations> clientLocation = new ArrayList<>();
        Locations location = new Locations();

        String token = PreferenceManager.getDefaultSharedPreferences(context).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(context).getString("BUSINESS_TAG", "Sorry Chap!");



            client.setClientName(String.valueOf(clientNameText.getText()));
            client.setBusinessTag(businessTag);

            location.setDescription(String.valueOf(clientDescriptionText.getText()));
            location.setPostCode(String.valueOf(clientPostcodeText.getText()));

            clientLocation.add(location);

            client.setLocations(clientLocation);


        Gson gson = new Gson();
        String body = gson.toJson(client);



        params.put("t", token);

        apiClient.getApiService().postClient(businessTag, params, body).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    CharSequence text = "New Client Added";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (response.code() == 401) {
                    Log.d("401 response: ", String.format("cant post client"));
                } else {
                    CharSequence text = "Data not valid";
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
