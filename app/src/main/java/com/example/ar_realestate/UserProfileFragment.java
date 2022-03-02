package com.example.ar_realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ar_realestate.databinding.FragmentLoginBinding;
import com.example.ar_realestate.databinding.FragmentUserProfileBinding;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding= FragmentUserProfileBinding.inflate(inflater,container,false);

        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        binding.inputUserName.setText(user.getUserName());
        binding.inputUserSurname.setText(user.getUserSurname());
        binding.inputUserMail.setText(user.getMailAddress());
       // binding.inputPassword.setText(user.getPassword());

        return  binding.getRoot();
    }
}