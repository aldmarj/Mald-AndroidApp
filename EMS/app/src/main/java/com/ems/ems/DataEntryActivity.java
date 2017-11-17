package com.ems.ems;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class DataEntryActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_log_work);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_log_work:
                            // What to do??
                            break;
                        case R.id.action_history:
                            Intent historyIntent = new Intent(DataEntryActivity.this, HistoryActivity.class);
                            startActivity(historyIntent);
                        case R.id.action_dashboard:
                            Intent logWorkIntent = new Intent(DataEntryActivity.this, MainActivity.class);
                            startActivity(logWorkIntent);
                        case R.id.action_clients:
                            ClientInfoFragment clientInfoFragment = new ClientInfoFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_client, clientInfoFragment).commit();
                        case R.id.action_calendar:
                            Intent calendarIntent = new Intent(DataEntryActivity.this, CalendarActivity.class);
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
                Intent accountInfoIntent = new Intent(DataEntryActivity.this, AccountInfoActivity.class);
                startActivity(accountInfoIntent);
                return true;

            case R.id.sign_out:
                Intent signOutIntent = new Intent(DataEntryActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    public void cancelEntry(View view) {
    }

    public void saveEntry(View view) {
    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
