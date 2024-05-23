package com.wits.grofast_user.MainHomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.grofast_user.Adapter.AllProductAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    AllProductAdapter allProductAdapter;
    List<Map<String, Object>> productItems;
    private GridLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_product), R.drawable.baseline_arrow_circle_left_24, 0);
        }

        recyclerView = root.findViewById(R.id.all_product_recycleview);
        productItems = new ArrayList<>();
        
        loadProductItems();

        //Product Item
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        allProductAdapter = new AllProductAdapter(getContext(),productItems);
        recyclerView.setAdapter(allProductAdapter);

        return root;
    }

    private void loadProductItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Apple");
        item1.put("Weight", "500Kg");
        item1.put("Price", "2000");
        item1.put("image", R.drawable.apple);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "strawberry");
        item2.put("Weight", "500Kg");
        item2.put("Price", "2000");
        item2.put("image", R.drawable.strawberry);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Apple");
        item3.put("Weight", "500Kg");
        item3.put("Price", "2000");
        item3.put("image", R.drawable.apple);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("Name", "strawberry");
        item4.put("Weight", "500Kg");
        item4.put("Price", "2000");
        item4.put("image", R.drawable.strawberry);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("Name", "strawberry");
        item5.put("Weight", "500Kg");
        item5.put("Price", "2000");
        item5.put("image", R.drawable.strawberry);

        Map<String, Object> item6 = new HashMap<>();
        item6.put("Name", "Apple");
        item6.put("Weight", "500Kg");
        item6.put("Price", "2000");
        item6.put("image", R.drawable.apple);

        productItems.add(item1);
        productItems.add(item2);
        productItems.add(item3);
        productItems.add(item4);
        productItems.add(item5);
        productItems.add(item6);
    }
}