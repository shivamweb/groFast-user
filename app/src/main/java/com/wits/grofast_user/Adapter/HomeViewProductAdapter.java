package com.wits.grofast_user.Adapter;

import static com.wits.grofast_user.Api.RetrofitService.domain;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wits.grofast_user.Api.responseModels.ProductModel;
import com.wits.grofast_user.Details.ProductDetailActivity;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeViewProductAdapter extends RecyclerView.Adapter<HomeViewProductAdapter.ViewHolders> {
    private List<ProductModel> productList;
    private Context context;
    private String TAG="HomeViewProductAdapter";

    public HomeViewProductAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewProductAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_homepage_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewProductAdapter.ViewHolders holder, int position) {
        ProductModel item = productList.get(position);
        holder.name.setText(item.getName());
        holder.weight.setText(item.getQuantity().toString());
        holder.price.setText(item.getPrice().toString());
        Glide.with(context).load(domain+item.getImage()).placeholder(R.drawable.gobhi_image).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("Name",  item.getName());
                intent.putExtra("Weight",  item.getQuantity().toString());
                intent.putExtra("Price",  item.getRetail_price().toString());
                intent.putExtra("Description",item.getProduct_detail());
                intent.putExtra("image", domain+item.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
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
