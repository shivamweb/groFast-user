package com.wits.grofast_user.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wits.grofast_user.Adapter.AddresslistAdapter;
import com.wits.grofast_user.Adapter.ShowAllAddressAdapter;
import com.wits.grofast_user.MainActivity;
import com.wits.grofast_user.MainHomePage.HomePage;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDetails extends AppCompatActivity {
    RadioButton radioButton;
    AppCompatButton select_address, changeaddress, placeOrder;
    TextView newaddress, selectedaddressshow;
    RecyclerView address_list_recyclerView;
    AddresslistAdapter addresslistAdapter;
    List<Map<String, Object>> AddressItems;
    ImageView close;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.payment_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_payment_details);
        radioButton = findViewById(R.id.cash_on_Delivery_radiobtn);
        radioButton.setChecked(true);

        select_address = findViewById(R.id.selected_delivery_address_btn);
        changeaddress = findViewById(R.id.change_selected_address);
        selectedaddressshow = findViewById(R.id.selected_address_textview);
        placeOrder = findViewById(R.id.place_order);
        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressBottomSheet();
            }
        });

        changeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressBottomSheet();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderPlaceDialog();
            }
        });
    }

    private void OrderPlaceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.orderplacelayout, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        AppCompatButton continue_shopping = dialogView.findViewById(R.id.continue_shopping);
        continue_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(PaymentDetails.this, HomePage.class);
                intent.putExtra("openHomeFragment", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dailogbox_background);
        }
        dialog.show();
    }

    private void showAddressBottomSheet() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addressbottomsheet);

        newaddress = dialog.findViewById(R.id.bottomsheet_add_new_Address);
        close = dialog.findViewById(R.id.bottomsheet_close);

        //Item
        address_list_recyclerView = dialog.findViewById(R.id.bottom_sheet_address_list_recycleview);
        AddressItems = new ArrayList<>();
        loadAddresslistItems();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        address_list_recyclerView.setLayoutManager(linearLayoutManager);
        addresslistAdapter = new AddresslistAdapter(this, AddressItems, new AddresslistAdapter.OnDeliveryButtonClickListener() {
            @Override
            public void onDeliveryButtonClick(String address) {
                selectedaddressshow.setText(address);
                selectedaddressshow.setVisibility(View.VISIBLE);
                changeaddress.setVisibility(View.VISIBLE);
                select_address.setVisibility(View.GONE);
                dismissDialog();
            }
        });
        address_list_recyclerView.setAdapter(addresslistAdapter);
        newaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), AddAddress.class);
                startActivity(in);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void loadAddresslistItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Address", "94, Yogeshwar nagar, Gadkhol patiya, Ankleshwar, 393001");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Address", "B-119, Shaym villa, kapodara Road, Ankleshwar, 393001");

        AddressItems.add(item1);
        AddressItems.add(item2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}