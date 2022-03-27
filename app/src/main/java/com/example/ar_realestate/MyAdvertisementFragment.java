package com.example.ar_realestate;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdvertisementFragment extends Fragment {
    public static MyAdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;
    public  static int id;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    static ArrayList<Advertisement> adv;
    public static Boolean clickMyAdvDetail=false;

    public MyAdvertisementFragment() {
        // Required empty public constructor
    }
    public static MyAdvertisementFragment newInstance(String param1, String param2) {
        MyAdvertisementFragment fragment = new MyAdvertisementFragment();
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
        View view=inflater.inflate(R.layout.fragment_my_advertisement,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recviewMyAdvs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        advAdapter=new MyAdvertisementAdapter(getData(getContext()),getContext());
        recyclerView.setAdapter(advAdapter);
        advAdapter.setOnItemClickListener(adv ->  {
            clickMyAdvDetail=true;
            advDetail=new AdvDetail(adv.getAdvId(),adv.getAdvTitle(),adv.getAdvImage(),adv.getPrice(),adv.getAdvStatus(),
                    adv.getRoomNum(),adv.getSquareMeters(),adv.getBuildingFloors(),adv.getFloorLoc(),
                    adv.getBuildAge(),adv.getBuildType(),adv.getItemStatus(),adv.getWarmType(),adv.getNumOfBathr(),
                    adv.getElgForCredit(),adv.getUsingStatus(),adv.getStateBuilding(),adv.getRentalIncome(),
                    adv.getDues(),adv.getSwap(),adv.getFront(),adv.getFuelType(),adv.getDate(),adv.getAddress(),adv.getCity(),adv.getTown());
            id=adv.getAdvId();
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
    static public ArrayList<Advertisement> getData(Context context){

        String sqlQuery="";
        Cursor cursor = null;
        int count=0;
        Boolean flagMyAdv=false;
        adv=new ArrayList<>();
        try {
             if (MyAccountFragment.clickMyAdv == true) {
                flagMyAdv = true;
                sqlQuery = "SELECT AdvId FROM UserAdvertisement WHERE UserId= ?";
                cursor = MainActivity.db.rawQuery(sqlQuery, new String[]{String.valueOf(MyAccountFragment.userMyId)});
                while (cursor.moveToNext()) {
                    int advertisementId = Integer.parseInt(cursor.getString(0));
                    sqlQuery = "SELECT * FROM  Advertisements WHERE AdvId = ? ";
                    Cursor cursorTemp = MainActivity.db.rawQuery(sqlQuery, new String[]{String.valueOf(advertisementId)});
                    int advTitleIndex = cursorTemp.getColumnIndex("AdvTitle");
                    int ImageIndex = cursorTemp.getColumnIndex("AdvImage");
                    int priceIndex = cursorTemp.getColumnIndex("Price");
                    int advStatusIndex = cursorTemp.getColumnIndex("AdvStatus");
                    int roomNumIndex = cursorTemp.getColumnIndex("RoomNum");
                    int squareMeterIndex = cursorTemp.getColumnIndex("SquareMeter");
                    int buildingFloorsIndex = cursorTemp.getColumnIndex("BuildingFloors");
                    int floorLocIndex = cursorTemp.getColumnIndex("FloorLoc");
                    int buildAgeIndex = cursorTemp.getColumnIndex("BuildAge");
                    int buildTypeIndex = cursorTemp.getColumnIndex("BuildType");
                    int itemStatusIndex = cursorTemp.getColumnIndex("ItemStatus");
                    int warmTypeIndex = cursorTemp.getColumnIndex("WarmType");
                    int numOfBathroomsIndex = cursorTemp.getColumnIndex("NumOfBathrooms");
                    int elgCreditIndex = cursorTemp.getColumnIndex("ElgCredit");
                    int usingStatusIndex = cursorTemp.getColumnIndex("UsingStatus");
                    int stateBuildingIndex = cursorTemp.getColumnIndex("StateBuilding");
                    int rentalIncomeIndex = cursorTemp.getColumnIndex("RentalIncome");
                    int duesIndex = cursorTemp.getColumnIndex("Dues");
                    int swapIndex = cursorTemp.getColumnIndex("Swap");
                    int frontIndex = cursorTemp.getColumnIndex("Front");
                    int fuelTypeIndex = cursorTemp.getColumnIndex("FuelType");
                    int dateIndex = cursorTemp.getColumnIndex("Date");
                    int addressIndex = cursorTemp.getColumnIndex("Address");
                    int cityIndex = cursorTemp.getColumnIndex("Cities");
                    int townIndex = cursorTemp.getColumnIndex("Town");
                    int xCoordinateIndex = cursorTemp.getColumnIndex("xCoordinate");
                    int yCoordinateIndex = cursorTemp.getColumnIndex("yCoordinate");
                    int advIdIndex = cursorTemp.getColumnIndex("AdvId");
                    while (cursorTemp.moveToNext()) {
                        Advertisement newAdv=new Advertisement();
                        newAdv.setAdvTitle(cursorTemp.getString(advTitleIndex));
                        newAdv.setAdvStatus(cursorTemp.getString(advStatusIndex));
                        newAdv.setRoomNum(cursorTemp.getString(roomNumIndex));
                        newAdv.setWarmType(cursorTemp.getString(warmTypeIndex));
                        newAdv.setElgForCredit(cursorTemp.getString(elgCreditIndex));
                        newAdv.setUsingStatus(cursorTemp.getString(usingStatusIndex));
                        newAdv.setBuildType(cursorTemp.getString(buildTypeIndex));
                        newAdv.setItemStatus(cursorTemp.getString(itemStatusIndex));
                        newAdv.setStateBuilding(cursorTemp.getString(stateBuildingIndex));
                        newAdv.setSwap(cursorTemp.getString(swapIndex));
                        newAdv.setFront(cursorTemp.getString(frontIndex));
                        newAdv.setFuelType(cursorTemp.getString(fuelTypeIndex));
                        newAdv.setDate(cursorTemp.getString(dateIndex));
                        newAdv.setAddress(cursorTemp.getString(addressIndex));
                        newAdv.setCity(cursorTemp.getString(cityIndex));
                        newAdv.setTown(cursorTemp.getString(townIndex));
                        newAdv.setPrice(cursorTemp.getInt(priceIndex));
                        newAdv.setSquareMeters(cursorTemp.getInt(squareMeterIndex));
                        newAdv.setBuildingFloors(cursorTemp.getInt(buildingFloorsIndex));
                        newAdv.setFloorLoc(cursorTemp.getInt(floorLocIndex));
                        newAdv.setBuildAge(cursorTemp.getInt(buildAgeIndex));
                        newAdv.setNumOfBathr(cursorTemp.getInt(numOfBathroomsIndex));
                        newAdv.setRentalIncome(cursorTemp.getInt(rentalIncomeIndex));
                        newAdv.setDues(cursorTemp.getInt(duesIndex));
                        newAdv.setAdvId(cursorTemp.getInt(advIdIndex));
                        byte[] imageByte = cursorTemp.getBlob(ImageIndex);
                        Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                        newAdv.setAdvImage(imageAdv);
                        newAdv.setLatitude(cursorTemp.getLong(xCoordinateIndex));
                        newAdv.setLongitude(cursorTemp.getLong(yCoordinateIndex));
                        System.out.println("asd "+newAdv.getAdvId());
                        adv.add(newAdv);
                    }
                    cursorTemp.close();
                    count++;
                }
                cursor.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        MyAccountFragment.clickMyAdv=false;
        return adv;
    }
}

