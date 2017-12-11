package com.ems.ems.Adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ems.ems.API.Client;
import com.ems.ems.Adapters.ClickListeners.RecViewClickListener;
import com.ems.ems.R;

import java.util.ArrayList;
import java.util.List;


public class ClientInfoAdapter extends RecyclerView.Adapter<ClientInfoAdapter.ViewHolder>{
    private List<Client> items = new ArrayList<>();
    private RecViewClickListener clickListener;

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

    public void setItems(List<Client> items) {
        notifyItemRangeRemoved(0, this.items.size());
        this.items.clear();
        this.items.addAll(items);
        notifyItemRangeInserted(0, this.items.size());
    }

    public void setClickListener(RecViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName;
        private TextView clientLocation;

        ViewHolder(View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            clientLocation = itemView.findViewById(R.id.client_location);

            FloatingActionButton myFab = itemView.findViewById(R.id.fab_find_location);
            myFab.setOnClickListener(v -> findClientLocation());
        }

        void bind(Client item){
            clientName.setText(item.getClientName());
            clientLocation.setText("PL4 8AA");
        }

        public void findClientLocation(){
            String clientPosition = (String) clientLocation.getText();
            if (clickListener != null) {
                clickListener.onClick(getAdapterPosition(),clientPosition);
            }
        }
    }



}
