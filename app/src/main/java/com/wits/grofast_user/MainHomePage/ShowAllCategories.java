package com.wits.grofast_user.MainHomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.wits.grofast_user.Adapter.ShowAllCategoriesAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.Api.responseModels.CategoryModel;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllCategories extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllCategoriesAdapter showAllCategoriesAdapter;
    private List<CategoryModel> categoryList = new ArrayList<>();
    private GridLayoutManager layoutManager;
    private final String TAG="ShowAllCategories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_categories);

        if (getIntent() != null && getIntent().hasExtra("categories")) {
            categoryList = getIntent().getParcelableArrayListExtra("categories");
        }

        recyclerView = findViewById(R.id.recycleview_all_categories_view);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        showAllCategoriesAdapter = new ShowAllCategoriesAdapter(categoryList,getApplicationContext());
        recyclerView.setAdapter(showAllCategoriesAdapter);
    }

}