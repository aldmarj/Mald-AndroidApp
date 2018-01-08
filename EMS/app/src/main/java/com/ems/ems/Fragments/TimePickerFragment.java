package com.ems.ems.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.ems.ems.Listeners.EndTimeListener;
import com.ems.ems.Listeners.StartTimeListener;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final int FLAG_START_TIME = 0;
    public static final int FLAG_END_TIME = 1;

    private int flag = 0;


    private StartTimeListener startTimeListener;
    private EndTimeListener endTimeListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.startTimeListener = (StartTimeListener)activity;
            this.endTimeListener = (EndTimeListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener and EndTimeListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void setFlag(int i) {
        flag = i;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);

        if(hourOfDay < 10) {
            time = "0" + Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        }
        if (minute < 10) {
            time = "0" + Integer.toString(hourOfDay) + ":" + "0" + Integer.toString(minute);
        }

        if (flag == FLAG_START_TIME) {
            this.startTimeListener.startTimeSet(time);
        }else if(flag == FLAG_END_TIME) {
            this.endTimeListener.endTimeSet(time);
        }



    }
}