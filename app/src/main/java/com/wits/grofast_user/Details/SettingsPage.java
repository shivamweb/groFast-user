package com.wits.grofast_user.Details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.wits.grofast_user.R;

public class SettingsPage extends AppCompatActivity {
    private ImageView languageToggle, policyToggle, accountToggle;
    private TextView titleLanguage, titlePolicy, titleAccount;
    private LinearLayout languageLayout, policyLayout, accountLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_page);

        languageToggle = findViewById(R.id.language_card_toggle);
        policyToggle = findViewById(R.id.policy_card_toggle);
        accountToggle = findViewById(R.id.account_card_toggle);

        titleLanguage = findViewById(R.id.language_card_title);
        titlePolicy = findViewById(R.id.policy_card_title);
        titleAccount = findViewById(R.id.account_card_title);

        languageLayout = findViewById(R.id.language_card_content);
        policyLayout = findViewById(R.id.policy_card_content);
        accountLayout = findViewById(R.id.account_card_content);

//        DEFINING CLICKLISTNERS

        View.OnClickListener languageClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (languageLayout.getVisibility() == View.VISIBLE) {
                    languageLayout.setVisibility(View.GONE);
                    languageToggle.setImageResource(R.drawable.hide_arrow);
                } else {
                    languageLayout.setVisibility(View.VISIBLE);
                    languageToggle.setImageResource(R.drawable.arrow_up);
                }
            }
        };
        View.OnClickListener policyClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (policyLayout.getVisibility() == View.VISIBLE) {
                    policyLayout.setVisibility(View.GONE);
                    policyToggle.setImageResource(R.drawable.hide_arrow);
                } else {
                    policyLayout.setVisibility(View.VISIBLE);
                    policyToggle.setImageResource(R.drawable.arrow_up);
                }
            }
        };
        View.OnClickListener accountClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountLayout.getVisibility() == View.VISIBLE) {
                    accountLayout.setVisibility(View.GONE);
                    accountToggle.setImageResource(R.drawable.hide_arrow);
                } else {
                    accountLayout.setVisibility(View.VISIBLE);
                    accountToggle.setImageResource(R.drawable.arrow_up);
                }
            }
        };

        languageToggle.setOnClickListener(languageClickListner);
        titleLanguage.setOnClickListener(languageClickListner);

        policyToggle.setOnClickListener(policyClickListner);
        titlePolicy.setOnClickListener(policyClickListner);

        accountToggle.setOnClickListener(accountClickListner);
        titleAccount.setOnClickListener(accountClickListner);

        policyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWeb();
            }
        });

    }

    private void navigateToWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.wingersitservices.co.in/"));
        startActivity(intent);
    }
}