package com.example.ar_realestate;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentFilterAdvBinding;
import com.example.ar_realestate.databinding.FragmentSignUpBinding;

import java.util.ArrayList;

public class FilterAdvFragment extends Fragment {

    private FragmentFilterAdvBinding binding;

    EditText editTxtPriceRange, editTxtRoomNum,
            editTxtSquareMt, editTxtBuildingFloors, editTxtFloorLoc, editTxtBuildAge, editTxtWarmType, editTxtNumofBath,
            editTxtRentalIncomeRange, editTxtDuesRange, editTxtAddress;

    Spinner spinnerAdvStatus, spinnerBuildType, spinnerItemStatus, spinnerElgbCredit, spinnerUsingStatus, spinnerStateOfBuilding, spinnerSwap, spinnerFront, spinnerFuelType;

    static public String advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, address;
    static public int priceMin, priceMax, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome,  dues;
    String tempPrice;
    static public Boolean applButton=false;
    Button applyFilter;

    public FilterAdvFragment() {
        // Required empty public constructor
    }

    private void init() { // initialize part

        spinnerAdvStatus = (Spinner) binding.FilterSpinnerAdvStatus;
        editTxtPriceRange = (EditText) binding.FilterEditTextPrice;
        editTxtRoomNum = (EditText) binding.FilterEditTextRoomNum;

        editTxtSquareMt = (EditText) binding.FilterEditTextSquareMeter;

        editTxtBuildingFloors = (EditText) binding.FilterEditTextBuildingFloors;

        editTxtFloorLoc = (EditText) binding.FilterEditTextFloorLoc;

        editTxtBuildAge = (EditText) binding.FilterEditTextBuildAge;

        spinnerBuildType = (Spinner) binding.FilterSpinnerBuildType;
        spinnerItemStatus = (Spinner) binding.FilterSpinnerItemStatus;
        editTxtWarmType = (EditText) binding.FilterEditTextWarmType;

        editTxtNumofBath = (EditText) binding.FilterEditTextNumOfBath;

        spinnerElgbCredit = (Spinner) binding.FilterSpinnerElgCredit;
        spinnerUsingStatus = (Spinner) binding.FilterSpinnerUsingStatus;
        spinnerStateOfBuilding = (Spinner) binding.FilterSpinnerStateBuilding;
        editTxtRentalIncomeRange = (EditText) binding.FilterEditTextRentalIncome;

        editTxtDuesRange = (EditText) binding.FilterEditTextDues;

        spinnerSwap = (Spinner) binding.FilterSpinnerSwap;
        spinnerFront = (Spinner) binding.FilterSpinnerFront;
        spinnerFuelType = (Spinner) binding.FilterSpinnerFuelType;
        editTxtAddress = (EditText) binding.FilterEditTextAddress;







        ArrayAdapter<CharSequence> adapterAdvStatus = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Adv_Status, android.R.layout.simple_spinner_item);
        adapterAdvStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdvStatus.setAdapter(adapterAdvStatus);
        spinnerAdvStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                advStatus = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterBuildType = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.BuildingType, android.R.layout.simple_spinner_item);
        adapterBuildType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBuildType.setAdapter(adapterBuildType);
        spinnerBuildType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                buildType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterItemStatus = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.ItemStatus, android.R.layout.simple_spinner_item);
        adapterItemStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerItemStatus.setAdapter(adapterItemStatus);
        spinnerItemStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemStatus = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterElgbCredit = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.ElgForCredit, android.R.layout.simple_spinner_item);
        adapterElgbCredit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerElgbCredit.setAdapter(adapterElgbCredit);
        spinnerElgbCredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                elgForCredit = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterUsingStatus = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.UsingStatus, android.R.layout.simple_spinner_item);
        adapterUsingStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsingStatus.setAdapter(adapterUsingStatus);
        spinnerUsingStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usingStatus = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterStateOfBuilding = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.StateBuilding, android.R.layout.simple_spinner_item);
        adapterStateOfBuilding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStateOfBuilding.setAdapter(adapterStateOfBuilding);
        spinnerStateOfBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateBuilding = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterSwap = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Swap, android.R.layout.simple_spinner_item);
        adapterSwap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSwap.setAdapter(adapterSwap);
        spinnerSwap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                swap = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterFront = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Front, android.R.layout.simple_spinner_item);
        adapterFront.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFront.setAdapter(adapterFront);
        spinnerFront.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                front = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterFuelType = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.FuelType, android.R.layout.simple_spinner_item);
        adapterFuelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapterFuelType);
        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fuelType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFilterAdvBinding.inflate(inflater, container, false);
        init();



        binding.FilterBtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                applButton=true;
                roomNum=editTxtRoomNum.getText().toString();
                squareMeters=Integer.parseInt(editTxtSquareMt.getText().toString());
                buildingFloors=Integer.parseInt(editTxtBuildingFloors.getText().toString());
                floorLoc=Integer.parseInt(editTxtFloorLoc.getText().toString());
                buildAge=Integer.parseInt(editTxtBuildAge.getText().toString());
                warmType=editTxtWarmType.getText().toString();
                numOfBathr=Integer.parseInt(editTxtNumofBath.getText().toString());
                rentalIncome=Integer.parseInt(editTxtRentalIncomeRange.getText().toString());
                dues=Integer.parseInt(editTxtDuesRange.getText().toString());
                address=editTxtAddress.getText().toString();

                tempPrice=editTxtPriceRange.getText().toString();
                String[] arrOfTempPrice=tempPrice.split("-");
                priceMin=Integer.parseInt(arrOfTempPrice[0].trim());
                priceMax=Integer.parseInt(arrOfTempPrice[1].trim());


                HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, homeFragment);
                fragmentTransaction.commit();
            }
        });


        return binding.getRoot();
    }

}



