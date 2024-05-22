package com.wits.grofast_user.Adapter;

import static com.wits.grofast_user.Api.RetrofitService.domain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wits.grofast_user.Api.responseModels.CategoryModel;
import com.wits.grofast_user.R;

import java.util.List;

public class TopCategoriesAdapter extends RecyclerView.Adapter<TopCategoriesAdapter.ViewHolders> {
    private final String TAG = "TopCategoriesAdapter";
    private List<CategoryModel> categoryList;
    private Context context;

    public TopCategoriesAdapter(List<CategoryModel> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopCategoriesAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_categories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopCategoriesAdapter.ViewHolders holder, int position) {
        CategoryModel item = categoryList.get(position);
        holder.Name.setText(item.getCategory_name());
        Glide.with(context).load(domain + item.getImage()).placeholder(R.drawable.apple).into(holder.Banner);
        Log.e(TAG, "onBindViewHolder: category Image " + domain + item.getImage());
        holder.Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Banner;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.top_name);
            Banner = itemView.findViewById(R.id.top_image);
        }
    }
}
