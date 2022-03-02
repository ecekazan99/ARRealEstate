package com.example.ar_realestate;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ar_realestate.R;


public class AdvDetailActivity extends AppCompatActivity {


    private ImageView advImageView;
    private TextView txtAdvTitle, txtAdvPrice, txtAdvStatus, txtAdvRoomNum, txtSquareMeter,
            txtBuildingFloors, txtFloorLocation, txtBuildingAge, txtBuildingType, txtItemStatus, txtWarmType, txtNumOfBathr,
            txtElgForCredit, txtUsingStatus, txtStateBuilding, txtRentalIncome, txtDues, txtSwap, txtFront,
            txtFuelType, txtDate, txtAddress,txtCity;

    String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address,city;
    int price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;
    long latitude, longitude;

    private Bitmap advImagePng;

    private void init() {
        System.out.println("Hereeeeeeeee1 ");
        advImageView = (ImageView) findViewById(R.id.detail_AdvImage);
        txtAdvTitle = (TextView) findViewById(R.id.detail_AdvTitle);
        txtAdvPrice = (TextView) findViewById(R.id.detail_AdvPrice);
        txtAdvStatus = (TextView) findViewById(R.id.detail_AdvStatus);
        txtAdvRoomNum = (TextView) findViewById(R.id.detail_AdvRoomNum);
        txtSquareMeter = (TextView) findViewById(R.id.detail_AdvSquareMeter);
        txtBuildingFloors = (TextView) findViewById(R.id.detail_AdvBuildingFloors);
        txtFloorLocation = (TextView) findViewById(R.id.detail_AdvFloorLoc);
        txtBuildingAge = (TextView) findViewById(R.id.detail_AdvBuildingAge);
        txtBuildingType = (TextView) findViewById(R.id.detail_AdvBuildingType);
        txtItemStatus = (TextView) findViewById(R.id.detail_AdvItemStatus);
        txtWarmType = (TextView) findViewById(R.id.detail_AdvWarmType);
        txtNumOfBathr = (TextView) findViewById(R.id.detail_AdvNumOfBathr);
        txtElgForCredit = (TextView) findViewById(R.id.detail_AdvElgCredit);
        txtUsingStatus = (TextView) findViewById(R.id.detail_AdvUsingStatus);
        txtStateBuilding = (TextView) findViewById(R.id.detail_AdvStateOfBuilding);
        txtRentalIncome = (TextView) findViewById(R.id.detail_AdvRentalIncome);
        txtDues = (TextView) findViewById(R.id.detail_AdvDues);
        txtSwap = (TextView) findViewById(R.id.detail_AdvSwap);
        txtFront = (TextView) findViewById(R.id.detail_AdvFront);
        txtFuelType = (TextView) findViewById(R.id.detail_AdvFuelType);
        txtDate = (TextView) findViewById(R.id.detail_AdvDate);
        txtAddress = (TextView) findViewById(R.id.detail_AdvAddress);
        txtCity=(TextView)findViewById(R.id.detail_AdvCity);
        if(Add_AdvActivity.add_Adv==false){

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

        }
        else if(Add_AdvActivity.add_Adv==true){
            advTitle=Add_AdvActivity.advDetailLast.getAdvTitle();
            advImagePng=Add_AdvActivity.advDetailLast.getAdv_image();
            price=Add_AdvActivity.advDetailLast.getPrice();
            advStatus=Add_AdvActivity.advDetailLast.getAdvStatus();
            roomNum=Add_AdvActivity.advDetailLast.getRoomNum();
            squareMeters=Add_AdvActivity.advDetailLast.getSquareMeters();
            buildingFloors=Add_AdvActivity.advDetailLast.getBuildingFloors();
            floorLoc=Add_AdvActivity.advDetailLast.getFloorLoc();
            buildAge=Add_AdvActivity.advDetailLast.getBuildAge();
            buildType=Add_AdvActivity.advDetailLast.getBuildType();
            itemStatus=Add_AdvActivity.advDetailLast.getItemStatus();
            warmType=Add_AdvActivity.advDetailLast.getWarmType();
            numOfBathr=Add_AdvActivity.advDetailLast.getNumOfBathr();
            elgForCredit=Add_AdvActivity.advDetailLast.getElgForCredit();
            usingStatus=Add_AdvActivity.advDetailLast.getUsingStatus();
            stateBuilding=Add_AdvActivity.advDetailLast.getStateBuilding();
            rentalIncome=Add_AdvActivity.advDetailLast.getRentalIncome();
            dues=Add_AdvActivity.advDetailLast.getDues();
            swap=Add_AdvActivity.advDetailLast.getSwap();
            front=Add_AdvActivity.advDetailLast.getFront();
            fuelType=Add_AdvActivity.advDetailLast.getFuelType();
            date=Add_AdvActivity.advDetailLast.getDate();
            address=Add_AdvActivity.advDetailLast.getAddress();
            city=Add_AdvActivity.advDetailLast.getCity();
        }
        Add_AdvActivity.add_Adv=false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_detail);
        init();

        txtAdvTitle.setText(advTitle);
        advImageView.setImageBitmap(advImagePng);
        txtAdvPrice.setText(String.valueOf(price));
        txtAdvStatus.setText(advStatus);
        System.out.println("HEREEEEEEEEEEEEE22");
        txtAdvRoomNum.setText(roomNum);
        System.out.println("HEREEEEEEEEEEEEE33");
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


    }
}