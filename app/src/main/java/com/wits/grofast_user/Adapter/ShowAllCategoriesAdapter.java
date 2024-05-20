package com.wits.grofast_user.Adapter;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowAllCategoriesAdapter extends RecyclerView.Adapter<ShowAllCategoriesAdapter.ViewHolders> {
    private List<Map<String, Object>> CategoriesItems;

    public ShowAllCategoriesAdapter(List<Map<String, Object>> CategoriesItems) {
        this.CategoriesItems = CategoriesItems;
    }
    @NonNull
    @Override
    public ShowAllCategoriesAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_categories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllCategoriesAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = CategoriesItems.get(position);
        holder.Name.setText((String) item.get("Name"));
        holder.Banner.setImageResource((int) item.get("image"));
    }

    @Override
    public int getItemCount() {
        return CategoriesItems.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Banner;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.all_categories_name);
            Banner = itemView.findViewById(R.id.all_categories_image);
        }
    }
}
