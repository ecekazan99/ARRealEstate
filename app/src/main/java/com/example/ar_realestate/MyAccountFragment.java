package com.example.ar_realestate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ar_realestate.databinding.FragmentMyaccountBinding;

public class MyAccountFragment extends Fragment {

    private FragmentMyaccountBinding binding;
    public static Boolean clickMyAdv=false;
    public static Boolean clickMyFav=false;
    public static int userMyId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMyaccountBinding.inflate(inflater, container, false);

        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");
        System.out.println(user.getUserId());
        userMyId=user.getUserId();

        binding.myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,new UserProfileFragment());
                fragmentTransaction.commit();

            }
        });

        binding.myAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMyAdv=true;
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,new MyAdvertisementFragment());
                fragmentTransaction.commit();

            }
        });

        binding.myFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMyFav=true;
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,new MyFavoritesFragment());
                fragmentTransaction.commit();

            }
        });

        binding.exitAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getActivity().getIntent();
                        User user=(User)intent.getSerializableExtra("UserInformation");
                        user=null;
                        intent.putExtra("UserInformation",user);
                        //getActivity().startActivity(intent);

                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,new LoginFragment());
                        fragmentTransaction.commit();
                    }
                }).setNegativeButton("No",null );

                AlertDialog alert=builder.create();
                alert.show();

            }
        });
       return binding.getRoot();
    }

}