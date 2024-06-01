package com.wits.grofast_user.MainHomePage;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wits.grofast_user.Adapter.AllOffersAdapter;
import com.wits.grofast_user.Adapter.CartResentAddProductAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    RecyclerView recyclerView_cart_resent_product;
    CartResentAddProductAdapter cartResentAddProduct;
    List<Map<String, Object>> Items;
    TextView additem;
    LinearLayout additemlayout, showedittextlayout;
    EditText additemedittext;
    AppCompatButton additembutton;
    ImageView additemimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar(getString(R.string.bottom_menu_cart), 0, 0);
        }

        additem = root.findViewById(R.id.cart_add_more_item);

        //Linear layout
        additemlayout = root.findViewById(R.id.cart_add_item_linearlayout);
        showedittextlayout = root.findViewById(R.id.cart_add_item_show_detail_layout);

        //Edittext
        additemedittext = root.findViewById(R.id.cart_add_item_edittext);

        //Button
        additembutton = root.findViewById(R.id.cart_add_item_button);

        //Image view
        additemimage = root.findViewById(R.id.cart_add_item_image);

        additemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showedittextlayout.getVisibility() == View.VISIBLE) {
                    showedittextlayout.setVisibility(View.GONE);
                    additemimage.setImageResource(R.drawable.hide_arrow);
                } else {
                    showedittextlayout.setVisibility(View.VISIBLE);
                    additemimage.setImageResource(R.drawable.arrow_up);
                }
            }
        });

        //Cart Item
        recyclerView_cart_resent_product = root.findViewById(R.id.fragment_cart_resent_product);
        Items = new ArrayList<>();
        loadCartProductItem();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_cart_resent_product.setLayoutManager(linearLayoutManager);
        cartResentAddProduct = new CartResentAddProductAdapter(getContext(), Items);
        recyclerView_cart_resent_product.setAdapter(cartResentAddProduct);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentnav, new ProductFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }

    private void loadCartProductItem() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Name", "Gobhi vegitable haha");
        item1.put("Price", "2000");
        item1.put("image", R.drawable.gobhi_image);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Name", "Gobhi tomato");
        item2.put("Price", "2000");
        item2.put("image", R.drawable.gobhi_image);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("Name", "Gobhi catego");
        item3.put("Price", "2000");
        item3.put("image", R.drawable.gobhi_image);

        Items.add(item1);
        Items.add(item2);
        Items.add(item3);
    }
}