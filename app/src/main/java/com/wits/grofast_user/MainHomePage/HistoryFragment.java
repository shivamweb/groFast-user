package com.wits.grofast_user.MainHomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.grofast_user.Adapter.AllHistoryAdapter;
import com.wits.grofast_user.Adapter.AllOffersAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    AllHistoryAdapter allHistoryAdapter;
    List<Map<String, Object>> Items;

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
        Items = new ArrayList<>();

        loadHistoryItem();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        allHistoryAdapter = new AllHistoryAdapter(getContext(), Items);
        recyclerView.setAdapter(allHistoryAdapter);
        return root;
    }

    private void loadHistoryItem() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Order_Id", "#2582");
        item1.put("Date", "30/12/1999");
        item1.put("Price", "200");

        List<Map<String, Object>> innerData1 = new ArrayList<>();
        Map<String, Object> innerItem1 = new HashMap<>();
        innerItem1.put("Quantity", "3");
        innerItem1.put("Product_Name", "Apple");
        innerItem1.put("Price", "20");
        innerItem1.put("image", R.drawable.gobhi_image);
        innerData1.add(innerItem1);

        Map<String, Object> innerItem2 = new HashMap<>();
        innerItem2.put("Quantity", "4");
        innerItem2.put("Product_Name", "Banana");
        innerItem2.put("Price", "20");
        innerItem2.put("image", R.drawable.of2);
        innerData1.add(innerItem2);

        Map<String, Object> innerItem3 = new HashMap<>();
        innerItem3.put("Quantity", "5");
        innerItem3.put("Product_Name", "Mango apple banana ");
        innerItem3.put("Price", "20");
        innerItem3.put("image", R.drawable.of1);
        innerData1.add(innerItem3);

        item1.put("InnerData", innerData1);

        //Item 2

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Order_Id", "#25869");
        item2.put("Date", "30/12/1999");
        item2.put("Price", "200");

        List<Map<String, Object>> innerData2 = new ArrayList<>();
        Map<String, Object> innerItem5 = new HashMap<>();
        innerItem5.put("Quantity", "6");
        innerItem5.put("Product_Name", "Orange hena khana hai");
        innerItem5.put("Price", "20");
        innerItem5.put("image", R.drawable.gobhi_image);
        innerData2.add(innerItem2);
        item2.put("InnerData", innerData2);


        //Item 3

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Order_Id", "#89658");
        item3.put("Date", "30/12/1999");
        item3.put("Price", "200");

        List<Map<String, Object>> innerData3 = new ArrayList<>();
        Map<String, Object> innerItem10 = new HashMap<>();
        innerItem10.put("Quantity", "1");
        innerItem10.put("Product_Name", "Pizza");
        innerItem10.put("Price", "20");
        innerItem10.put("image", R.drawable.gobhi_image);
        innerData3.add(innerItem3);
        item3.put("InnerData", innerData3);

        Items.add(item1);
        Items.add(item2);
        Items.add(item3);
    }
}