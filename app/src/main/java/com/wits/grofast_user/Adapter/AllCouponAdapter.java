package com.wits.grofast_user.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllCouponAdapter extends RecyclerView.Adapter<AllCouponAdapter.ViewHolders> {
    @NonNull
    @Override
    public AllCouponAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCouponAdapter.ViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
        }
    }
}
