package com.wits.grofast_user.Adapter;

import static android.service.controls.ControlsProviderService.TAG;
import static com.wits.grofast_user.CommonUtilities.formatDate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Api.responseModels.CouponModel;
import com.wits.grofast_user.R;

import java.util.List;
import java.util.Map;

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
        holder.status.setText(getStatusText(item.getStatus()));
        setStatusColor(holder.status, item.getStatus());
        holder.description.setText(item.getDescription());
        String StartDate = formatDate(item.getFrom(), "yyyy-MM-dd", "dd-MM-yyyy");
        String EndDate = formatDate(item.getTo(), "yyyy-MM-dd", "dd-MM-yyyy");
        holder.from.setText(StartDate);
        holder.to.setText(EndDate);
//        String discountText = item.getDiscount();
//        if (item.getType() == 1) {
//            discountText += "%";
//        } else if (item.getType() == 2) {
//            discountText += "₹";
//        }
//        holder.discount.setText(discountText);
        holder.amout.setText(item.getMax_amount());
        holder.code.setText(item.getCode());
        Log.e("TAG", "onBindViewHolder: status : " + item.getStatus());
        Log.e("TAG", "onBindViewHolder: type : " + item.getType());
    }

    private String getStatusText(int status) {
        switch (status) {
            case 1:
                return context.getString(R.string.Active);
            case 2:
                return context.getString(R.string.Inactive);
            case 3:
                return context.getString(R.string.Expired);
            default:
                return "Unknown";
        }
    }

    private void setStatusColor(TextView textView, int status) {
        switch (status) {
            case 1:
                textView.setTextColor(context.getResources().getColor(R.color.active));
                break;
            case 2:
                textView.setTextColor(context.getResources().getColor(R.color.inactive));
                break;
            case 3:
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
