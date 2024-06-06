package com.wits.grofast_user.MainHomePage;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.AllHistoryAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.OrderInterface;
import com.wits.grofast_user.Api.responseClasses.OrderHistoryResponse;
import com.wits.grofast_user.Api.responseModels.OrderModel;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    AllHistoryAdapter allHistoryAdapter;
    private List<OrderModel> orderList = new ArrayList<>();
    private UserActivitySession userActivitySession;
    private final String TAG = "HistoryFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_history), 0, 0);
        }
        //History Item
        recyclerView = root.findViewById(R.id.history_fragment_recycleview);
        userActivitySession = new UserActivitySession(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadOrderHistory();

        return root;
    }

    private void loadOrderHistory() {
        Call<OrderHistoryResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(OrderInterface.class).fetchOrderHistory();

        call.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                if (response.isSuccessful()) {
                    OrderHistoryResponse orderHistoryResponse = response.body();
                    orderList = orderHistoryResponse.getOrderList();
                    allHistoryAdapter = new AllHistoryAdapter(getContext(), orderList);
                    recyclerView.setAdapter(allHistoryAdapter);
                } else handleApiError(TAG, response, getContext());
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}