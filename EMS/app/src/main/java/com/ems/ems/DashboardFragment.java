package com.ems.ems;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aldma on 15/11/2017.
 */

public class DashboardFragment extends Fragment {
    DashboardAdapter dashboardAdapter = new DashboardAdapter();
    List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(dashboardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for(int i = 0; i < 50; i++){
            items.add("Dashboard " + i);
        }

        dashboardAdapter.setItems(items);
        return view;



    }
}
