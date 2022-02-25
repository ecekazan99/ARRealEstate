package com.example.ar_realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private AdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;
    RecyclerView recyclerView;
    ArrayList<Advertisement> adv;

   /* public HomeFragment() {
        // Required empty public constructor
    }*/

    /*public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=view.findViewById(R.id.recview);
        // burayı ekledim
        advAdapter=new AdvertisementAdapter(Advertisement.getData(this.getContext()),this.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //burayı ekledim
        recyclerView.setAdapter(advAdapter);
        advAdapter.setOnItemClickListener(adv ->  {

            advDetail=new AdvDetail(adv.getAdvTitle(),adv.getAdvImage(),adv.getPrice(),adv.getAdvStatus(),adv.getRoomNum(),adv.getSquareMeters(),adv.getBuildingFloors(),adv.getFloorLoc(),adv.getBuildAge(),adv.getBuildType(),adv.getItemStatus(),adv.getWarmType(),adv.getNumOfBathr(),adv.getElgForCredit(),adv.getUsingStatus(),adv.getStateBuilding(),adv.getRentalIncome(),adv.getDues(),adv.getSwap(),adv.getFront(),adv.getFuelType(),adv.getDate(),adv.getAddress());
            Intent detailIntent=new Intent(getActivity(),AdvDetailActivity.class);

            startActivity(detailIntent);

        }); // buraya kadar kısım

      /* adv=new ArrayList<>();

        adv.add(new Advertisement(1,R.drawable.ic_home_black_24dp, "Title-1","Address-1",12));
        adv.add(new Advertisement(2,R.drawable.ic_home_black_24dp, "Title-2","Address-2",13));
        adv.add(new Advertisement(3,R.drawable.ic_home_black_24dp, "Title-3","Address-3",14));
        adv.add(new Advertisement(4,R.drawable.ic_home_black_24dp, "Title-4","Address-4",14));
        adv.add(new Advertisement(5,R.drawable.ic_home_black_24dp, "Title-5","Address-5",15));

        recyclerView.setAdapter(new AdvertisementAdapter(adv));*/
        return view;
    }
}