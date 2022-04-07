package com.example.ar_realestate;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentAddAdvBinding;
import com.example.ar_realestate.databinding.FragmentArBinding;
import com.example.ar_realestate.databinding.FragmentLoginBinding;

public class ARFragment extends Fragment {
    private FragmentArBinding binding;
    public ARFragment() {
        // Required empty public constructor
    }

    public static ARFragment newInstance(String param1, String param2) {
        ARFragment fragment = new ARFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentArBinding.inflate(inflater,container,false);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PackageManager manager = getContext().getPackageManager();
                Intent intent = manager.getLaunchIntentForPackage("com.DefaultCompany.deneme16_gps");
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);

            }
        });


        return binding.getRoot();
    }
}