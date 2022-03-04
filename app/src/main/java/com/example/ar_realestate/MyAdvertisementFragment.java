package com.example.ar_realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdvertisementFragment extends Fragment {
    private AdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;
    RecyclerView recyclerView;
    ArrayList<Advertisement> adv;
    public static Boolean clickMyAdvDetail=false;



    public MyAdvertisementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        System.out.println(user.getUserId());


        View view=inflater.inflate(R.layout.fragment_my_advertisement,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recviewMyAdvs);
        // burayı ekledim
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        advAdapter=new AdvertisementAdapter(Advertisement.getData(getContext()),getContext());

        recyclerView.setAdapter(advAdapter);
        //burayı ekledim

        advAdapter.setOnItemClickListener(adv ->  {
            clickMyAdvDetail=true;
            advDetail=new AdvDetail(adv.getAdvTitle(),adv.getAdvImage(),adv.getPrice(),adv.getAdvStatus(),
                    adv.getRoomNum(),adv.getSquareMeters(),adv.getBuildingFloors(),adv.getFloorLoc(),
                    adv.getBuildAge(),adv.getBuildType(),adv.getItemStatus(),adv.getWarmType(),adv.getNumOfBathr(),
                    adv.getElgForCredit(),adv.getUsingStatus(),adv.getStateBuilding(),adv.getRentalIncome(),
                    adv.getDues(),adv.getSwap(),adv.getFront(),adv.getFuelType(),adv.getDate(),adv.getAddress(),adv.getCity(),adv.getTown());

            MainActivity.navViewToolbar.setVisibility(View.INVISIBLE);
            MainActivity.navViewToolbar_detail.setVisibility(View.VISIBLE);
            replaceFragment(new AdvDetailFragment());
            // MainActivity.navViewToolbar_detail.setVisibility(View.INVISIBLE);
        }); // buraya kadar kısım

        return view;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }

}