package com.wits.grofast_user.Details.MainHomePage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.wits.grofast_user.Adapter.AddLocationSerachResultAdapter;
import com.wits.grofast_user.Details.Coupon;
import com.wits.grofast_user.Details.EditProfile;
import com.wits.grofast_user.Details.MyAddress;
import com.wits.grofast_user.Details.NotificationSetting;
import com.wits.grofast_user.Details.Wallet;
import com.wits.grofast_user.MainActivity;
import com.wits.grofast_user.R;
import com.wits.grofast_user.session.UserActivitySession;
import com.wits.grofast_user.session.UserDetailSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private ImageView menuBar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textview_set_location;
    AddLocationSerachResultAdapter addLocationSerachResultAdapter;
    List<Map<String, Object>> LocationItems;

    private TextView userName, userPhoneNo;
    private CircleImageView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);

        UserActivitySession session=new UserActivitySession(getApplicationContext());
        UserDetailSession userDetailSession = new UserDetailSession(getApplicationContext());

        drawerLayout = findViewById(R.id.drawerlayout1);
        menuBar = findViewById(R.id.menu_bar);
        navigationView = findViewById(R.id.menu_navigation);
        View headerView = navigationView.getHeaderView(0);
        textview_set_location = findViewById(R.id.textview_set_location);

        userName = headerView.findViewById(R.id.user_name);
        userPhoneNo = headerView.findViewById(R.id.user_phone_no);
        userProfile = headerView.findViewById(R.id.user_profile);

        userPhoneNo.setText(userDetailSession.getPhoneNo());
        userName.setText(userDetailSession.getName());
        Glide.with(getApplicationContext()).load(userDetailSession.getImage()).placeholder(R.drawable.gobhi_image).into(userProfile);

        menuBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        textview_set_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlocationDialogbox();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.fragmentnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navproduct) {
                    loadfragment(new ProductFragment(), false);
                } else if (id == R.id.navoffers) {
                    loadfragment(new OffersFragment(), false);
                } else if (id == R.id.navhome) {
                    loadfragment(new HomeFragment(), true);
                } else if (id == R.id.navcart) {
                    loadfragment(new CartFragment(), false);
                } else {
                    loadfragment(new HistoryFragment(), false);
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navhome);
        loadfragment(new HomeFragment(), true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    loadfragment(new HomeFragment(), false);
                } else if (id == R.id.menu_enable_location) {
//                    loadfragment(new ProductFragment(), false);
                } else if (id == R.id.menu_coupon) {
                    startActivity(new Intent(HomePage.this, Coupon.class));
                } else if (id == R.id.menu_offers) {
                    loadfragment(new OffersFragment(), false);
                } else if (id == R.id.menu_wallet) {
                    startActivity(new Intent(HomePage.this, Wallet.class));
                }else if (id == R.id.menu_notification_setting) {
                    startActivity(new Intent(HomePage.this, NotificationSetting.class));
                } else if (id == R.id.menu_edit_profile) {
                    startActivity(new Intent(HomePage.this, EditProfile.class));
                } else if (id == R.id.menu_my_address) {
                    startActivity(new Intent(HomePage.this, MyAddress.class));
                } else if (id == R.id.menu_logout) {
                    session.setLoginStaus(false);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void loadfragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentnav, fragment);
        fragmentTransaction.commit();

        if (fragment instanceof OffersFragment) {
            updateActionBar(getString(R.string.bottom_menu_offers), R.drawable.baseline_arrow_circle_left_24, 0);
        } else if (fragment instanceof ProductFragment) {
            updateActionBar(getString(R.string.bottom_menu_product), R.drawable.baseline_arrow_circle_left_24, 0);
        } else {
            updateActionBar(getString(R.string.set_location), R.drawable.baseline_location_on_24, R.drawable.baseline_keyboard_arrow_down_24);
        }
    }

    public void updateActionBar(String text, int leftDrawable, int rightDrawable) {
        textview_set_location.setText(text);
        textview_set_location.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, rightDrawable, 0);
    }

    private void openlocationDialogbox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.add_location_design, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        ImageView close = dialogView.findViewById(R.id.close_add_location);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RecyclerView recyclerView = dialogView.findViewById(R.id.recycleview_search_result_add_location);
        LocationItems = new ArrayList<>();

        loadlocationItem();

        //Top Stores Item
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        addLocationSerachResultAdapter = new AddLocationSerachResultAdapter(LocationItems);
        recyclerView.setAdapter(addLocationSerachResultAdapter);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dailogbox_background);
        }
        dialog.show();
    }

    private void loadlocationItem() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("LocationName", "Ankleshwar");
        item1.put("SubName", "Bharuch");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("LocationName", "Ankleshwar");
        item2.put("SubName", "Bharuch");

        Map<String, Object> item3 = new HashMap<>();
        item3.put("LocationName", "Ankleshwar");
        item3.put("SubName", "Bharuch");

        LocationItems.add(item1);
        LocationItems.add(item2);
        LocationItems.add(item3);
    }

}