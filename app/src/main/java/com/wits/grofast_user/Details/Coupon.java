package com.wits.grofast_user.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wits.grofast_user.Adapter.AllCouponAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coupon extends AppCompatActivity {

    RecyclerView recyclerView;
    AllCouponAdapter allCouponAdapter;
    List<Map<String, Object>> CouponItems;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.coupon_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_coupon);

        recyclerView = findViewById(R.id.all_coupon_recycleview);
        CouponItems = new ArrayList<>();

        loadCouponItems();

        //Coupon Item
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        allCouponAdapter = new AllCouponAdapter(this, CouponItems);
        recyclerView.setAdapter(allCouponAdapter);

    }

    private void loadCouponItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Ankleshwar");
        item1.put("SubName", "Bharuch");
        item1.put("Code", "4587452");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "Ankleshwar");
        item2.put("SubName", "Bharuch");
        item2.put("Code", "NNANAAJA");

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Ankleshwar");
        item3.put("SubName", "Bharuch");
        item3.put("Code", "hahahhaha");

        CouponItems.add(item1);
        CouponItems.add(item2);
        CouponItems.add(item3);
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