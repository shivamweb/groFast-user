package com.wits.grofast_user.MainHomePage;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.ShowAllCategoriesAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.CategoryInterface;
import com.wits.grofast_user.Api.responseClasses.CategoryResponse;
import com.wits.grofast_user.Api.responseModels.CategoryModel;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllCategories extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAllCategoriesAdapter showAllCategoriesAdapter;
    private List<CategoryModel> categoryList = new ArrayList<>();
    private GridLayoutManager layoutManager;
    private UserActivitySession userActivitySession;
    private final String TAG = "ShowAllCategories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.show_all_categories_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_show_all_categories);

        userActivitySession = new UserActivitySession(getApplicationContext());
        recyclerView = findViewById(R.id.recycleview_all_categories_view);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        getAllCategories();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllCategories() {
        Call<CategoryResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(CategoryInterface.class).fetchCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    categoryList = categoryResponse.getCategories();
                    showAllCategoriesAdapter = new ShowAllCategoriesAdapter(categoryList, getApplicationContext());
                    recyclerView.setAdapter(showAllCategoriesAdapter);
                } else {
                    handleApiError(TAG, response, getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}