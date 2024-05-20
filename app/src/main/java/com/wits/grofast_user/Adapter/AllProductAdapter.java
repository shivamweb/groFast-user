package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Details.ProductDetailActivity;
import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolders> {
    private List<Map<String, Object>> AllproductItems;
    Context context;

    public AllProductAdapter(Context context, List<Map<String, Object>> AllproductItems) {
        this.context = context;
        this.AllproductItems = AllproductItems;
    }

    @NonNull
    @Override
    public AllProductAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = AllproductItems.get(position);
        holder.name.setText((String) item.get("Name"));
        holder.weight.setText((String) item.get("Weight"));
        holder.price.setText((String) item.get("Price"));
        holder.image.setImageResource((int) item.get("image"));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("Name", (String) item.get("Name"));
                intent.putExtra("Weight", (String) item.get("Weight"));
                intent.putExtra("Price", (String) item.get("Price"));
                intent.putExtra("image", (int) item.get("image"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AllproductItems.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, weight, price;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            weight = itemView.findViewById(R.id.product_weight);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}
