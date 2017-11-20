package com.ems.ems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aldmar on 17/11/2017.
 */

public class LogWorkFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_work,
                container, false);
        return view;
    }

    public void showDatePickerDialog(View view) {
    }
}
