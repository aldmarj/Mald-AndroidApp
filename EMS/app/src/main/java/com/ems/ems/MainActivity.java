package com.ems.ems;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView entryView,historyView,clientView,dashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}


    

