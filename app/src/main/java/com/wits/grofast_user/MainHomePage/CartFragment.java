package com.wits.grofast_user.MainHomePage;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.CartResentAddProductAdapter;
import com.wits.grofast_user.Adapter.TaxesChargesAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.CartInterface;
import com.wits.grofast_user.Api.responseClasses.AddToCartResponse;
import com.wits.grofast_user.Api.responseClasses.CartFetchResponse;
import com.wits.grofast_user.Api.responseModels.CartModel;
import com.wits.grofast_user.Api.responseModels.TaxAndCharge;
import com.wits.grofast_user.Details.Coupon;
import com.wits.grofast_user.Details.PaymentDetails;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    RecyclerView recyclerView_cart_resent_product, taxes_charges_cart_recycleview;
    CartResentAddProductAdapter cartItemsAdapter;
    TaxesChargesAdapter taxesChargesAdapter;
    //    List<Map<String, Object>> ResetItems;
    List<Map<String, Object>> TaxesItems;
    TextView additem, couponLink, tip20, tip30, tipother;
    LinearLayout additemlayout, showeditItemtextlayout, addcoponlayout, showeditCouponlayout, Taxeslayout, tiplayout;
    EditText additemedittext, coupontext, tipamount;
    AppCompatButton additembutton, addCouponbutton, checkout;

    private List<CartModel> cartModelList = new ArrayList<>();
    private List<TaxAndCharge> taxAndCharges = new ArrayList<>();
    private UserActivitySession userActivitySession;
    TextView additem;
    LinearLayout additemlayout, showeditItemtextlayout, addcartlayout, showeditCouponlayout, Taxeslayout;
    EditText additemedittext, coupontext;
    AppCompatButton additembutton, addCouponbutton;
    ImageView additemimage, couponimagechange, Taxesimage;
    LinearLayoutManager linearLayoutManager;
    private final String TAG = "CartFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_cart), 0, 0);
        }

        //Textview
        additem = root.findViewById(R.id.cart_add_more_item);
        couponLink = root.findViewById(R.id.cart_coupon_link);
        tip20 = root.findViewById(R.id.tip_20);
        tip30 = root.findViewById(R.id.tip_30);
        tipother = root.findViewById(R.id.tip_other);
        userActivitySession = new UserActivitySession(getContext());

        //Linear layout
        additemlayout = root.findViewById(R.id.cart_add_item_linearlayout);
        showeditItemtextlayout = root.findViewById(R.id.cart_add_item_show_detail_layout);
        addcoponlayout = root.findViewById(R.id.cart_add_coupon_layout);
        showeditCouponlayout = root.findViewById(R.id.cart_add_coupon_show_detail_layout);
        Taxeslayout = root.findViewById(R.id.cart_add_taxes_layout);
        tiplayout = root.findViewById(R.id.tip_layout);

        //Edittext
        additemedittext = root.findViewById(R.id.cart_add_item_edittext);
        coupontext = root.findViewById(R.id.cart_add_coupon_edittext);
        tipamount = root.findViewById(R.id.tip_amount_enter);

        //Button
        additembutton = root.findViewById(R.id.cart_add_item_button);
        addCouponbutton = root.findViewById(R.id.cart_add_coupon_button);
        checkout = root.findViewById(R.id.checkout);

        //Image view
        additemimage = root.findViewById(R.id.cart_add_item_image);
        couponimagechange = root.findViewById(R.id.cart_add_coupon_image);
        Taxesimage = root.findViewById(R.id.cart_add_taxes_image);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), PaymentDetails.class);
                startActivity(in);
            }
        });

        View.OnClickListener tipClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTipSelection();
                updateTipSelection((TextView) v);

                if (v == tipother) {
                    tipamount.setVisibility(View.VISIBLE);
                } else {
                    tipamount.setVisibility(View.GONE);
                }
            }
        };

        tip20.setOnClickListener(tipClickListener);
        tip30.setOnClickListener(tipClickListener);
        tipother.setOnClickListener(tipClickListener);


        Taxeslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taxes_charges_cart_recycleview.getVisibility() == View.VISIBLE) {
                    taxes_charges_cart_recycleview.setVisibility(View.GONE);
                    Taxesimage.setImageResource(R.drawable.hide_arrow);
                } else {
                    taxes_charges_cart_recycleview.setVisibility(View.VISIBLE);
                    Taxesimage.setImageResource(R.drawable.arrow_up);
                }
            }
        });

        additemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showeditItemtextlayout.getVisibility() == View.VISIBLE) {
                    showeditItemtextlayout.setVisibility(View.GONE);
                    additemimage.setImageResource(R.drawable.hide_arrow);
                } else {
                    showeditItemtextlayout.setVisibility(View.VISIBLE);
                    additemimage.setImageResource(R.drawable.arrow_up);
                }
            }
        });

        addcoponlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showeditCouponlayout.getVisibility() == View.VISIBLE) {
                    showeditCouponlayout.setVisibility(View.GONE);
                    couponLink.setVisibility(View.GONE);
                    couponimagechange.setImageResource(R.drawable.hide_arrow);
                } else {
                    showeditCouponlayout.setVisibility(View.VISIBLE);
                    couponLink.setVisibility(View.VISIBLE);
                    couponimagechange.setImageResource(R.drawable.arrow_up);
                }
            }
        });

        couponLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), Coupon.class);
                startActivity(in);
            }
        });

        //Resent Cart Item
        recyclerView_cart_resent_product = root.findViewById(R.id.fragment_cart_resent_product);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_cart_resent_product.setLayoutManager(linearLayoutManager);

        loadCartItems(null, null, null);

        //Taxes Charges cart item
        taxes_charges_cart_recycleview = root.findViewById(R.id.taxes_charges_cart_recycleview);
        TaxesItems = new ArrayList<>();
        loadTaxesItem();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        taxes_charges_cart_recycleview.setLayoutManager(linearLayoutManager);
        taxesChargesAdapter = new TaxesChargesAdapter(getContext(), TaxesItems);
        taxes_charges_cart_recycleview.setAdapter(taxesChargesAdapter);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentnav, new ProductFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }

    private void loadCartItems(Integer tip, Integer couponCode, String aditionalNote) {
        Call<CartFetchResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(CartInterface.class).fetchCartDetails(tip, couponCode, aditionalNote);

        call.enqueue(new Callback<CartFetchResponse>() {
            @Override
            public void onResponse(Call<CartFetchResponse> call, Response<CartFetchResponse> response) {
                if (response.isSuccessful()) {
                    CartFetchResponse cartFetchResponse = response.body();
                    cartModelList = cartFetchResponse.getCartModelList();

                    cartItemsAdapter = new CartResentAddProductAdapter(cartModelList, getContext());
                    recyclerView_cart_resent_product.setAdapter(cartItemsAdapter);
                } else handleApiError(TAG, response, getContext());
            }

            @Override
            public void onFailure(Call<CartFetchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void loadTaxesItem() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Gobhi vegitable haha");
        item1.put("SubName", "2000");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "Gobhi tomato");
        item2.put("SubName", "2000");

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Gobhi catego");
        item3.put("SubName", "2000");

        TaxesItems.add(item1);
        TaxesItems.add(item2);
        TaxesItems.add(item3);
    }

    private void resetTipSelection() {
        resetTip(tip20);
        resetTip(tip30);
        resetTip(tipother);
    }

    private void resetTip(TextView tipView) {
        tipView.setBackground(getResources().getDrawable(R.drawable.button_line));
        tipView.setTextColor(getResources().getColor(R.color.Login_theme));
        tipView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_currency_rupee_24, 0, 0, 0);
        tipView.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.Login_theme)));
    }

    private void updateTipSelection(TextView selectedTip) {
        selectedTip.setBackground(getResources().getDrawable(R.drawable.selected_text));
        selectedTip.setTextColor(getResources().getColor(R.color.white));
        selectedTip.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_currency_rupee_24, 0, 0, 0);
        selectedTip.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

        // Ensure the padding remains 20dp on left and right
        selectedTip.setPadding(
                getResources().getDimensionPixelSize(R.dimen.padding_left_right),
                selectedTip.getPaddingTop(),
                getResources().getDimensionPixelSize(R.dimen.padding_left_right),
                selectedTip.getPaddingBottom()
        );
    }
}