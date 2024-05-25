package com.wits.grofast_user.MainHomePage;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.HomeViewProductAdapter;
import com.wits.grofast_user.Adapter.TopCategoriesAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.CategoryInterface;
import com.wits.grofast_user.Api.interfaces.ProductInerface;
import com.wits.grofast_user.Api.paginatedResponses.CategoryPaginatedRes;
import com.wits.grofast_user.Api.paginatedResponses.ProductPaginatedRes;
import com.wits.grofast_user.Api.responseClasses.CategoryResponse;
import com.wits.grofast_user.Api.responseClasses.ProductResponse;
import com.wits.grofast_user.Api.responseModels.CategoryModel;
import com.wits.grofast_user.Api.responseModels.ProductModel;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView top_stores_recycleview, product_recycleview;
    TopCategoriesAdapter topStoreAdapter;
    HomeViewProductAdapter productAdapter;
    private List<CategoryModel> categoryList = new ArrayList<>();
    private List<ProductModel> productList = new ArrayList<>();
    private GridLayoutManager layoutManager;
    TextView view_all_categories, view_all_product;
    LinearLayout load_categories;
    NestedScrollView layoutcategories;
    private boolean isCategoriesLoaded = false;
    private boolean isProductsLoaded = false;
    private final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        top_stores_recycleview = root.findViewById(R.id.recycleview_top_stores);
        product_recycleview = root.findViewById(R.id.product_recycleview);

        view_all_categories = root.findViewById(R.id.view_all_categories_homepage);
        view_all_product = root.findViewById(R.id.view_all_product);

        load_categories = root.findViewById(R.id.progress_bar_top_categories);
        layoutcategories = root.findViewById(R.id.layout_top_categories);

        ShowPageLoader();

        //Top Stores Item
        layoutManager = new GridLayoutManager(getContext(), 4);
        top_stores_recycleview.setLayoutManager(layoutManager);
        getCategories();

        //Product Item
        layoutManager = new GridLayoutManager(getContext(), 2);
        product_recycleview.setLayoutManager(layoutManager);
        getProducts();

        view_all_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), ShowAllCategories.class);
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

    private void getCategories() {
        Call<CategoryResponse> call = RetrofitService.getClient().create(CategoryInterface.class).fetchCategories(1);
        call.enqueue(new Callback<CategoryResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    CategoryPaginatedRes paginatedResponse = categoryResponse.getPaginatedCategories();
                    categoryList = categoryResponse.getPaginatedCategories().getCategoryList();
                    topStoreAdapter = new TopCategoriesAdapter(categoryList, getContext());
                    top_stores_recycleview.setAdapter(topStoreAdapter);

                    Log.i(TAG, "onResponse: total categories " + paginatedResponse.getTotal());
                    Log.i(TAG, "onResponse: fetched categories " + paginatedResponse.getTo());
                    isCategoriesLoaded = true;
                    checkIfDataLoaded();
                } else {
                    if (isAdded())
                        handleApiError(TAG, response, getContext());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getProducts() {
        Call<ProductResponse> call = RetrofitService.getClient().create(ProductInerface.class).fetchProducts(1);
        call.enqueue(new Callback<ProductResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    ProductPaginatedRes paginatedResponse = productResponse.getPaginatedProducts();
                    productList = paginatedResponse.getProductList();

                    productAdapter = new HomeViewProductAdapter(productList, getContext());
                    product_recycleview.setAdapter(productAdapter);

                    Log.i(TAG, "onResponse: total products " + paginatedResponse.getTotal());
                    Log.i(TAG, "onResponse: fetched products " + paginatedResponse.getTo());

                    isProductsLoaded = true;
                    checkIfDataLoaded();
                } else {
                    if (isAdded())
                        handleApiError(TAG, response, getContext());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void ShowPageLoader() {
        load_categories.setVisibility(View.VISIBLE);
        layoutcategories.setVisibility(View.INVISIBLE);
    }

    private void  HidePageLoader(){
        load_categories.setVisibility(View.GONE);
        layoutcategories.setVisibility(View.VISIBLE);
    }

    private void checkIfDataLoaded() {
        if (isCategoriesLoaded && isProductsLoaded) {
            HidePageLoader();
        }
    }
}