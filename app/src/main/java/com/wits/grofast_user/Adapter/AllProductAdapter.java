package com.wits.grofast_user.Adapter;

import static com.wits.grofast_user.Api.RetrofitService.domain;
import static com.wits.grofast_user.CommonUtilities.handleApiError;
import static com.wits.grofast_user.CommonUtilities.showToast;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.CartInterface;
import com.wits.grofast_user.Api.responseClasses.CartResponse;
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
        ProductModel item = productList.get(position);
        holder.name.setText(item.getName());
        holder.weight.setText(item.getQuantity().toString() + " " + item.getUnitName());
        holder.price.setText(item.getPrice().toString());
        Glide.with(context).load(domain + item.getImage()).placeholder(R.drawable.gobhi_image).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("Name", item.getName());
                intent.putExtra("Weight", item.getQuantity().toString() + " " + item.getUnitName());
                intent.putExtra("Price", item.getRetail_price().toString());
                intent.putExtra("Description", item.getProduct_detail());
                intent.putExtra("image", domain + item.getImage());
                Log.e("TAG", "onClick: weight : " + item.getQuantity());
                Log.e("TAG", "onClick: product id : " + item.getId());
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
                if (currentQuantity > 1) {
                    holder.total_product_quantity.setText(String.valueOf(currentQuantity - 1));
                }
            }
        });

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.total_product_quantity.getText().toString());
                int amount = item.getPrice();
                Addtocart(holder,item.getId(), amount, quantity);
                holder.btn_add_to_cart.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);
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
        private ProgressBar progressBar;

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
            progressBar = itemView.findViewById(R.id.loader_cart_item);
        }
    }

    private void Addtocart(ViewHolders holder, int id, int amount, int quantity) {
        UserActivitySession userActivitySession = new UserActivitySession(context);
        Call<CartResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(CartInterface.class).addToCart(id, amount, quantity);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    holder.btn_add_to_cart.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(View.GONE);
                    CartResponse cartResponse = response.body();
                    Log.e("Addtocart", "Product added to cart message :" + cartResponse.getMessage());
                    Log.e("Addtocart", "Product added to cart id : " + id);
                    Log.e("Addtocart", "Product added to cart amount : " + amount);
                    Log.e("Addtocart", "Product added to cart quantity :" + quantity);
                    showToast(context, cartResponse.getMessage());
                } else {
                    handleApiError(TAG, response, context);
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });

    }
}
