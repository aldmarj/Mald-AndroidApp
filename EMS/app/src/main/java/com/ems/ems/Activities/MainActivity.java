package com.ems.ems.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ems.ems.Fragments.CalendarFragment;
import com.ems.ems.Fragments.ClientInfoFragment;
import com.ems.ems.Fragments.ClientLocationFragment;
import com.ems.ems.Fragments.DashboardFragment;
import com.ems.ems.Fragments.DatePickerFragment;
import com.ems.ems.Fragments.HistoryFragment;
import com.ems.ems.Fragments.LogWorkFragment;
import com.ems.ems.Fragments.TimePickerFragment;
import com.ems.ems.Listeners.DateListener;
import com.ems.ems.Listeners.EndTimeListener;
import com.ems.ems.Listeners.StartTimeListener;
import com.ems.ems.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements StartTimeListener, EndTimeListener, DateListener {
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createLocationRequest();
        currentLocation();

        dashboardFragmentView();




        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setSelectedItemId(R.id.action_dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_log_work:
                            logWorkFragment();
                            break;
                        case R.id.action_history:
                            historyFragmentView();
                            break;
                        case R.id.action_dashboard:
                            dashboardFragmentView();
                            break;
                        case R.id.action_clients:
                            clientFragmentView();
                            break;
                        case R.id.action_calendar:
                            calendarFragment();
                            break;
                    }
                    return true;
                });
    }

    public void dashboardFragmentView() {
        Toolbar dashboardToolbar = findViewById(R.id.my_toolbar);
        //dashboardToolbar.inflateMenu(R.menu.main_menu);
        setSupportActionBar(dashboardToolbar);
        dashboardToolbar.setTitle("EMS - Dashboard");

        DashboardFragment dashboardFragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit();
    }

    public void clientFragmentView() {
        Toolbar clientToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(clientToolbar);
        clientToolbar.setTitle("EMS - Clients");

        ClientInfoFragment clientInfoFragment = new ClientInfoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, clientInfoFragment).commit();
    }

    public void historyFragmentView() {
        Toolbar historyToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(historyToolbar);
        historyToolbar.setTitle("EMS - History");

        HistoryFragment historyFragment = new HistoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, historyFragment).commit();
    }

    public void logWorkFragment() {
        Toolbar logWorkToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(logWorkToolbar);
        logWorkToolbar.setTitle("EMS - Log Work");

        LogWorkFragment logWorkFragment = new LogWorkFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, logWorkFragment).commit();
    }

    public void calendarFragment() {

        Intent mapsIntent = new Intent(this, MapsActivity.class);
        startActivity(mapsIntent);
    }

    private void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void currentLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("CURRENTLAT", String.valueOf(location.getLatitude())).apply();
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("CURRENTLONG", String.valueOf(location.getLongitude())).apply();
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                PreferenceManager.getDefaultSharedPreferences(this).getAll().clear();


                Intent signOutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void showTimePickerDialog(View view) {
        int id = view.getId();
        TimePickerFragment newFragment = new TimePickerFragment();
        if (id == R.id.log_start_time) {
            newFragment.setFlag(TimePickerFragment.FLAG_START_TIME);
            newFragment.show(getSupportFragmentManager(), "timePicker");
        } else if (id == R.id.log_end_time) {
            newFragment.setFlag(TimePickerFragment.FLAG_END_TIME);
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void endTimeSet(String time) {
        Button textFragment = findViewById(R.id.log_end_time);
        textFragment.setText(time);
    }

    @Override
    public void startTimeSet(String time) {
        Button textFragment = findViewById(R.id.log_start_time);
        textFragment.setText(time);
    }

    @Override
    public void dateSet(String date) {
        Button textFragment = findViewById(R.id.log_date);
        textFragment.setText(date);
    }
}


    

