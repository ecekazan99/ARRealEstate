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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private AdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;
    private static List<Advertisement> advertisementList;
    private static List<Advertisement> advTemp;
    RecyclerView recyclerView;
    static ArrayList<Advertisement> adv;
    public HomeFragment() {
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
        MyFavoritesFragment.clickMyFavDetail=false;
        MyAccountFragment.clickMyAdv=false;
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        advAdapter=new AdvertisementAdapter(getData(getContext()),getContext());
       recyclerView.setAdapter(advAdapter);
        advAdapter.setOnItemClickListener(adv ->  {
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


    static public ArrayList<Advertisement> getData(Context context){

        String sqlQuery="";
        Cursor cursor = null;
        int count=0;
        Boolean flagMyAdv=false;
        adv=new ArrayList<>();
        final ServiceManage serviceManage=new ServiceManage();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                advertisementList=serviceManage.getAdvertisement();
                for(int i=0;i<advertisementList.size();i++){
                    adv.add(advertisementList.get(i));
                }
                //System.out.println(advertisementList.get(0).getAdvTitle());
            }
        });
        thread.start();

        // burası yüzünden  home-fav sorunu
       System.out.println(MyAccountFragment.clickMyAdv);
       /* try {

            if(FilterAdvFragment.applButton==true && MyFavoritesFragment.clickMyFav!=true){

                sqlQuery= "SELECT * FROM  Advertisements  WHERE AdvStatus  = ? AND  (Price BETWEEN "+FilterAdvFragment.priceMin+" AND "+FilterAdvFragment.priceMax+ " )"
                        +" AND RoomNum = ? AND (SquareMeter BETWEEN "+FilterAdvFragment.squareMeterMin+" AND "+FilterAdvFragment.squareMeterMax+")"
                        +" AND (BuildingFloors BETWEEN "+FilterAdvFragment.buildingFloorsMin+" AND "+FilterAdvFragment.buildingFloorsMax+")"
                        +" AND (FloorLoc BETWEEN "+FilterAdvFragment.floorLocMin+" AND "+FilterAdvFragment.floorLocMax+")"
                        +" AND (BuildAge BETWEEN "+FilterAdvFragment.buildAgeMin+" AND "+FilterAdvFragment.buildAgeMax+")"
                        +" AND BuildType = ? AND ItemStatus = ? AND WarmType = ?" + "AND (NumOfBathrooms BETWEEN "+FilterAdvFragment.numOfBathrMin+" AND "+FilterAdvFragment.numOfBathrMax+") "
                        +" AND ElgCredit = ? AND UsingStatus = ? AND StateBuilding = ? "
                        +" AND (RentalIncome BETWEEN "+FilterAdvFragment.rentalIncomeMin+" AND "+FilterAdvFragment.rentalIncomeMax+") AND (Dues BETWEEN "+FilterAdvFragment.duesMin+" AND "+FilterAdvFragment.duesMax+") "
                        +" AND Swap = ? AND Front = ? AND FuelType = ?  AND Cities = ? AND Town = ?";

                cursor=MainActivity.db.rawQuery(sqlQuery, new String[] { FilterAdvFragment.advStatus,FilterAdvFragment.roomNum, FilterAdvFragment.buildType,
                        FilterAdvFragment.itemStatus,FilterAdvFragment.warmType,FilterAdvFragment.elgForCredit, FilterAdvFragment.usingStatus,
                        FilterAdvFragment.stateBuilding, FilterAdvFragment.swap,FilterAdvFragment.front,FilterAdvFragment.fuelType,FilterAdvFragment.city,FilterAdvFragment.town});


            }
            else if(FilterAdvFragment.applButton!=true && MainActivity.incrPriceClick==false && MainActivity.decrsPriceClick==false && MyAccountFragment.clickMyAdv!=true &&  MyFavoritesFragment.clickMyFavDetail!=true)
            {

                sqlQuery="SELECT * FROM Advertisements";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }
            else if(MainActivity.incrPriceClick==true && MainActivity.decrsPriceClick==false)
            {

                sqlQuery="SELECT * FROM Advertisements ORDER BY Price ASC";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }
            else if(MainActivity.incrPriceClick==false && MainActivity.decrsPriceClick==true)
            {

                sqlQuery="SELECT * FROM Advertisements ORDER BY Price DESC";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }

            int advTitleIndex = cursor.getColumnIndex("AdvTitle");
            int ImageIndex = cursor.getColumnIndex("AdvImage");
            int priceIndex = cursor.getColumnIndex("Price");
            int advStatusIndex = cursor.getColumnIndex("AdvStatus");
            int roomNumIndex = cursor.getColumnIndex("RoomNum");
            int squareMeterIndex = cursor.getColumnIndex("SquareMeter");
            int buildingFloorsIndex = cursor.getColumnIndex("BuildingFloors");
            int floorLocIndex = cursor.getColumnIndex("FloorLoc");
            int buildAgeIndex = cursor.getColumnIndex("BuildAge");
            int buildTypeIndex = cursor.getColumnIndex("BuildType");
            int itemStatusIndex = cursor.getColumnIndex("ItemStatus");
            int warmTypeIndex = cursor.getColumnIndex("WarmType");
            int numOfBathroomsIndex = cursor.getColumnIndex("NumOfBathrooms");
            int elgCreditIndex = cursor.getColumnIndex("ElgCredit");
            int usingStatusIndex = cursor.getColumnIndex("UsingStatus");
            int stateBuildingIndex = cursor.getColumnIndex("StateBuilding");
            int rentalIncomeIndex = cursor.getColumnIndex("RentalIncome");
            int duesIndex = cursor.getColumnIndex("Dues");
            int swapIndex = cursor.getColumnIndex("Swap");
            int frontIndex = cursor.getColumnIndex("Front");
            int fuelTypeIndex = cursor.getColumnIndex("FuelType");
            int dateIndex = cursor.getColumnIndex("Date");
            int addressIndex = cursor.getColumnIndex("Address");
            int cityIndex = cursor.getColumnIndex("Cities");
            int townIndex = cursor.getColumnIndex("Town");
            int xCoordinateIndex = cursor.getColumnIndex("xCoordinate");
            int yCoordinateIndex = cursor.getColumnIndex("yCoordinate");
            int advIdIndex = cursor.getColumnIndex("AdvId");
            while (cursor.moveToNext()) {
                Advertisement newAdv=new Advertisement();
                newAdv.setAdvTitle(cursor.getString(advTitleIndex));
                newAdv.setAdvStatus(cursor.getString(advStatusIndex));
                newAdv.setRoomNum(cursor.getString(roomNumIndex));
                newAdv.setWarmType(cursor.getString(warmTypeIndex));
                newAdv.setElgForCredit(cursor.getString(elgCreditIndex));
                newAdv.setUsingStatus(cursor.getString(usingStatusIndex));
                newAdv.setBuildType(cursor.getString(buildTypeIndex));
                newAdv.setItemStatus(cursor.getString(itemStatusIndex));
                newAdv.setStateBuilding(cursor.getString(stateBuildingIndex));
                newAdv.setSwap(cursor.getString(swapIndex));
                newAdv.setFront(cursor.getString(frontIndex));
                newAdv.setFuelType(cursor.getString(fuelTypeIndex));
                newAdv.setDate(cursor.getString(dateIndex));
                newAdv.setAddress(cursor.getString(addressIndex));
                newAdv.setCity(cursor.getString(cityIndex));
                newAdv.setTown(cursor.getString(townIndex));
                newAdv.setPrice(cursor.getInt(priceIndex));
                newAdv.setSquareMeters(cursor.getInt(squareMeterIndex));
                newAdv.setBuildingFloors(cursor.getInt(buildingFloorsIndex));
                newAdv.setFloorLoc(cursor.getInt(floorLocIndex));
                newAdv.setBuildAge(cursor.getInt(buildAgeIndex));
                newAdv.setNumOfBathr(cursor.getInt(numOfBathroomsIndex));
                newAdv.setRentalIncome(cursor.getInt(rentalIncomeIndex));
                newAdv.setDues(cursor.getInt(duesIndex));
                newAdv.setAdvId(cursor.getInt(advIdIndex));
                byte[] imageByte = cursor.getBlob(ImageIndex);
                Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                newAdv.setAdvImage(imageAdv);
                newAdv.setLatitude(cursor.getLong(xCoordinateIndex));
                newAdv.setLongitude(cursor.getLong(yCoordinateIndex));
               // System.out.println("asd "+newAdv.getAdvId());
                adv.add(newAdv);

            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        MyAccountFragment.clickMyAdv=false;
        MainActivity.decrsPriceClick=false;
        MainActivity.incrPriceClick=false;
        FilterAdvFragment.applButton=false;
        MyFavoritesFragment.clickMyFavDetail=false;
        return adv;
    }
}