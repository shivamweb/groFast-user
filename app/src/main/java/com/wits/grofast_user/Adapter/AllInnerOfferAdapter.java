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

import de.hdodenhof.circleimageview.CircleImageView;

public class AllInnerOfferAdapter extends RecyclerView.Adapter<AllInnerOfferAdapter.ViewHolders> {
    private List<Map<String, Object>> allinneroffersItem;
    private Context context;

    public AllInnerOfferAdapter(Context context, List<Map<String, Object>> allinneroffersItem) {
        this.context = context;
        this.allinneroffersItem = allinneroffersItem;
    }


    @NonNull
    @Override
    public AllInnerOfferAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_inner_offers_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllInnerOfferAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = allinneroffersItem.get(position);
        holder.image.setImageResource((int) item.get("image"));
    }

    @Override
    public int getItemCount() {
        return allinneroffersItem.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name, subname;
        ImageView image;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.inner_offer_image);
        }
    }

}
