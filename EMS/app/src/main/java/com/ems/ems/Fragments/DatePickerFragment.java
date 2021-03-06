package com.ems.ems.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.ems.ems.Listeners.DateListener;
import com.ems.ems.Listeners.EndTimeListener;
import com.ems.ems.Listeners.StartTimeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateListener dateListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.dateListener = (DateListener) activity;

        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener and EndTimeListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        dateListener.dateSet(format.format(calendar.getTime()));
    }
}
