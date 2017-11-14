package com.ems.ems;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


    

