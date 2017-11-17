package com.ems.ems;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("EMS - Dashboard");

        dashboardFragmentView();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setSelectedItemId(R.id.action_dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_log_work:
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


    

