package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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
        holder.name.setText((String) item.get("Name"));
        holder.subname.setText((String) item.get("SubName"));
        holder.code.setText((String) item.get("Code"));
    }

    @Override
    public int getItemCount() {
        return AllCouponItems.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name, subname, code;
        AppCompatButton copy;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.all_coupon_name);
            subname = itemView.findViewById(R.id.all_coupon_subname);
            code = itemView.findViewById(R.id.all_coupon_code);
            copy = itemView.findViewById(R.id.all_coupon_copy);
        }
    }
}
