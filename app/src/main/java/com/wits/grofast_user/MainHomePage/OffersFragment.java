package com.wits.grofast_user.MainHomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wits.grofast_user.R;

public class OffersFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_offers, container, false);
        if (getActivity() instanceof HomePage) {
            ((HomePage) getActivity()).updateActionBar("Offers",R.drawable.baseline_arrow_circle_left_24,0);
        }
        return root;
    }
}