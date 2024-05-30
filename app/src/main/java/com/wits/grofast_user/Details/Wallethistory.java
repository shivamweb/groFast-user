package com.wits.grofast_user.Details;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wits.grofast_user.Adapter.WallethistoryAdapter;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.WalletInterface;
import com.wits.grofast_user.Api.paginatedResponses.WalletPaginatedRes;
import com.wits.grofast_user.Api.responseClasses.WalletResponse;
import com.wits.grofast_user.Api.responseModels.WalletModel;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallethistory extends AppCompatActivity {

    RecyclerView recyclerView;
    private WallethistoryAdapter wallethistoryAdapter;
    private final String TAG = "WalletHistoryActivity";
    private List<WalletModel> walletModelslist = new ArrayList<>();
    UserActivitySession userActivitySession;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.wallet_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_wallethistory);
        recyclerView = findViewById(R.id.wallet_history_recycleview);

        userActivitySession = new UserActivitySession(getApplicationContext());
//        load_data.setVisibility(View.VISIBLE);
//        data.setVisibility(View.GONE);
        loadWalletDetails();

        //Coupon Item
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void loadWalletDetails() {
        Call<WalletResponse> call = RetrofitService.getClient(userActivitySession.getToken()).create(WalletInterface.class).fetchWallet();
        call.enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                if (response.isSuccessful()) {
                    WalletResponse walletResponse = response.body();
                    WalletPaginatedRes walletPaginatedRes = walletResponse.getWalletPaginatedRes();
                    walletModelslist = walletPaginatedRes.getWalletList();

                    wallethistoryAdapter = new WallethistoryAdapter(walletModelslist, getApplicationContext());
                    recyclerView.setAdapter(wallethistoryAdapter);

                    Log.i(TAG, "onResponse: fetched " + walletPaginatedRes.getTo() + " wallets");
                    Log.i(TAG, "onResponse: total wallets " + walletPaginatedRes.getTotal());
                } else handleApiError(TAG, response, getApplicationContext());
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}