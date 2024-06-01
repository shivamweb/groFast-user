package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class CartResentAddProductAdapter extends RecyclerView.Adapter<CartResentAddProductAdapter.ViewHolders> {
    private List<Map<String, Object>> cartitem;
    private Context context;

    public CartResentAddProductAdapter(Context context, List<Map<String, Object>> cartitem) {
        this.context = context;
        this.cartitem = cartitem;
    }

    @NonNull
    @Override
    public CartResentAddProductAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_cart_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartResentAddProductAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = cartitem.get(position);
        holder.product_name.setText((String) item.get("Name"));
        holder.product_price.setText((String) item.get("Price"));
        holder.product_image.setImageResource((int) item.get("image"));

        holder.addquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.totalquantity.getText().toString());
                holder.totalquantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        holder.removeqyantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.totalquantity.getText().toString());
                if (currentQuantity > 0) {
                    holder.totalquantity.setText(String.valueOf(currentQuantity - 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartitem.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        ImageView product_image, removeqyantity, addquantity;
        TextView product_name, product_price, totalquantity;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.cart_product_image);
            product_name = itemView.findViewById(R.id.cart_product_name);
            product_price = itemView.findViewById(R.id.cart_product_price);
            removeqyantity = itemView.findViewById(R.id.cart_remove_product_quantity);
            addquantity = itemView.findViewById(R.id.cart_add_product_quantity);
            totalquantity = itemView.findViewById(R.id.cart_total_product_quantity);
        }
    }
}
