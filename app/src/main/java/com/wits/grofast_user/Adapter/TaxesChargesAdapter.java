package com.wits.grofast_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Api.responseModels.TaxAndCharge;
import com.wits.grofast_user.Enums.TaxAndChargesEnum;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.CartDetailSession;

import java.util.List;

public class TaxesChargesAdapter extends RecyclerView.Adapter<TaxesChargesAdapter.ViewHolders> {
    private List<TaxAndCharge> taxAndCharges;
    private Context context;
    private CartDetailSession cartDetailSession;

    public TaxesChargesAdapter(Context context, List<TaxAndCharge> taxAndCharges) {
        this.context = context;
        this.taxAndCharges = taxAndCharges;
    }

    @NonNull
    @Override
    public TaxesChargesAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_taxes_charges_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaxesChargesAdapter.ViewHolders holder, int position) {
        cartDetailSession = new CartDetailSession(context);
        TaxAndCharge item = taxAndCharges.get(position);

        String text = item.getText();
        Float value = item.getValue();
        holder.name.setText(text);
        holder.subname.setText(value.toString());

        if (text.equals(TaxAndChargesEnum.CGST.getTag())) {
            cartDetailSession.setCgst(value);
        } else if (text.equals(TaxAndChargesEnum.SGST.getTag())) {
            cartDetailSession.setSgst(value);
        } else if (text.equals(TaxAndChargesEnum.DISCOUNT.getTag())) {
            cartDetailSession.setDiscount(value);
        } else if (text.equals(TaxAndChargesEnum.DELEVERYCHARGES.getTag())) {
            cartDetailSession.setDeleveryCharges(value);
        }
    }

    @Override
    public int getItemCount() {
        return taxAndCharges.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name,subname;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taxes_name);
            subname = itemView.findViewById(R.id.taxes_sub_name);
        }
    }
}
