package com.wits.grofast_user.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

public class WallethistoryAdapter extends RecyclerView.Adapter<WallethistoryAdapter.ViewHolders> {

    @NonNull
    @Override
    public WallethistoryAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_wallet_history_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallethistoryAdapter.ViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView orderid, status, created, point;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            orderid = itemView.findViewById(R.id.wallet_history_order_id);
            status = itemView.findViewById(R.id.wallet_history_status);
            created = itemView.findViewById(R.id.wallet_history_created_at);
            point = itemView.findViewById(R.id.wallet_history_points);
        }
    }
}
