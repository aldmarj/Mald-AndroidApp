package com.ems.ems.Adapters;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ems.ems.API.WorkLogPojo.WorkLog;
import com.ems.ems.R;
import com.ems.ems.Utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.*;

/**
 * Created by aldmar on 17/11/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private List<WorkLog> items = new ArrayList<>();
    Context context;

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        context = parent.getContext();
        return new HistoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<WorkLog> items) {
        notifyItemRangeRemoved(0, this.items.size());
        this.items.clear();
        this.items.addAll(items);
        notifyItemRangeInserted(0, this.items.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName;
        private TextView workDescription;
        private TextView workStartTime;
        private TextView workEndTime;

        ViewHolder(View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            workDescription = itemView.findViewById(R.id.client_work_description);
            workStartTime = itemView.findViewById(R.id.worklog_start_time);
            workEndTime = itemView.findViewById(R.id.worklog_end_time);
        }

        void bind(WorkLog item) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            Date startDate = new Date(item.getStartTime());
            Date endDate = new Date(item.getEndTime());

            String startTime = dateFormat.format(startDate);
            String endTime = dateFormat.format(endDate);

            if (PreferenceManager.getDefaultSharedPreferences(context).contains(item.getClientId())) {
                clientName.setText(PreferenceManager.getDefaultSharedPreferences(context).getString(item.getClientId(), "Sorry Chap!"));
            }
            workDescription.setText(item.getDescription());
            workStartTime.setText(startTime);
            workEndTime.setText(endTime);

        }
    }
}
