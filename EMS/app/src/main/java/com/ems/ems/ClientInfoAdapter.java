package com.ems.ems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ClientInfoAdapter extends RecyclerView.Adapter<ClientInfoAdapter.ViewHolder>{
    private List<String> items = new ArrayList<>();

    @Override
    public ClientInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClientInfoAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_client_info_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ClientInfoAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<String> items) {
        notifyItemRangeRemoved(0, this.items.size());
        this.items.clear();
        this.items.addAll(items);
        notifyItemRangeInserted(0, this.items.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }

        void bind(String item){
            textView.setText(item);
        }
    }

}
