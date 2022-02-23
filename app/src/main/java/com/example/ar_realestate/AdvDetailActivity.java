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
            txtFuelType, txtDate, txtAddress;

    String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address;
    int price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;
    long latitude, longitude;

    private Bitmap advImagePng;

    private void init() {
        advImageView = (ImageView) findViewById(R.id.detail_AdvImage);
        txtAdvTitle = (TextView) findViewById(R.id.detail_AdvTitle);
        txtAdvPrice = (TextView) findViewById(R.id.detail_AdvPrice);
        txtAdvStatus = (TextView) findViewById(R.id.detail_AdvStatus);
        txtAdvRoomNum = (TextView) findViewById(R.id.detail_AdvRoomNum);
        txtSquareMeter = (TextView) findViewById(R.id.detail_AdvSquareMeter);
        txtBuildingFloors = (TextView) findViewById(R.id.add_adv_TextBuildingFloors);
        txtFloorLocation = (TextView) findViewById(R.id.detail_AdvFloorLoc);
        txtBuildingAge = (TextView) findViewById(R.id.detail_AdvBuildingAge);
        txtBuildingType = (TextView) findViewById(R.id.add_adv_TextBuildType);
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

        advTitle= HomeFragment.advDetail.getAdvTitle();
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


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_detail);
        init();

        txtAdvTitle.setText(advTitle);
        advImageView.setImageBitmap(advImagePng);
        txtAdvPrice.setText(price);
        txtAdvStatus.setText(advStatus);
        txtAdvRoomNum.setText(roomNum);
        txtSquareMeter.setText(squareMeters);
        txtBuildingFloors.setText(buildingFloors);
        txtFloorLocation.setText(floorLoc);
        txtBuildingAge.setText(buildAge);
        txtBuildingType.setText(buildType);
        txtItemStatus.setText(itemStatus);
        txtWarmType.setText(warmType);
        txtNumOfBathr.setText(numOfBathr);
        txtElgForCredit.setText(elgForCredit);
        txtUsingStatus.setText(usingStatus);
        txtStateBuilding.setText(stateBuilding);
        txtRentalIncome.setText(rentalIncome);
        txtDues.setText(dues);
        txtSwap.setText(swap);
        txtFront.setText(front);
        txtFuelType.setText(fuelType);
        txtDate.setText(date);
        txtAddress.setText(address);


    }
}