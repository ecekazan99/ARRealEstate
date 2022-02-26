package com.example.ar_realestate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ar_realestate.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentLoginBinding.inflate(inflater,container,false);
       binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SignUpFragment signUpFragment=new SignUpFragment();

               FragmentManager fragmentManager=getFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,signUpFragment);
               fragmentTransaction.commit();
           }
       });

        return binding.getRoot();
    }
}