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

import com.wits.grofast_user.Api.responseModels.AddressModel;
import com.wits.grofast_user.Details.EditAddress;
import com.wits.grofast_user.R;

import java.util.List;

public class ShowAllAddressAdapter extends RecyclerView.Adapter<ShowAllAddressAdapter.ViewHolders> {
    private List<AddressModel> addressList;
    private Context context;

    public ShowAllAddressAdapter(Context context, List<AddressModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public ShowAllAddressAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_address_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAddressAdapter.ViewHolders holder, int position) {
        AddressModel item = addressList.get(position);
        String customerAddress = item.getAddress() + "," + item.getCity() + "," + item.getPin_code();
        holder.address.setText(customerAddress);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, EditAddress.class);
                in.putExtra("address", item);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView address;
        ImageView edit;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.all_address_address);
            edit = itemView.findViewById(R.id.all_address_edit);
        }
    }
}
