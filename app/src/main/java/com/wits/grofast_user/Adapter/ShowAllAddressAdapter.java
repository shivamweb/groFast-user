package com.wits.grofast_user.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

public class ShowAllAddressAdapter extends RecyclerView.Adapter<ShowAllAddressAdapter.ViewHolders> {
    private List<Map<String, Object>> AllAddress;
    private Context context;

    public ShowAllAddressAdapter(Context context, List<Map<String, Object>> AllAddress) {
        this.context = context;
        this.AllAddress = AllAddress;
    }

    @NonNull
    @Override
    public ShowAllAddressAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_address_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAddressAdapter.ViewHolders holder, int position) {
        Map<String, Object> item = AllAddress.get(position);
        holder.address.setText((String) item.get("Address"));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.add_address_layout, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        ImageView close = dialogView.findViewById(R.id.close_add_address);
        TextView add = dialogView.findViewById(R.id.add_address_textview);
        TextView edit = dialogView.findViewById(R.id.edit_address_textview);
        AppCompatButton savebutton = dialogView.findViewById(R.id.all_address_save);
        AppCompatButton editbutton = dialogView.findViewById(R.id.all_address_edit);

        edit.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
        savebutton.setVisibility(View.GONE);
        editbutton.setVisibility(View.VISIBLE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dailogbox_background);
        }
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return AllAddress.size();
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
