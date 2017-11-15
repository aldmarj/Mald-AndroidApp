package com.ems.ems;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView entryView,historyView,clientView,dashboardView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_log_work:
                            Intent logWorkIntent = new Intent(MainActivity.this, DataEntryActivity.class);
                            startActivity(logWorkIntent);
                        case R.id.action_history:
                            Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
                            startActivity(historyIntent);
                        case R.id.action_dashboard:
                            // What to do??
                            break;
                        case R.id.action_clients:
                            Intent clientIntent = new Intent(MainActivity.this, ClientInfoActivity.class);
                            startActivity(clientIntent);
                        case R.id.action_calendar:
                            Intent calendarIntent = new Intent(MainActivity.this, CalendarActivity.class);
                            startActivity(calendarIntent);
                    }
                    return true;
                });


        init();
    }




    private void init() {
        entryView = (CardView) findViewById(R.id.entryView);
        historyView = (CardView) findViewById(R.id.historyView);
        clientView = (CardView) findViewById(R.id.clientView);
        dashboardView = (CardView) findViewById(R.id.dashboardView);


        entryView.setOnClickListener(this);
        historyView.setOnClickListener(this);
        clientView.setOnClickListener(this);
        dashboardView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.entryView:
                Intent intent = new Intent(MainActivity.this, DataEntryActivity.class);
                startActivity(intent);
                break;
            case R.id.historyView:
                break;
            case R.id.clientView:
                break;
            case R.id.dashboardView:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_info:
                // Take to account info activity
                return true;

            case R.id.sign_out:
                // sign out
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


    

