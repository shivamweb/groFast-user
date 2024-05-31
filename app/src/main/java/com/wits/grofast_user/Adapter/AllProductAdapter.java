package com.wits.grofast_user.Adapter;

import static com.wits.grofast_user.Api.RetrofitService.domain;
import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.AddToCartInterface;
import com.wits.grofast_user.Api.responseClasses.AddToCartResponse;
import com.wits.grofast_user.Api.responseModels.ProductModel;
import com.wits.grofast_user.Details.ProductDetailActivity;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolders> {
    private List<ProductModel> productList = new ArrayList<>();
    private Context context;
    ProductModel product;
    private final String TAG = "AllProductAdapter";

    public AllProductAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public AllProductAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolders holder, int position) {
         product = productList.get(position);
        holder.name.setText(product.getName());
        holder.weight.setText(product.getQuantity().toString() + " " + product.getUnitName());
        holder.price.setText(product.getPrice().toString());
        Glide.with(context).load(domain + product.getImage()).placeholder(R.drawable.gobhi_image).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("Name", product.getName());
                intent.putExtra("Weight", product.getQuantity().toString() + " " + product.getUnitName());
                intent.putExtra("Price", product.getRetail_price().toString());
                intent.putExtra("Description", product.getProduct_detail());
                intent.putExtra("image", domain + product.getImage());
                Log.e("TAG", "onClick: weight : " + product.getQuantity());
                Log.e("TAG", "onClick: id : " + product.getId());
                context.startActivity(intent);
            }
        });

        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.total_product_quantity.getText().toString());
                holder.total_product_quantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        holder.remove_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.total_product_quantity.getText().toString());
                if (currentQuantity > 0) {
                    holder.total_product_quantity.setText(String.valueOf(currentQuantity - 1));
                }
            }
        });

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addtocart(product.getId(),Integer.parseInt(holder.total_product_quantity.getText().toString()),1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {

        ImageView image, add_product, remove_product;
        TextView name, weight, price, total_product_quantity;
        AppCompatButton btn_add_to_cart;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            weight = itemView.findViewById(R.id.product_weight);
            price = itemView.findViewById(R.id.product_price);
            add_product = itemView.findViewById(R.id.add_product_quantity);
            remove_product = itemView.findViewById(R.id.remove_product_quantity);
            total_product_quantity = itemView.findViewById(R.id.total_product_quantity);
            btn_add_to_cart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }

    private void Addtocart(int id, int amount, int quantity) {
        UserActivitySession userActivitySession = new UserActivitySession(context);
        Call<AddToCartResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(AddToCartInterface.class).fetchcart(id,amount,quantity);
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                if (response.isSuccessful()){
                    AddToCartResponse cartResponse = response.body();
                    Log.d("Addtocart", "Product added to cart: " + cartResponse);
                } else {
                    handleApiError(TAG, response, context);
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {

            }
        });

    }
}
