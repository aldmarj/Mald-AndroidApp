package com.ems.ems.Activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.ClientPojo.Client;
import com.ems.ems.API.ClientPojo.Locations;
import com.ems.ems.API.GoogleAPIClient;
import com.ems.ems.API.GooglePojo.GeoLocation;
import com.ems.ems.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private APIClient apiClient = new APIClient();
    private GoogleAPIClient googleApiClient = new GoogleAPIClient();
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> geoParams = new HashMap<>();

    ArrayList<String> clientName = new ArrayList<>();
    ArrayList<String> clientDescription = new ArrayList<>();
    ArrayList<String> clientPostcode = new ArrayList<>();
    ArrayList<Double> latArray = new ArrayList<>();
    ArrayList<Double> lngArray = new ArrayList<>();

    private Marker clientMarker;
    private static final int ZOOM_LEVEL = 15;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.my_toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        toolbar.setNavigationOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        clientName.clear();
        clientDescription.clear();
        clientPostcode.clear();

        clientApiCall();


    }

    private void clientApiCall() {


        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("TOKEN", "Sorry Chap!");
        String businessTag = PreferenceManager.getDefaultSharedPreferences(this).getString("BUSINESS_TAG", "Sorry Chap!");

        params.put("t", token);

        apiClient.getApiService().getClient(businessTag, params).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();

                for (Client item : clients) {
                    if (item.getLocations().size() != 0) {
                        clientName.add(item.getClientName());
                        clientDescription.add(item.getLocations().get(0).getDescription());
                        clientPostcode.add(item.getLocations().get(0).getPostCode());

                    }
                }
                googleLatLong();
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Client API error: ", t.getMessage());
            }
        });


    }

    private void googleLatLong() {

        for (String postcode : clientPostcode) {
            String tempPostcode = postcode.replaceAll("\\s+", "");

            geoParams.put("address", tempPostcode);
            geoParams.put("key", "AIzaSyBok17yEyr1SeNcldrxJrEbkHyDpImRhgg");
            googleApiClient.getApiService().getLatLong(geoParams).enqueue(new Callback<GeoLocation>() {
                @Override
                public void onResponse(Call<GeoLocation> call, Response<GeoLocation> response) {


                    Log.d("Google API Success: ", "We're In");
                    GeoLocation geoLocation = response.body();
                    if (geoLocation.getResults().size() != 0) {
                        double lat = geoLocation.getResults().get(0).getGeometry().getLocation().getLat();
                        double lng = geoLocation.getResults().get(0).getGeometry().getLocation().getLng();

                        latArray.add(geoLocation.getResults().get(0).getGeometry().getLocation().getLat());
                        lngArray.add(geoLocation.getResults().get(0).getGeometry().getLocation().getLng());


                        Log.d("Google API Location: ", "Lets ride it out");
                        Log.d("Google API Lat: ", String.valueOf(lat));
                        Log.d("Google API Long: ", String.valueOf(lng));
                    }
                    placeAMarkers();
                }

                @Override
                public void onFailure(Call<GeoLocation> call, Throwable t) {
                    Log.d("Google API error: ", t.getMessage());
                }
            });
        }

    }

    private void placeAMarkers() {
        if (latArray.size() == clientName.size()) {
            for (int i = 0; i < clientName.size(); i++) {
                String name = clientName.get(i);
                LatLng position = new LatLng(latArray.get(i), lngArray.get(i));
                mMap.addMarker(new MarkerOptions().position(position).title(name));

                if (i == 0) {
                    CameraPosition camPos = new CameraPosition(position, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));

                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
