package com.wits.grofast_user.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wits.grofast_user.Adapter.AllCouponAdapter;
import com.wits.grofast_user.R;

public class Coupon extends AppCompatActivity {

    RecyclerView recyclerView;
    AllCouponAdapter allCouponAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        recyclerView = findViewById(R.id.all_coupon_recycleview);
    }
}