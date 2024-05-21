package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class AllCouponAdapter extends RecyclerView.Adapter<AllCouponAdapter.ViewHolders> {
    private List<Map<String, Object>> AllCouponItems;
    private Context context;

    public AllCouponAdapter(Context context, List<Map<String, Object>> AllCouponItems) {
        this.context = context;
        this.AllCouponItems = AllCouponItems;
    }
    @NonNull
    @Override
    public AllCouponAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_coupon_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCouponAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = AllCouponItems.get(position);
    }

    @Override
    public int getItemCount() {
        return AllCouponItems.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
        }
    }
}
