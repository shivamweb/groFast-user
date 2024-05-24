package com.wits.grofast_user.Details;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wits.grofast_user.Adapter.RelatedProductAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productWeight, productPrice;
    RecyclerView recyclerView;
    RelatedProductAdapter relatedProductAdapter;
    List<Map<String, Object>> relatedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.product_details_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productWeight = findViewById(R.id.product_detail_weight);
        productPrice = findViewById(R.id.product_detail_price);
        recyclerView = findViewById(R.id.product_related_item_recycleview);

        if (getIntent() != null) {
            productName.setText(getIntent().getStringExtra("Name"));
            productWeight.setText(getIntent().getStringExtra("Weight"));
            productPrice.setText(getIntent().getStringExtra("Price"));
            Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).placeholder(R.drawable.gobhi_image).into(productImage);
        }

        relatedItems = new ArrayList<>();

        loadRelatedItems();

        //Product Item
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        relatedProductAdapter = new RelatedProductAdapter(this,relatedItems);
        recyclerView.setAdapter(relatedProductAdapter);
    }

    private void loadRelatedItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Apple");
        item1.put("Price", "500Kg");
        item1.put("image", R.drawable.apple);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "strawberry");
        item2.put("Price", "500Kg");
        item2.put("image", R.drawable.strawberry);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Apple");
        item3.put("Price", "500Kg");
        item3.put("image", R.drawable.apple);

        relatedItems.add(item1);
        relatedItems.add(item2);
        relatedItems.add(item3);
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