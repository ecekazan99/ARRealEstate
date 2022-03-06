package com.example.ar_realestate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentAddAdvBinding;
import com.example.ar_realestate.databinding.FragmentAdvDetailBinding;

public class AdvDetailFragment extends Fragment {

    private FragmentAdvDetailBinding binding;
    static public SQLiteDatabase db;

    private ImageView advImageView;
    private TextView txtAdvTitle, txtAdvPrice, txtAdvStatus, txtAdvRoomNum, txtSquareMeter,
            txtBuildingFloors, txtFloorLocation, txtBuildingAge, txtBuildingType, txtItemStatus, txtWarmType, txtNumOfBathr,
            txtElgForCredit, txtUsingStatus, txtStateBuilding, txtRentalIncome, txtDues, txtSwap, txtFront,
            txtFuelType, txtDate, txtAddress,txtCity,txtTown;

    String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address,city,town;
    int advId,price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;
    long latitude, longitude;

    private Bitmap advImagePng;

    public AdvDetailFragment() {
        // Required empty public constructor
    }
    private void init() {

        advImageView = (ImageView) binding.detailAdvImage;
        txtAdvTitle = (TextView) binding.detailAdvTitle;
        txtAdvPrice = (TextView) binding.detailAdvPrice;
        txtAdvStatus = (TextView) binding.detailAdvStatus;
        txtAdvRoomNum = (TextView) binding.detailAdvRoomNum;
        txtSquareMeter = (TextView) binding.detailAdvSquareMeter;
        txtBuildingFloors = (TextView) binding.detailAdvBuildingFloors;
        txtFloorLocation = (TextView) binding.detailAdvFloorLoc;
        txtBuildingAge = (TextView) binding.detailAdvBuildingAge;
        txtBuildingType = (TextView) binding.detailAdvBuildingType;
        txtItemStatus = (TextView) binding.detailAdvItemStatus;
        txtWarmType = (TextView) binding.detailAdvWarmType;
        txtNumOfBathr = (TextView) binding.detailAdvNumOfBathr;
        txtElgForCredit = (TextView) binding.detailAdvElgCredit;
        txtUsingStatus = (TextView) binding.detailAdvUsingStatus;
        txtStateBuilding = (TextView) binding.detailAdvStateOfBuilding;
        txtRentalIncome = (TextView) binding.detailAdvRentalIncome;
        txtDues = (TextView) binding.detailAdvDues;
        txtSwap = (TextView) binding.detailAdvSwap;
        txtFront = (TextView) binding.detailAdvFront;
        txtFuelType = (TextView) binding.detailAdvFuelType;
        txtDate = (TextView) binding.detailAdvDate;
        txtAddress = (TextView) binding.detailAdvAddress;
        txtCity=(TextView)binding.detailAdvCity;
        txtTown=(TextView) binding.detailAdvTown;

        if(AddAdvFragment.add_Adv==false && MyAdvertisementFragment.clickMyAdvDetail!=true){
           advId=HomeFragment.advDetail.getAdvId();
            advTitle=HomeFragment.advDetail.getAdvTitle();
            advImagePng=HomeFragment.advDetail.getAdv_image();
            price=HomeFragment.advDetail.getPrice();
            advStatus=HomeFragment.advDetail.getAdvStatus();
            roomNum=HomeFragment.advDetail.getRoomNum();
            squareMeters=HomeFragment.advDetail.getSquareMeters();
            buildingFloors=HomeFragment.advDetail.getBuildingFloors();
            floorLoc=HomeFragment.advDetail.getFloorLoc();
            buildAge=HomeFragment.advDetail.getBuildAge();
            buildType=HomeFragment.advDetail.getBuildType();
            itemStatus=HomeFragment.advDetail.getItemStatus();
            warmType=HomeFragment.advDetail.getWarmType();
            numOfBathr=HomeFragment.advDetail.getNumOfBathr();
            elgForCredit=HomeFragment.advDetail.getElgForCredit();
            usingStatus=HomeFragment.advDetail.getUsingStatus();
            stateBuilding=HomeFragment.advDetail.getStateBuilding();
            rentalIncome=HomeFragment.advDetail.getRentalIncome();
            dues=HomeFragment.advDetail.getDues();
            swap=HomeFragment.advDetail.getSwap();
            front=HomeFragment.advDetail.getFront();
            fuelType=HomeFragment.advDetail.getFuelType();
            date=HomeFragment.advDetail.getDate();
            address=HomeFragment.advDetail.getAddress();
            city=HomeFragment.advDetail.getCity();
            town=HomeFragment.advDetail.getTown();


        }
        else if(AddAdvFragment.add_Adv==true && MyAdvertisementFragment.clickMyAdvDetail!=true){
            advId=AddAdvFragment.advDetailLast.getAdvId();
            advTitle=AddAdvFragment.advDetailLast.getAdvTitle();
            advImagePng=AddAdvFragment.advDetailLast.getAdv_image();
            price=AddAdvFragment.advDetailLast.getPrice();
            advStatus=AddAdvFragment.advDetailLast.getAdvStatus();
            roomNum=AddAdvFragment.advDetailLast.getRoomNum();
            squareMeters=AddAdvFragment.advDetailLast.getSquareMeters();
            buildingFloors=AddAdvFragment.advDetailLast.getBuildingFloors();
            floorLoc=AddAdvFragment.advDetailLast.getFloorLoc();
            buildAge=AddAdvFragment.advDetailLast.getBuildAge();
            buildType=AddAdvFragment.advDetailLast.getBuildType();
            itemStatus=AddAdvFragment.advDetailLast.getItemStatus();
            warmType=AddAdvFragment.advDetailLast.getWarmType();
            numOfBathr=AddAdvFragment.advDetailLast.getNumOfBathr();
            elgForCredit=AddAdvFragment.advDetailLast.getElgForCredit();
            usingStatus=AddAdvFragment.advDetailLast.getUsingStatus();
            stateBuilding=AddAdvFragment.advDetailLast.getStateBuilding();
            rentalIncome=AddAdvFragment.advDetailLast.getRentalIncome();
            dues=AddAdvFragment.advDetailLast.getDues();
            swap=AddAdvFragment.advDetailLast.getSwap();
            front=AddAdvFragment.advDetailLast.getFront();
            fuelType=AddAdvFragment.advDetailLast.getFuelType();
            date=AddAdvFragment.advDetailLast.getDate();
            address=AddAdvFragment.advDetailLast.getAddress();
            city=AddAdvFragment.advDetailLast.getCity();
            town=AddAdvFragment.advDetailLast.getTown();
        }
        else if(MyAdvertisementFragment.clickMyAdvDetail==true || MyAdvertisementAdapter.clickAdvUpdate==true){

            advId=MyAdvertisementFragment.advDetail.getAdvId();
            advTitle=MyAdvertisementFragment.advDetail.getAdvTitle();
            advImagePng=MyAdvertisementFragment.advDetail.getAdv_image();
            price=MyAdvertisementFragment.advDetail.getPrice();
            advStatus=MyAdvertisementFragment.advDetail.getAdvStatus();
            roomNum=MyAdvertisementFragment.advDetail.getRoomNum();
            squareMeters=MyAdvertisementFragment.advDetail.getSquareMeters();
            buildingFloors=MyAdvertisementFragment.advDetail.getBuildingFloors();
            floorLoc=MyAdvertisementFragment.advDetail.getFloorLoc();
            buildAge=MyAdvertisementFragment.advDetail.getBuildAge();
            buildType=MyAdvertisementFragment.advDetail.getBuildType();
            itemStatus=MyAdvertisementFragment.advDetail.getItemStatus();
            warmType=MyAdvertisementFragment.advDetail.getWarmType();
            numOfBathr=MyAdvertisementFragment.advDetail.getNumOfBathr();
            elgForCredit=MyAdvertisementFragment.advDetail.getElgForCredit();
            usingStatus=MyAdvertisementFragment.advDetail.getUsingStatus();
            stateBuilding=MyAdvertisementFragment.advDetail.getStateBuilding();
            rentalIncome=MyAdvertisementFragment.advDetail.getRentalIncome();
            dues=MyAdvertisementFragment.advDetail.getDues();
            swap=MyAdvertisementFragment.advDetail.getSwap();
            front=MyAdvertisementFragment.advDetail.getFront();
            fuelType=MyAdvertisementFragment.advDetail.getFuelType();
            date=MyAdvertisementFragment.advDetail.getDate();
            address=MyAdvertisementFragment.advDetail.getAddress();
            city=MyAdvertisementFragment.advDetail.getCity();
            town=MyAdvertisementFragment.advDetail.getTown();

        }
        MyAdvertisementFragment.clickMyAdvDetail=false;
        MyAdvertisementAdapter.clickAdvUpdate=false;
        AddAdvFragment.add_Adv=false;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdvDetailBinding.inflate(inflater, container, false);


        init();

        txtAdvTitle.setText(advTitle);
        advImageView.setImageBitmap(advImagePng);
        txtAdvPrice.setText(String.valueOf(price));
        txtAdvStatus.setText(advStatus);
        txtAdvRoomNum.setText(roomNum);
        txtSquareMeter.setText(String.valueOf(squareMeters));
        txtBuildingFloors.setText(String.valueOf(buildingFloors));
        txtFloorLocation.setText(String.valueOf(floorLoc));
        txtBuildingAge.setText(String.valueOf(buildAge));
        txtBuildingType.setText(buildType);
        txtItemStatus.setText(itemStatus);
        txtWarmType.setText(warmType);
        txtNumOfBathr.setText(String.valueOf(numOfBathr));
        txtElgForCredit.setText(elgForCredit);
        txtUsingStatus.setText(usingStatus);
        txtStateBuilding.setText(stateBuilding);
        txtRentalIncome.setText(String.valueOf(rentalIncome));
        txtDues.setText(String.valueOf(dues));
        txtSwap.setText(swap);
        txtFront.setText(front);
        txtFuelType.setText(fuelType);
        txtDate.setText(date);
        txtAddress.setText(address);
        txtCity.setText(city);
        txtTown.setText(town);

        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        MainActivity.database.onCreate(MainActivity.db);
        db = MainActivity.database.getWritableDatabase();

        Drawable addFav = getResources().getDrawable(R.drawable.ic_baseline_favorite_24);

        Drawable notFav = getContext().getDrawable(R.drawable.ic_baseline_favorite_border_24);

        if(user==null){
            binding.buttonFav1.setImageDrawable(notFav);
        }
        else{
            Cursor c1 = db.rawQuery("SELECT * FROM Favorite WHERE UserId=?  AND AdvId=? AND FavoriteStatus=? ;",
                    new String[]{String.valueOf(user.getUserId()), String.valueOf(advId), "1"});

            if(c1.moveToFirst()){
                System.out.println("zaten fav");
                binding.buttonFav1.setImageDrawable(addFav);
            }
            else{
                System.out.println("fav değil");
                binding.buttonFav1.setImageDrawable(notFav);
            }
        }

        binding.buttonFav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user==null){
                    Toast.makeText(getActivity(),  "Please login to your account!!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                   Cursor c = db.rawQuery("SELECT * FROM Favorite WHERE UserId=?  AND AdvId=? AND FavoriteStatus=? ;",
                            new String[]{String.valueOf(user.getUserId()),  String.valueOf(advId), "1"});

                    if(c.moveToFirst()){ // Fav
                        binding.buttonFav1.setImageDrawable(notFav);

                        db.execSQL("UPDATE Favorite SET FavoriteStatus = 0 WHERE UserId = "+ String.valueOf(user.getUserId())+
                                    " AND AdvId="+String.valueOf(advId)+";");

                        Toast.makeText(getActivity(),  "It has been deleted to the list of favorites !!",
                                Toast.LENGTH_SHORT).show();

                        System.out.println("favv");
                    }
                    else{
                        binding.buttonFav1.setImageDrawable(addFav);

                        c = db.rawQuery("SELECT * FROM Favorite WHERE UserId=?  AND AdvId=? AND FavoriteStatus=? ;",
                                new String[]{String.valueOf(user.getUserId()), String.valueOf(advId), "0"});

                        if(c.moveToFirst()) { // Fav olmuş eskiden

                            System.out.println("favv olmuş");

                            db.execSQL("UPDATE Favorite SET FavoriteStatus = 1 WHERE UserId = "+ String.valueOf(user.getUserId())+
                                            " AND AdvId= "+String.valueOf(advId)+";");

                            Toast.makeText(getActivity(),  "It has been added to the list of favorites !!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{// Hiç fav olmamış

                            System.out.println("favv olmamuş");

                            MainActivity.database.onCreate(MainActivity.db);
                            String sqlQuery="INSERT INTO Favorite (UserId ,AdvId ,FavoriteStatus)  VALUES(?,?,?);";
                            SQLiteStatement statement = MainActivity.db.compileStatement(sqlQuery);

                            statement.bindString(1,String.valueOf(user.getUserId()));
                            statement.bindString(2,String.valueOf(advId));
                            statement.bindString(3,"1");
                            statement.execute();

                            Toast.makeText(getActivity(),  "It has been added to the list of favorites !!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return binding.getRoot();

    }

}