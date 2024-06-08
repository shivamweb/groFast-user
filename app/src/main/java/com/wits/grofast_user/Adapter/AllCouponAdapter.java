package com.wits.grofast_user.Adapter;

import static com.wits.grofast_user.CommonUtilities.formatDate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Api.responseModels.CouponModel;
import com.wits.grofast_user.R;

import java.util.List;

public class AllCouponAdapter extends RecyclerView.Adapter<AllCouponAdapter.ViewHolders> {
    private List<CouponModel> AllCouponItems;
    private Context context;

    public AllCouponAdapter(Context context, List<CouponModel> AllCouponItems) {
        this.context = context;
        this.AllCouponItems = AllCouponItems;
    }

    @NonNull
    @Override
    public AllCouponAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_coupon_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCouponAdapter.ViewHolders holder, int position) {
        CouponModel item = AllCouponItems.get(position);
        holder.name.setText(item.getName());
        holder.status.setText(item.getStatus());
        setStatusColor(holder.status, item.getStatus());
        holder.description.setText(item.getDescription());
        String StartDate = formatDate(item.getFrom(), "yyyy-MM-dd", "dd-MM-yyyy");
        String EndDate = formatDate(item.getTo(), "yyyy-MM-dd", "dd-MM-yyyy");
        holder.from.setText(StartDate);
        holder.to.setText(EndDate);
        holder.amout.setText(item.getMax_amount());
        holder.code.setText(item.getCode());

        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", item.getCode());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, context.getString(R.string.toast_coupon_code_copied), Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("TAG", "onBindViewHolder: status : " + item.getStatus());
        Log.e("TAG", "onBindViewHolder: type : " + item.getType());
    }

    private void setStatusColor(TextView textView, String status) {
        switch (status) {
            case "Active":
                textView.setTextColor(context.getResources().getColor(R.color.active));
                break;
            case "Inactive":
                textView.setTextColor(context.getResources().getColor(R.color.inactive));
                break;
            case "Expired":
                textView.setTextColor(context.getResources().getColor(R.color.expired));
                break;
            default:
                textView.setTextColor(context.getResources().getColor(android.R.color.black));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return AllCouponItems.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name, description, from, to, code, discount, amout, status;
        AppCompatButton copy;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.all_coupon_name);
            description = itemView.findViewById(R.id.all_coupon_subname);
            from = itemView.findViewById(R.id.all_coupon_from);
            to = itemView.findViewById(R.id.all_coupon_to);
            code = itemView.findViewById(R.id.all_coupon_code);
            discount = itemView.findViewById(R.id.all_coupon_discount);
            amout = itemView.findViewById(R.id.all_coupon_amount);
            status = itemView.findViewById(R.id.all_coupon_status);
            copy = itemView.findViewById(R.id.all_coupon_copy);
        }
    }
}
