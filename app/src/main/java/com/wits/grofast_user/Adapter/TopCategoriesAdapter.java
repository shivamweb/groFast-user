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

public class TopCategoriesAdapter extends RecyclerView.Adapter<TopCategoriesAdapter.ViewHolders> {
    private List<Map<String, Object>> TopStoreItems;

    public TopCategoriesAdapter(List<Map<String, Object>> TopStoreItems) {
        this.TopStoreItems = TopStoreItems;
    }


    @NonNull
    @Override
    public TopCategoriesAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_categories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopCategoriesAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = TopStoreItems.get(position);
        holder.Name.setText((String) item.get("Name"));
        holder.Banner.setImageResource((int) item.get("image"));

        holder.Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return TopStoreItems.size();
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
