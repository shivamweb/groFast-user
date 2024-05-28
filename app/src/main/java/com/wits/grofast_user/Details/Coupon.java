package com.wits.grofast_user.Details;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wits.grofast_user.Adapter.AllCouponAdapter;
import com.wits.grofast_user.Adapter.AllProductAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.CouponInterface;
import com.wits.grofast_user.Api.interfaces.ProductInerface;
import com.wits.grofast_user.Api.paginatedResponses.CouponPaginationRes;
import com.wits.grofast_user.Api.paginatedResponses.ProductPaginatedRes;
import com.wits.grofast_user.Api.responseClasses.CouponResponse;
import com.wits.grofast_user.Api.responseClasses.ProductResponse;
import com.wits.grofast_user.Api.responseModels.CouponModel;
import com.wits.grofast_user.Api.responseModels.ProductModel;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Coupon extends AppCompatActivity {
    RecyclerView recyclerView;
    AllCouponAdapter allCouponAdapter;
    private List<CouponModel> couponModelList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    private final String TAG = "CouponActivity";
    private UserActivitySession userActivitySession;
    LinearLayout load_data;
    NestedScrollView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.coupon_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_coupon);

        recyclerView = findViewById(R.id.all_coupon_recycleview);
        load_data = findViewById(R.id.coupon_load);
        data = findViewById(R.id.scroll_coupon_data);

        userActivitySession = new UserActivitySession(getApplicationContext());
        load_data.setVisibility(View.VISIBLE);
        data.setVisibility(View.GONE);
        loadCouponItems();

        //Coupon Item
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void loadCouponItems() {
        Call<CouponResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(CouponInterface.class).fetchCoupon(1);
        call.enqueue(new Callback<CouponResponse>() {
            @Override
            public void onResponse(Call<CouponResponse> call, Response<CouponResponse> response) {
                load_data.setVisibility(View.GONE);
                data.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    CouponResponse couponResponse = response.body();
                    CouponPaginationRes couponPaginationRes = couponResponse.getCouponPaginationRes();
                    couponModelList = couponPaginationRes.getCouponList();
                    allCouponAdapter = new AllCouponAdapter(getApplicationContext(), couponModelList);
                    recyclerView.setAdapter(allCouponAdapter);
                    Log.i(TAG, "onResponse: total products " + couponPaginationRes.getTotal());
                    Log.i(TAG, "onResponse: fetched products " + couponPaginationRes.getTo());
                } else {
                    handleApiError(TAG, response, getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<CouponResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}