package com.ems.ems;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ClientInfoFragment extends Fragment {
    ClientInfoAdapter clientInfoAdapter = new ClientInfoAdapter();
    List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_info, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_client_info);
        recyclerView.setAdapter(clientInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for(int i = 0; i < 50; i++){
            items.add("Client Info " + i);
        }

        clientInfoAdapter.setItems(items);
        return view;



    }
}
