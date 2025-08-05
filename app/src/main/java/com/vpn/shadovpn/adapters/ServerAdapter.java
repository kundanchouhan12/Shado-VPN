package com.vpn.shadovpn.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vpn.shadovpn.R;
import com.vpn.shadovpn.models.ServerModel;

import java.util.List;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ServerViewHolder> {

    private List<ServerModel> serverList;
    private Context context;
    private OnItemClickListener listener;

    public ServerAdapter(List<ServerModel> serverList, Context context) {
        this.serverList = serverList;
        this.context = context;
    }

    // ✅ Define interface
    public interface OnItemClickListener {
        void onItemClick(ServerModel serverModel);
    }

    // ✅ Setter method for listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_server, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServerViewHolder holder, int position) {
        ServerModel serverModel = serverList.get(position);

        holder.txtCountry.setText(serverModel.getCountryName());
        holder.txtPing.setText(serverModel.getPing() + " ms");

        int ping = serverModel.getPing();
        if (ping <= 70) {
            holder.txtPing.setTextColor(Color.parseColor("#4CAF50")); // Green
        } else if (ping <= 150) {
            holder.txtPing.setTextColor(Color.parseColor("#FFC107")); // Yellow
        } else {
            holder.txtPing.setTextColor(Color.parseColor("#F44336")); // Red
        }

        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(serverModel); // ✅ Call your listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return serverList.size();
    }

    public static class ServerViewHolder extends RecyclerView.ViewHolder {
        TextView txtCountry, txtPing;
        CardView cardView;

        public ServerViewHolder(View itemView) {
            super(itemView);
            txtCountry = itemView.findViewById(R.id.txtCountry);
            txtPing = itemView.findViewById(R.id.txtPing);
            cardView = (CardView) itemView;
        }
    }
}
