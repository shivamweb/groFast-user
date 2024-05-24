package com.wits.grofast_user.MainHomePage;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private final String TAG = "ProductFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_product), R.drawable.baseline_arrow_circle_left_24, 0);
        }

        recyclerView = root.findViewById(R.id.all_product_recycleview);

        //Product Item
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        getProducts(1);
        return root;
    }

    private void getProducts(int page) {
        Call<ProductResponse> call = RetrofitService.getClient().create(ProductInerface.class).fetchProducts(page);
        call.enqueue(new Callback<ProductResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    ProductPaginatedRes paginatedResponse = productResponse.getPaginatedProducts();
                    productList = paginatedResponse.getProductList();

                    allProductAdapter = new AllProductAdapter(getContext(), productList);
                    recyclerView.setAdapter(allProductAdapter);

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
}