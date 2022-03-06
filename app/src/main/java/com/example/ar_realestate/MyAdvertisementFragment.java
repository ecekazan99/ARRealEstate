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
    public static ArrayList<Advertisement> adv;
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
        // Inflate the layout for this fragment
        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        System.out.println(user.getUserId());


        View view=inflater.inflate(R.layout.fragment_my_advertisement,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recviewMyAdvs);
        // burayı ekledim
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        advAdapter=new MyAdvertisementAdapter(getData(getContext()),getContext());

        recyclerView.setAdapter(advAdapter);
        //burayı ekledim

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
        try {

             if (MyAccountFragment.clickMyAdv == true) {
                System.out.println("Burayaa girdii 555555");
                flagMyAdv = true;
                sqlQuery = "SELECT AdvId FROM UserAdvertisement WHERE UserId= ?";
                cursor = MainActivity.db.rawQuery(sqlQuery, new String[]{String.valueOf(MyAccountFragment.userMyId)});
                while (cursor.moveToNext()) {
                    int advertisementId = Integer.parseInt(cursor.getString(0));
                    sqlQuery = "SELECT * FROM  Advertisements WHERE AdvId = ? ";
                    Cursor cursorTemp = MainActivity.db.rawQuery(sqlQuery, new String[]{String.valueOf(advertisementId)});
                    int advTitleIndex = cursorTemp.getColumnIndex("AdvTitle");
                    int ImageIndex = cursorTemp.getColumnIndex("AdvImage");
                    int priceIncex = cursorTemp.getColumnIndex("Price");
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
                        advTitleList.add(cursorTemp.getString(advTitleIndex));
                        advStatusList.add(cursorTemp.getString(advStatusIndex));
                        roomNumList.add(cursorTemp.getString(roomNumIndex));
                        warmTypeList.add(cursorTemp.getString(warmTypeIndex));
                        elgForCreditList.add(cursorTemp.getString(elgCreditIndex));
                        usingStatusList.add(cursorTemp.getString(usingStatusIndex));
                        buildTypeList.add(cursorTemp.getString(buildTypeIndex));
                        itemStatusList.add(cursorTemp.getString(itemStatusIndex));
                        stateBuildingList.add(cursorTemp.getString(stateBuildingIndex));
                        swapList.add(cursorTemp.getString(swapIndex));
                        frontList.add(cursorTemp.getString(frontIndex));
                        fuelTypeList.add(cursorTemp.getString(fuelTypeIndex));
                        dateList.add(cursorTemp.getString(dateIndex));
                        addressList.add(cursorTemp.getString(addressIndex));
                        cityList.add(cursorTemp.getString(cityIndex));
                        townList.add(cursorTemp.getString(townIndex));
                        priceList.add(cursorTemp.getInt(priceIncex));
                        squareMetersList.add(cursorTemp.getInt(squareMeterIndex));
                        buildingFloorsList.add(cursorTemp.getInt(buildingFloorsIndex));
                        floorLocList.add(cursorTemp.getInt(floorLocIndex));
                        buildAgeList.add(cursorTemp.getInt(buildAgeIndex));
                        numOfBathrList.add(cursorTemp.getInt(numOfBathroomsIndex));
                        rentalIncomeList.add(cursorTemp.getInt(rentalIncomeIndex));
                        duesList.add(cursorTemp.getInt(duesIndex));
                        advIdList.add(cursorTemp.getInt(advIdIndex));
                        byte[] imageByte = cursorTemp.getBlob(ImageIndex);
                        Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                        ImageList.add(imageAdv);
                        xCoordinateList.add(cursorTemp.getLong(xCoordinateIndex));
                        yCoordinateList.add(cursorTemp.getLong(yCoordinateIndex));

                    }
                    cursorTemp.close();
                    for (int i = count; i < advTitleList.size(); i++) {
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
                    count++;
                }
                cursor.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        MyAccountFragment.clickMyAdv=false;
       // MainActivity.decrsPriceClick=false;
      //  MainActivity.incrPriceClick=false;
       // FilterAdvFragment.applButton=false;
        return advertisementList;
    }

}

