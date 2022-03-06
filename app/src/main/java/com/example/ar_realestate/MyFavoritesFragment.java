package com.example.ar_realestate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyFavoritesFragment extends Fragment {

    private AdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;
    RecyclerView recyclerView;
    ArrayList<Advertisement> adv;
    static public SQLiteDatabase db;
    public static Boolean clickMyFavDetail=false;
    public MyFavoritesFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        MainActivity.database.onCreate(MainActivity.db);
        db = MainActivity.database.getWritableDatabase();

        View view=inflater.inflate(R.layout.fragment_my_favorites,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recviewMyFavs);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       // advAdapter=new AdvertisementAdapter(Advertisement.getData(getContext()),getContext());
        Cursor c1 = db.rawQuery("SELECT * FROM Favorite as fav, Advertisements as adv, UserAdvertisement as usAdv "+
                    " WHERE fav.AdvId=usAdv.AdvId AND fav.FavoriteStatus=1 AND fav.AdvId=adv.AdvId AND fav.UserId=?;",
                         new String[]{String.valueOf(user.getUserId())});

        adv=new ArrayList<>();

        int advTitleIndex = c1.getColumnIndex("AdvTitle");
        int ImageIndex = c1.getColumnIndex("AdvImage");
        int priceIndex = c1.getColumnIndex("Price");
        int advStatusIndex = c1.getColumnIndex("AdvStatus");
        int roomNumIndex = c1.getColumnIndex("RoomNum");
        int squareMeterIndex = c1.getColumnIndex("SquareMeter");
        int buildingFloorsIndex = c1.getColumnIndex("BuildingFloors");
        int floorLocIndex = c1.getColumnIndex("FloorLoc");
        int buildAgeIndex = c1.getColumnIndex("BuildAge");
        int buildTypeIndex = c1.getColumnIndex("BuildType");
        int itemStatusIndex = c1.getColumnIndex("ItemStatus");
        int warmTypeIndex = c1.getColumnIndex("WarmType");
        int numOfBathroomsIndex = c1.getColumnIndex("NumOfBathrooms");
        int elgCreditIndex = c1.getColumnIndex("ElgCredit");
        int usingStatusIndex = c1.getColumnIndex("UsingStatus");
        int stateBuildingIndex = c1.getColumnIndex("StateBuilding");
        int rentalIncomeIndex = c1.getColumnIndex("RentalIncome");
        int duesIndex = c1.getColumnIndex("Dues");
        int swapIndex = c1.getColumnIndex("Swap");
        int frontIndex = c1.getColumnIndex("Front");
        int fuelTypeIndex = c1.getColumnIndex("FuelType");
        int dateIndex = c1.getColumnIndex("Date");
        int addressIndex = c1.getColumnIndex("Address");
        int cityIndex = c1.getColumnIndex("Cities");
        int townIndex = c1.getColumnIndex("Town");
        int xCoordinateIndex = c1.getColumnIndex("xCoordinate");
        int yCoordinateIndex = c1.getColumnIndex("yCoordinate");
        int advIdIndex = c1.getColumnIndex("AdvId");

        while(c1.moveToNext()){
            Advertisement newAdv=new Advertisement();
            newAdv.setAdvTitle(c1.getString(advTitleIndex));
            newAdv.setAdvStatus(c1.getString(advStatusIndex));
            newAdv.setRoomNum(c1.getString(roomNumIndex));
            newAdv.setWarmType(c1.getString(warmTypeIndex));
            newAdv.setElgForCredit(c1.getString(elgCreditIndex));
            newAdv.setUsingStatus(c1.getString(usingStatusIndex));
            newAdv.setBuildType(c1.getString(buildTypeIndex));
            newAdv.setItemStatus(c1.getString(itemStatusIndex));
            newAdv.setStateBuilding(c1.getString(stateBuildingIndex));
            newAdv.setSwap(c1.getString(swapIndex));
            newAdv.setFront(c1.getString(frontIndex));
            newAdv.setFuelType(c1.getString(fuelTypeIndex));
            newAdv.setDate(c1.getString(dateIndex));
            newAdv.setAddress(c1.getString(addressIndex));
            newAdv.setCity(c1.getString(cityIndex));
            newAdv.setTown(c1.getString(townIndex));
            newAdv.setPrice(c1.getInt(priceIndex));
            newAdv.setSquareMeters(c1.getInt(squareMeterIndex));
            newAdv.setBuildingFloors(c1.getInt(buildingFloorsIndex));
            newAdv.setFloorLoc(c1.getInt(floorLocIndex));
            newAdv.setBuildAge(c1.getInt(buildAgeIndex));
            newAdv.setNumOfBathr(c1.getInt(numOfBathroomsIndex));
            newAdv.setRentalIncome(c1.getInt(rentalIncomeIndex));
            newAdv.setDues(c1.getInt(duesIndex));
            newAdv.setAdvId(c1.getInt(advIdIndex));
            byte[] imageByte = c1.getBlob(ImageIndex);
            Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            newAdv.setAdvImage(imageAdv);
            newAdv.setLatitude(c1.getLong(xCoordinateIndex));
            newAdv.setLongitude(c1.getLong(yCoordinateIndex));
            System.out.println("asd "+newAdv.getAdvId());
            adv.add(newAdv);
        }

        advAdapter=new AdvertisementAdapter(adv, getContext());
        recyclerView.setAdapter(advAdapter);

        advAdapter.setOnItemClickListener(adv ->  {
            clickMyFavDetail=true;
            advDetail=new AdvDetail(adv.getAdvId(),adv.getAdvTitle(),adv.getAdvImage(),adv.getPrice(),adv.getAdvStatus(),
                    adv.getRoomNum(),adv.getSquareMeters(),adv.getBuildingFloors(),adv.getFloorLoc(),
                    adv.getBuildAge(),adv.getBuildType(),adv.getItemStatus(),adv.getWarmType(),adv.getNumOfBathr(),
                    adv.getElgForCredit(),adv.getUsingStatus(),adv.getStateBuilding(),adv.getRentalIncome(),
                    adv.getDues(),adv.getSwap(),adv.getFront(),adv.getFuelType(),adv.getDate(),adv.getAddress(),adv.getCity(),adv.getTown());

            MainActivity.navViewToolbar.setVisibility(View.INVISIBLE);
            replaceFragment(new AdvDetailFragment());
        });

        return view;
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }



}