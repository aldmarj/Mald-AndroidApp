package com.ems.ems.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ems.ems.Fragments.CalendarFragment;
import com.ems.ems.Fragments.ClientInfoFragment;
import com.ems.ems.Fragments.DashboardFragment;
import com.ems.ems.Fragments.HistoryFragment;
import com.ems.ems.Fragments.LogWorkFragment;
import com.ems.ems.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dashboardFragmentView();


        String returnToken;
        returnToken = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("TOKEN", "defaultStringIfNothingFound");

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
        Toolbar calendarToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(calendarToolbar);
        calendarToolbar.setTitle("EMS - Calendar");

        CalendarFragment calendarFragment = new CalendarFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calendarFragment).commit();
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
            case R.id.account_info:
                Intent accountInfoIntent = new Intent(MainActivity.this, AccountInfoActivity.class);
                startActivity(accountInfoIntent);
                return true;

            case R.id.sign_out:
                //SharedPreferences.Editor.remove("TOKEN", getApplicationContext().MODE_PRIVATE).commit();

                Intent signOutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


    

