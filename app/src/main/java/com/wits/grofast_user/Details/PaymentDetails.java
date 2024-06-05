package com.wits.grofast_user.Details;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.AddresslistAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.AddressInterface;
import com.wits.grofast_user.Api.interfaces.OrderInterface;
import com.wits.grofast_user.Api.responseClasses.AddressFetchResponse;
import com.wits.grofast_user.Api.responseClasses.OrderPlaceResponse;
import com.wits.grofast_user.Api.responseModels.AddressModel;
import com.wits.grofast_user.MainHomePage.HomePage;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.CartDetailSession;
import com.wits.grofast_user.session.UserActivitySession;
import com.wits.grofast_user.session.UserDetailSession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetails extends AppCompatActivity {
    RadioButton radioButton;
    AppCompatButton select_address, changeaddress, placeOrder;
    TextView newaddress, selectedaddressshow;
    RecyclerView address_list_recyclerView;
    AddresslistAdapter addresslistAdapter;
    ImageView close;
    Dialog dialog;
    private TextView customerName, customerNumber;
    private List<AddressModel> addressList = new ArrayList<>();
    private UserDetailSession userDetailSession;
    private UserActivitySession userActivitySession;
    private CartDetailSession cartDetailSession;
    private Integer selectedAddressId;
    private final String TAG = "PaymentDetails";

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

        userDetailSession = new UserDetailSession(getApplicationContext());
        userActivitySession = new UserActivitySession(getApplicationContext());
        cartDetailSession = new CartDetailSession(getApplicationContext());

        select_address = findViewById(R.id.selected_delivery_address_btn);
        changeaddress = findViewById(R.id.change_selected_address);
        selectedaddressshow = findViewById(R.id.selected_address_textview);
        placeOrder = findViewById(R.id.place_order);

        customerName = findViewById(R.id.payment_customer_name);
        customerNumber = findViewById(R.id.payment_customer_number);

        customerName.setText("Name : " + userDetailSession.getName());
        customerNumber.setText("Number : " + userDetailSession.getPhoneNo());

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
                placeOrder(cartDetailSession.getTotalAmount(), cartDetailSession.getCoupon(), cartDetailSession.getDiscount(), cartDetailSession.getDeleveryCharges(), cartDetailSession.getCgst(), cartDetailSession.getSgst(), Integer.parseInt(cartDetailSession.getTip()), cartDetailSession.getAditionalNote(), selectedAddressId, "amit", 123, 1);
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
        getCustomerAddress();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addressbottomsheet);

        newaddress = dialog.findViewById(R.id.bottomsheet_add_new_Address);
        close = dialog.findViewById(R.id.bottomsheet_close);

        //Item
        address_list_recyclerView = dialog.findViewById(R.id.bottom_sheet_address_list_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        address_list_recyclerView.setLayoutManager(linearLayoutManager);

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

    private void getCustomerAddress() {
        Call<AddressFetchResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(AddressInterface.class).fetchCustmerAddress();
        call.enqueue(new Callback<AddressFetchResponse>() {
            @Override
            public void onResponse(Call<AddressFetchResponse> call, Response<AddressFetchResponse> response) {
                if (response.isSuccessful()) {
                    AddressFetchResponse addressFetchResponse = response.body();
                    addressList = addressFetchResponse.getAddressList();
                    addresslistAdapter = new AddresslistAdapter(getApplicationContext(), addressList, new AddresslistAdapter.OnDeliveryButtonClickListener() {
                        @Override
                        public void onDeliveryButtonClick(String address, int addressId) {
                            selectedaddressshow.setText(address);
                            selectedaddressshow.setVisibility(View.VISIBLE);
                            changeaddress.setVisibility(View.VISIBLE);
                            select_address.setVisibility(View.GONE);
                            selectedAddressId = addressId;
                            dismissDialog();
                        }
                    });
                    address_list_recyclerView.setAdapter(addresslistAdapter);
                    Log.e(TAG, "onResponse: message : " + addressFetchResponse.getMessage());
                } else handleApiError(TAG, response, getApplicationContext());
            }

            @Override
            public void onFailure(Call<AddressFetchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void placeOrder(Integer totalAmount, String couponCode, float discount, int deleveryCharges, float cgst, float sgst, int tip, String aditionalNote, int addressId, String receiverName, Integer receiverPhone, int paymentMethod) {

        Call<OrderPlaceResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(OrderInterface.class).placeOrder(totalAmount, couponCode, discount, deleveryCharges, cgst, sgst, tip, aditionalNote, addressId, receiverName, receiverPhone, paymentMethod);

        call.enqueue(new Callback<OrderPlaceResponse>() {
            @Override
            public void onResponse(Call<OrderPlaceResponse> call, Response<OrderPlaceResponse> response) {
                if (response.isSuccessful()) {
                    OrderPlaceDialog();
                } else handleApiError(TAG, response, getApplicationContext());
            }

            @Override
            public void onFailure(Call<OrderPlaceResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}