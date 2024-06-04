package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class AllHistoryAdapter extends RecyclerView.Adapter<AllHistoryAdapter.ViewHolders> {
    private List<Map<String, Object>> historyItem;
    private Context context;

    public AllHistoryAdapter(Context context, List<Map<String, Object>> historyItem) {
        this.context = context;
        this.historyItem = historyItem;
    }

    @NonNull
    @Override
    public AllHistoryAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_history_fragment_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllHistoryAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = historyItem.get(position);
        holder.ProductOrderId.setText((String) item.get("Order_Id"));
        holder.ProductDate.setText((String) item.get("Date"));
        holder.ProductPrice.setText((String) item.get("Price"));

        List<Map<String, Object>> innerItems = (List<Map<String, Object>>) item.get("InnerData");
        AllInnerHistoryAdapter allInnerHistoryAdapter = new AllInnerHistoryAdapter(context, innerItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(allInnerHistoryAdapter);
        holder.recyclerView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return historyItem.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView ProductOrderId, ProductStatus, ProductInvoice, ProductDate, ProductPrice;
        LinearLayout ProductReorder;
        RecyclerView recyclerView;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            ProductOrderId = itemView.findViewById(R.id.history_product_order_id);
            ProductStatus = itemView.findViewById(R.id.history_product_status);
            ProductInvoice = itemView.findViewById(R.id.history_product_invoice);
            ProductDate = itemView.findViewById(R.id.history_product_date);
            ProductPrice = itemView.findViewById(R.id.history_product_price);
            ProductReorder = itemView.findViewById(R.id.history_product_reorder_layout);
            recyclerView = itemView.findViewById(R.id.history_product_inner_recycleview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
