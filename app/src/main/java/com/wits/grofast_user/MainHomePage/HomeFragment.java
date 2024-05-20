package com.wits.grofast_user.MainHomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wits.grofast_user.Adapter.HomeViewProductAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    RecyclerView top_stores_recycleview, product_recycleview;
    TopCategoriesAdapter topStoreAdapter;
    HomeViewProductAdapter productAdapter;
    List<Map<String, Object>> TopStoresItems;
    List<Map<String, Object>> ProductItem;
    private GridLayoutManager layoutManager;
    TextView view_all_categories, view_all_product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        top_stores_recycleview = root.findViewById(R.id.recycleview_top_stores);
        product_recycleview = root.findViewById(R.id.product_recycleview);

        view_all_categories = root.findViewById(R.id.view_all_categories_homepage);
        view_all_product = root.findViewById(R.id.view_all_product);

        TopStoresItems = new ArrayList<>();
        ProductItem = new ArrayList<>();

        loadTopStoreItems();
        loadProductItems();

        //Top Stores Item
        layoutManager = new GridLayoutManager(getContext(), 4);
        top_stores_recycleview.setLayoutManager(layoutManager);
        topStoreAdapter = new TopCategoriesAdapter(TopStoresItems);
        top_stores_recycleview.setAdapter(topStoreAdapter);

        //Product Item
        layoutManager = new GridLayoutManager(getContext(), 2);
        product_recycleview.setLayoutManager(layoutManager);
        productAdapter = new HomeViewProductAdapter(getContext(),ProductItem);
        product_recycleview.setAdapter(productAdapter);

        view_all_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(),ShowAllCategories.class);
                startActivity(in);
            }
        });

        view_all_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProductFragment());
            }
        });
        return root;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentnav, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadTopStoreItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Food");
        item1.put("image", R.drawable.apple);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "Food");
        item2.put("image", R.drawable.strawberry);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Food");
        item3.put("image", R.drawable.apple);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("Name", "Food");
        item4.put("image", R.drawable.strawberry);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("Name", "Food");
        item5.put("image", R.drawable.apple);

        Map<String, Object> item6 = new HashMap<>();
        item6.put("Name", "Food");
        item6.put("image", R.drawable.strawberry);

        Map<String, Object> item7 = new HashMap<>();
        item7.put("Name", "Food");
        item7.put("image", R.drawable.apple);

        Map<String, Object> item8 = new HashMap<>();
        item8.put("Name", "Food");
        item8.put("image", R.drawable.strawberry);

        TopStoresItems.add(item1);
        TopStoresItems.add(item2);
        TopStoresItems.add(item3);
        TopStoresItems.add(item4);
        TopStoresItems.add(item5);
        TopStoresItems.add(item6);
        TopStoresItems.add(item7);
        TopStoresItems.add(item8);

    }

    private void loadProductItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Gobhi");
        item1.put("Weight", "500Kg");
        item1.put("Price", "2000");
        item1.put("image", R.drawable.gobhi_image);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "Gobhi");
        item2.put("Weight", "500Kg");
        item2.put("Price", "2000");
        item2.put("image", R.drawable.gobhi_image);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Gobhi");
        item3.put("Weight", "500Kg");
        item3.put("Price", "2000");
        item3.put("image", R.drawable.gobhi_image);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("Name", "Gobhi");
        item4.put("Weight", "500Kg");
        item4.put("Price", "2000");
        item4.put("image", R.drawable.gobhi_image);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("Name", "Gobhi");
        item5.put("Weight", "500Kg");
        item5.put("Price", "2000");
        item5.put("image", R.drawable.gobhi_image);

        Map<String, Object> item6 = new HashMap<>();
        item6.put("Name", "Gobhi");
        item6.put("Weight", "500Kg");
        item6.put("Price", "2000");
        item6.put("image", R.drawable.gobhi_image);

        ProductItem.add(item1);
        ProductItem.add(item2);
        ProductItem.add(item3);
        ProductItem.add(item4);
        ProductItem.add(item5);
        ProductItem.add(item6);
    }
}