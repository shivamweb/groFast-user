package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class TaxesChargesAdapter extends RecyclerView.Adapter<TaxesChargesAdapter.ViewHolders> {
    private List<Map<String, Object>> itemlist;
    private Context context;

    public TaxesChargesAdapter(Context context, List<Map<String, Object>> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public TaxesChargesAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_taxes_charges_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaxesChargesAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = itemlist.get(position);
        holder.name.setText((String) item.get("Name"));
        holder.subname.setText((String) item.get("SubName"));
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name,subname;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taxes_name);
            subname = itemView.findViewById(R.id.taxes_sub_name);
        }
    }
}
