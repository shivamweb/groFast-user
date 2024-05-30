package com.wits.grofast_user.MainHomePage;

import static android.view.View.GONE;
import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.AllProductAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.ProductInerface;
import com.wits.grofast_user.Api.paginatedResponses.ProductPaginatedRes;
import com.wits.grofast_user.Api.responseClasses.ProductResponse;
import com.wits.grofast_user.Api.responseModels.ProductModel;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {
    RecyclerView recyclerView;
    AllProductAdapter allProductAdapter;
    private List<ProductModel> productList = new ArrayList<>();
    private GridLayoutManager layoutManager;
    NestedScrollView show_data;
    LinearLayout load;
    private final String TAG = "ProductFragment";
    private UserActivitySession userActivitySession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_product), R.drawable.baseline_arrow_circle_left_24, 0);
        }

        userActivitySession = new UserActivitySession(getContext());
        recyclerView = root.findViewById(R.id.all_product_recycleview);
        load = root.findViewById(R.id.progress_bar_product_page);
        show_data = root.findViewById(R.id.show_product_data);

        //Product Item
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        switch (userActivitySession.getProductFetchIndicator()) {
            case 0:
                getProducts(1);
                break;
            case 1:
                Bundle bundle = getArguments();
                if (bundle != null) {
                    String category = bundle.getString("categoryName");
                    Log.i(TAG, "onCreateView: category " + category);
                    getProductByCategory(category);
                }
                break;
            default:
                getProducts(1);
        }
        return root;
    }

    private void getProducts(int page) {
        load.setVisibility(View.VISIBLE);
        show_data.setVisibility(GONE);
        Call<ProductResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(ProductInerface.class).fetchProducts(page);
        call.enqueue(new Callback<ProductResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                load.setVisibility(GONE);
                show_data.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    ProductPaginatedRes paginatedResponse = productResponse.getPaginatedProducts();
                    productList = paginatedResponse.getProductList();

                    allProductAdapter = new AllProductAdapter(getContext(), productList);
                    recyclerView.setAdapter(allProductAdapter);

                    Log.i(TAG, "onResponse: getProducts message " + productResponse.getMessage());
                    Log.i(TAG, "onResponse: total products " + paginatedResponse.getTotal());
                    Log.i(TAG, "onResponse: fetched products " + paginatedResponse.getTo());
                } else {
                    if (isAdded()) handleApiError(TAG, response, getContext());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getProductByCategory(String category) {
        load.setVisibility(View.VISIBLE);
        show_data.setVisibility(GONE);

        Call<ProductResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(ProductInerface.class).fetchProductsByCategory(1, category);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                load.setVisibility(GONE);
                show_data.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    ProductPaginatedRes paginatedResponse = productResponse.getPaginatedProducts();
                    productList = paginatedResponse.getProductList();

                    allProductAdapter = new AllProductAdapter(getContext(), productList);
                    recyclerView.setAdapter(allProductAdapter);

                    Log.i(TAG, "onResponse: getProductByCategory message " + productResponse.getMessage());
                    Log.i(TAG, "onResponse: total products " + paginatedResponse.getTotal());
                    Log.i(TAG, "onResponse: fetched products " + paginatedResponse.getTo());
                    Toast.makeText(getContext(), "" + productResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    handleApiError(TAG, response, getContext());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        userActivitySession.setProductFetchIndicator(0);
    }
}