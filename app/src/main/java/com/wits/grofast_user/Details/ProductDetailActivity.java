package com.wits.grofast_user.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wits.grofast_user.R;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productWeight, productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productWeight = findViewById(R.id.product_detail_weight);
        productPrice = findViewById(R.id.product_detail_price);

        if (getIntent() != null) {
            productName.setText(getIntent().getStringExtra("Name"));
            productWeight.setText(getIntent().getStringExtra("Weight"));
            productPrice.setText(getIntent().getStringExtra("Price"));
            productImage.setImageResource(getIntent().getIntExtra("image", 0));
        }
    }
}