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

public class HomeFragment extends Fragment {

    private AdvertisementAdapter advAdapter;
    static public AdvDetail advDetail;

    RecyclerView recyclerView;
    ArrayList<Advertisement> adv;

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
        MyFavoritesFragment.clickMyFav=false;
        MyAccountFragment.clickMyAdv=false;
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recview);
        // burayı ekledim
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        advAdapter=new AdvertisementAdapter(getData(getContext()),getContext());

       recyclerView.setAdapter(advAdapter);
        //burayı ekledim

        advAdapter.setOnItemClickListener(adv ->  {

            advDetail=new AdvDetail(adv.getAdvId(),adv.getAdvTitle(),adv.getAdvImage(),adv.getPrice(),adv.getAdvStatus(),
                    adv.getRoomNum(),adv.getSquareMeters(),adv.getBuildingFloors(),adv.getFloorLoc(),
                    adv.getBuildAge(),adv.getBuildType(),adv.getItemStatus(),adv.getWarmType(),adv.getNumOfBathr(),
                    adv.getElgForCredit(),adv.getUsingStatus(),adv.getStateBuilding(),adv.getRentalIncome(),
                    adv.getDues(),adv.getSwap(),adv.getFront(),adv.getFuelType(),adv.getDate(),adv.getAddress(),adv.getCity(),adv.getTown());

            MainActivity.navViewToolbar.setVisibility(View.INVISIBLE);
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


    static public ArrayList<Advertisement> getData(Context context){

        String sqlQuery="";
        Cursor cursor = null;
        int count=0;
        Boolean flagMyAdv=false;
        ArrayList<Advertisement> advertisementList=new ArrayList<>();
        ArrayList<String> advTitleList=new ArrayList<>();
        ArrayList<String> advStatusList=new ArrayList<>();
        ArrayList<String> roomNumList=new ArrayList<>();
        ArrayList<String> warmTypeList=new ArrayList<>();
        ArrayList<String> elgForCreditList=new ArrayList<>();
        ArrayList<String> usingStatusList=new ArrayList<>();
        ArrayList<String> buildTypeList=new ArrayList<>();
        ArrayList<String> itemStatusList=new ArrayList<>();
        ArrayList<String> stateBuildingList=new ArrayList<>();
        ArrayList<String> swapList=new ArrayList<>();
        ArrayList<String> frontList=new ArrayList<>();
        ArrayList<String> fuelTypeList=new ArrayList<>();
        ArrayList<String> dateList=new ArrayList<>();
        ArrayList<String> addressList=new ArrayList<>();
        ArrayList<String> cityList=new ArrayList<>();
        ArrayList<String> townList=new ArrayList<>();

        ArrayList<Integer> priceList=new ArrayList<>();
        ArrayList<Integer> squareMetersList=new ArrayList<>();
        ArrayList<Integer> buildingFloorsList=new ArrayList<>();
        ArrayList<Integer> floorLocList=new ArrayList<>();
        ArrayList<Integer> buildAgeList=new ArrayList<>();
        ArrayList<Integer> numOfBathrList=new ArrayList<>();
        ArrayList<Integer> rentalIncomeList=new ArrayList<>();
        ArrayList<Integer> duesList=new ArrayList<>();
        ArrayList<Integer> advIdList=new ArrayList<>();
        ArrayList<Bitmap> ImageList=new ArrayList<>();

        ArrayList<Long>xCoordinateList=new ArrayList<>();
        ArrayList<Long>yCoordinateList=new ArrayList<>();
        // burası yüzünden  home-fav sorunu
       System.out.println(MyAccountFragment.clickMyAdv);
        try {

            if(FilterAdvFragment.applButton==true && MyFavoritesFragment.clickMyFav!=true){
                System.out.println("Burayaa girdii 1111111");
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
            else if(FilterAdvFragment.applButton!=true && MainActivity.incrPriceClick==false && MainActivity.decrsPriceClick==false && MyAccountFragment.clickMyAdv!=true &&  MyFavoritesFragment.clickMyFav!=true && MyFavoritesFragment.clickMyFavDetail!=true)
            {
                System.out.println("Burayaa girdii 2222222222");
                sqlQuery="SELECT * FROM Advertisements";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }
            else if(MainActivity.incrPriceClick==true && MainActivity.decrsPriceClick==false)
            {
                System.out.println("Burayaa girdii 33333333");
                sqlQuery="SELECT * FROM Advertisements ORDER BY Price ASC";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }
            else if(MainActivity.incrPriceClick==false && MainActivity.decrsPriceClick==true)
            {
                System.out.println("Burayaa girdii 4444444444");
                sqlQuery="SELECT * FROM Advertisements ORDER BY Price DESC";
                cursor=MainActivity.db.rawQuery(sqlQuery,null);
            }

            int advTitleIndex = cursor.getColumnIndex("AdvTitle");
            int ImageIndex = cursor.getColumnIndex("AdvImage");
            int priceIncex = cursor.getColumnIndex("Price");
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
                advTitleList.add(cursor.getString(advTitleIndex));
                advStatusList.add(cursor.getString(advStatusIndex));
                roomNumList.add(cursor.getString(roomNumIndex));
                warmTypeList.add(cursor.getString(warmTypeIndex));
                elgForCreditList.add(cursor.getString(elgCreditIndex));
                usingStatusList.add(cursor.getString(usingStatusIndex));
                buildTypeList.add(cursor.getString(buildTypeIndex));
                itemStatusList.add(cursor.getString(itemStatusIndex));
                stateBuildingList.add(cursor.getString(stateBuildingIndex));
                swapList.add(cursor.getString(swapIndex));
                frontList.add(cursor.getString(frontIndex));
                fuelTypeList.add(cursor.getString(fuelTypeIndex));
                dateList.add(cursor.getString(dateIndex));
                addressList.add(cursor.getString(addressIndex));
                cityList.add(cursor.getString(cityIndex));
                townList.add(cursor.getString(townIndex));
                priceList.add(cursor.getInt(priceIncex));
                squareMetersList.add(cursor.getInt(squareMeterIndex));
                buildingFloorsList.add(cursor.getInt(buildingFloorsIndex));
                floorLocList.add(cursor.getInt(floorLocIndex));
                buildAgeList.add(cursor.getInt(buildAgeIndex));
                numOfBathrList.add(cursor.getInt(numOfBathroomsIndex));
                rentalIncomeList.add(cursor.getInt(rentalIncomeIndex));
                duesList.add(cursor.getInt(duesIndex));
                advIdList.add(cursor.getInt(advIdIndex));
                byte[] imageByte = cursor.getBlob(ImageIndex);
                Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                ImageList.add(imageAdv);
                xCoordinateList.add(cursor.getLong(xCoordinateIndex));
                yCoordinateList.add(cursor.getLong(yCoordinateIndex));

            }
            cursor.close();
            for (int i = 0; i < advTitleList.size(); i++) {
                Advertisement adv = new Advertisement();
                adv.setAdvTitle(advTitleList.get(i));
                adv.setAdvImage(ImageList.get(i));
                adv.setPrice(priceList.get(i));
                adv.setAdvStatus(advStatusList.get(i));
                adv.setRoomNum(roomNumList.get(i));
                adv.setSquareMeters(squareMetersList.get(i));
                adv.setBuildingFloors(buildingFloorsList.get(i));
                adv.setFloorLoc(floorLocList.get(i));
                adv.setBuildAge(buildAgeList.get(i));
                adv.setBuildType(buildTypeList.get(i));
                adv.setItemStatus(itemStatusList.get(i));
                adv.setWarmType(warmTypeList.get(i));
                adv.setNumOfBathr(numOfBathrList.get(i));
                adv.setElgForCredit(elgForCreditList.get(i));
                adv.setUsingStatus(usingStatusList.get(i));
                adv.setStateBuilding(stateBuildingList.get(i));
                adv.setRentalIncome(rentalIncomeList.get(i));
                adv.setDues(duesList.get(i));
                adv.setSwap(swapList.get(i));
                adv.setFront(frontList.get(i));
                adv.setFuelType(fuelTypeList.get(i));
                adv.setDate(dateList.get(i));
                adv.setAddress(addressList.get(i));
                adv.setCity(cityList.get(i));
                adv.setTown(townList.get(i));
                adv.setLatitude(xCoordinateList.get(i));
                adv.setLongitude(yCoordinateList.get(i));
                adv.setAdvId(advIdList.get(i));
                advertisementList.add(adv);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        MyAccountFragment.clickMyAdv=false;
        MainActivity.decrsPriceClick=false;
        MainActivity.incrPriceClick=false;
        FilterAdvFragment.applButton=false;
        MyFavoritesFragment.clickMyFavDetail=false;
        return advertisementList;
    }
}