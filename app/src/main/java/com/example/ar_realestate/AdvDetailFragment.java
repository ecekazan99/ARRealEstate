package com.example.ar_realestate;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ar_realestate.databinding.FragmentAddAdvBinding;
import com.example.ar_realestate.databinding.FragmentAdvDetailBinding;

public class AdvDetailFragment extends Fragment {

    private FragmentAdvDetailBinding binding;

    private ImageView advImageView;
    private TextView txtAdvTitle, txtAdvPrice, txtAdvStatus, txtAdvRoomNum, txtSquareMeter,
            txtBuildingFloors, txtFloorLocation, txtBuildingAge, txtBuildingType, txtItemStatus, txtWarmType, txtNumOfBathr,
            txtElgForCredit, txtUsingStatus, txtStateBuilding, txtRentalIncome, txtDues, txtSwap, txtFront,
            txtFuelType, txtDate, txtAddress,txtCity,txtTown;

    String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address,city,town;
    int price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;
    long latitude, longitude;

    private Bitmap advImagePng;

    public AdvDetailFragment() {
        // Required empty public constructor
    }
    private void init() {
        System.out.println("Hereeeeeeeee1 ");
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

        if(AddAdvFragment.add_Adv==false){

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
        else if(AddAdvFragment.add_Adv==true){
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
        System.out.println("Hereeee 4444");
        txtTown.setText(town);

        return binding.getRoot();

    }

}