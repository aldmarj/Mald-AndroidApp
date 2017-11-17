package com.ems.ems;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_history);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_log_work:
                            Intent logWorkIntent = new Intent(HistoryActivity.this, DataEntryActivity.class);
                            startActivity(logWorkIntent);
                        case R.id.action_history:
                            // What to do??
                            break;
                        case R.id.action_dashboard:
                            Intent historyIntent = new Intent(HistoryActivity.this, MainActivity.class);
                            startActivity(historyIntent);
                        case R.id.action_clients:
                            ClientInfoFragment clientInfoFragment = new ClientInfoFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_client, clientInfoFragment).commit();
                        case R.id.action_calendar:
                            Intent calendarIntent = new Intent(HistoryActivity.this, CalendarActivity.class);
                            startActivity(calendarIntent);
                    }
                    return true;
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_info:
                Intent accountInfoIntent = new Intent(HistoryActivity.this, AccountInfoActivity.class);
                startActivity(accountInfoIntent);
                return true;

            case R.id.sign_out:
                Intent signOutIntent = new Intent(HistoryActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
