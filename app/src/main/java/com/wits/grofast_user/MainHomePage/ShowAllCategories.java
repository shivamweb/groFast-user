package com.wits.grofast_user.MainHomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wits.grofast_user.Adapter.ShowAllCategoriesAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllCategories extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllCategoriesAdapter showAllCategoriesAdapter;
    List<Map<String, Object>> CategoriesItems;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_categories);
        recyclerView = findViewById(R.id.recycleview_all_categories_view);

        CategoriesItems = new ArrayList<>();
        loadCategoriesItems();
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        showAllCategoriesAdapter = new ShowAllCategoriesAdapter(CategoriesItems);
        recyclerView.setAdapter(showAllCategoriesAdapter);
    }

    private void loadCategoriesItems() {
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

        CategoriesItems.add(item1);
        CategoriesItems.add(item2);
        CategoriesItems.add(item3);
        CategoriesItems.add(item4);
        CategoriesItems.add(item5);
        CategoriesItems.add(item6);
        CategoriesItems.add(item7);
        CategoriesItems.add(item8);

    }
}