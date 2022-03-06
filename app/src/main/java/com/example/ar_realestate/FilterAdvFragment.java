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

    EditText editTxtPriceMin,editTxtPriceMax, editTxtSquareMin,editTxtSquareMax, editTxtFloorLocMin,editTxtFloorLocMax, editTxtNumofBathMin,editTxtNumofBathMax,
            editTxtRentalIncomeMin,editTxtRentalIncomeMax, editTxtDuesMin,editTxtDuesMax, editTextBuildFloorMin,
            editTextBuildFloorMax,editTextBuildAgeMin,editTextBuildAgeMax;

    Spinner spinnerAdvStatus,spinnerRoomNum, spinnerBuildType, spinnerItemStatus,spinnerWarmtype, spinnerElgbCredit, spinnerUsingStatus, spinnerStateOfBuilding, spinnerSwap, spinnerFront, spinnerFuelType,spinnerCity,spinnerTown;

    static public String advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, city,town;

    static public int priceMin, priceMax, squareMeterMin,squareMeterMax, buildingFloorsMin,buildingFloorsMax, floorLocMin,floorLocMax, buildAgeMin,buildAgeMax, numOfBathrMin,numOfBathrMax, rentalIncomeMin,rentalIncomeMax, duesMin,duesMax;

    static public Boolean applButton=false;

    public static int cityId;
    public static String[] cities=new String[81];
    public static ArrayList<String> districties=new ArrayList<>();
    ArrayAdapter<String> adapterCities;
    ArrayAdapter<String> adapterTowns;


    public FilterAdvFragment() {
        // Required empty public constructor
    }

    private void init() { // initialize part

        spinnerAdvStatus = (Spinner) binding.FilterSpinnerAdvStatus;
        editTxtPriceMin = (EditText) binding.FilterPriceMin;
        editTxtPriceMax = (EditText) binding.FilterPriceMax;
        spinnerRoomNum = (Spinner) binding.FilterSpinnerRoomNum;
        editTxtSquareMin = (EditText) binding.FilterSquareMin;
        editTxtSquareMax = (EditText) binding.FilterSquareMax;
        editTextBuildFloorMin=(EditText)binding.FilterBuildingFloorMin;
        editTextBuildFloorMax=(EditText)binding.FilterBuildingFloorMax;
        editTxtFloorLocMin = (EditText) binding.FilterFloorLocMin;
        editTxtFloorLocMax = (EditText) binding.FilterFloorLocMax;
        editTextBuildAgeMin=(EditText)binding.FilterBuildAgeMin;
        editTextBuildAgeMax=(EditText)binding.FilterBuildAgeMax;
        spinnerBuildType = (Spinner) binding.FilterSpinnerBuildType;
        spinnerItemStatus = (Spinner) binding.FilterSpinnerItemStatus;
        spinnerWarmtype = (Spinner) binding.FilterWarmType;
        editTxtNumofBathMin = (EditText) binding.FilterNumofBathMin;
        editTxtNumofBathMax=(EditText)binding.FilterNumofBathMax;
        spinnerElgbCredit = (Spinner) binding.FilterSpinnerElgCredit;
        spinnerUsingStatus = (Spinner) binding.FilterSpinnerUsingStatus;
        spinnerStateOfBuilding = (Spinner) binding.FilterSpinnerStateBuilding;
        editTxtRentalIncomeMin = (EditText) binding.FilterRentalMin;
        editTxtRentalIncomeMax = (EditText) binding.FilterRentalMax;
        editTxtDuesMin = (EditText) binding.FilterDuesMin;
        editTxtDuesMax = (EditText) binding.FilterDuesMax;
        spinnerSwap = (Spinner) binding.FilterSpinnerSwap;
        spinnerFront = (Spinner) binding.FilterSpinnerFront;
        spinnerFuelType = (Spinner) binding.FilterSpinnerFuelType;
        spinnerCity=(Spinner)binding.FilterSpinnerCity;
        spinnerTown=(Spinner)binding.FilterSpinnerTown;


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

        ArrayAdapter<CharSequence> adapterWarmType = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.WarmType, android.R.layout.simple_spinner_item);
        adapterWarmType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerWarmtype.setAdapter(adapterWarmType);
        spinnerWarmtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                warmType = adapterView.getItemAtPosition(i).toString();
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

        ArrayAdapter<CharSequence> adapterRoomNum = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.RoomNumber, android.R.layout.simple_spinner_item);
        adapterRoomNum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNum.setAdapter(adapterRoomNum);
        spinnerRoomNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                roomNum = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       getCities();
        adapterTowns=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,districties);
        adapterTowns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTown.setAdapter(adapterTowns);

        adapterCities=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,cities);
        adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCities);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city=adapterView.getItemAtPosition(i).toString();
                districties.clear();
                getTown(city);
                adapterTowns=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,districties);
                adapterTowns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTown.setAdapter(adapterTowns);
                spinnerTown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        town=adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

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

                try {
                    priceMin = Integer.parseInt(editTxtPriceMin.getText().toString());
                    priceMax = Integer.parseInt(editTxtPriceMax.getText().toString());

                    squareMeterMin = Integer.parseInt(editTxtSquareMin.getText().toString());
                    squareMeterMax = Integer.parseInt(editTxtSquareMax.getText().toString());

                    buildingFloorsMin = Integer.parseInt(editTextBuildFloorMin.getText().toString());
                    buildingFloorsMax = Integer.parseInt(editTextBuildFloorMax.getText().toString());

                    floorLocMin = Integer.parseInt(editTxtFloorLocMin.getText().toString());
                    floorLocMax = Integer.parseInt(editTxtFloorLocMax.getText().toString());

                    buildAgeMin = Integer.parseInt(editTextBuildAgeMin.getText().toString());
                    buildAgeMax = Integer.parseInt(editTextBuildAgeMax.getText().toString());

                    numOfBathrMin = Integer.parseInt(editTxtNumofBathMin.getText().toString());
                    numOfBathrMax = Integer.parseInt(editTxtNumofBathMax.getText().toString());

                    rentalIncomeMin = Integer.parseInt(editTxtRentalIncomeMin.getText().toString());
                    rentalIncomeMax = Integer.parseInt(editTxtRentalIncomeMax.getText().toString());

                    duesMin = Integer.parseInt(editTxtDuesMin.getText().toString());
                    duesMax = Integer.parseInt(editTxtDuesMax.getText().toString());


                }catch (Exception e){
                    System.out.println("Hataa");
                }

                if(filterInputControl()==true){
                    applButton=true;
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, homeFragment);
                    fragmentTransaction.commit();
                }

            }
        });


        return binding.getRoot();
    }


    public void getCities()
    {
        int counter=0;
        MainActivity.database.onCreate(MainActivity.db);
        Cursor cursor=MainActivity.db.rawQuery("SELECT * FROM Cities",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            cities[counter]=cursor.getString(1);
            counter++;
            cursor.moveToNext();

        }

    }
    public void getTown(String cityName){
        String selectSquery="SELECT CityId FROM Cities WHERE CityName = '"+cityName+"'";
        Cursor cursor=MainActivity.db.rawQuery(selectSquery,null);
        while (cursor.moveToNext()){
            cityId=Integer.parseInt(cursor.getString(0));
            cursor.close();
        }

        selectSquery="SELECT * FROM District WHERE CityId = '"+cityId+"'";
        cursor=MainActivity.db.rawQuery(selectSquery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){

            districties.add(cursor.getString(1));
            cursor.moveToNext();

        }
    }
        public boolean filterInputControl(){

            Boolean checkEmpty =true;
            if(advStatus.equals("Select Adv Status") || buildType.equals("Select Build Type") || itemStatus.equals("Select Item Status") ||
                    elgForCredit.equals("Select Elg Credit") || usingStatus.equals("Select Using Status") || stateBuilding.equals("Select State Building")||
                    swap.equals("Select Swap")|| front.equals("Select Front") || fuelType.equals("Select Fuel Type") ||
                    roomNum.equals("Select Room Number") || warmType.equals("Select Warm Type") || city.equals("Select City") ){
                checkEmpty =false;

                Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

            }else if(TextUtils.isEmpty(editTxtPriceMin.getText().toString())|| TextUtils.isEmpty(editTxtPriceMax.getText().toString())||
                    TextUtils.isEmpty(editTxtSquareMin.getText().toString())||TextUtils.isEmpty(editTxtSquareMax.getText().toString())||
                    TextUtils.isEmpty(editTextBuildFloorMin.getText().toString())|| TextUtils.isEmpty(editTextBuildFloorMax.getText().toString())||
                    TextUtils.isEmpty(editTxtFloorLocMin.getText().toString()) ||TextUtils.isEmpty(editTxtFloorLocMax.getText().toString()) ||
                    TextUtils.isEmpty(editTextBuildAgeMin.getText().toString())||TextUtils.isEmpty(editTextBuildAgeMax.getText().toString()) ||
                    TextUtils.isEmpty(editTxtNumofBathMin.getText().toString())||TextUtils.isEmpty(editTxtNumofBathMax.getText().toString()) ||
                    TextUtils.isEmpty(editTxtRentalIncomeMin.getText().toString())||TextUtils.isEmpty(editTxtRentalIncomeMax.getText().toString())||
                    TextUtils.isEmpty(editTxtDuesMin.getText().toString()) || TextUtils.isEmpty(editTxtDuesMax.getText().toString())){

                checkEmpty=false;
                Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

            }else if(!TextUtils.isDigitsOnly(editTxtPriceMin.getText()) || !TextUtils.isDigitsOnly(editTxtPriceMax.getText()) ||
                    !TextUtils.isDigitsOnly(editTxtSquareMin.getText()) || !TextUtils.isDigitsOnly(editTxtSquareMax.getText())||
                    !TextUtils.isDigitsOnly(editTextBuildFloorMin.getText()) || !TextUtils.isDigitsOnly(editTextBuildFloorMax.getText()) ||
                    !TextUtils.isDigitsOnly(editTxtFloorLocMin.getText()) || !TextUtils.isDigitsOnly(editTxtFloorLocMax.getText()) ||
                    !TextUtils.isDigitsOnly(editTextBuildAgeMin.getText()) || !TextUtils.isDigitsOnly(editTextBuildAgeMax.getText()) ||
                    !TextUtils.isDigitsOnly(editTxtRentalIncomeMin.getText())|| !TextUtils.isDigitsOnly(editTxtRentalIncomeMin.getText())||
                    !TextUtils.isDigitsOnly(editTxtDuesMin.getText()) || !TextUtils.isDigitsOnly(editTxtDuesMax.getText()) ||
                    !TextUtils.isDigitsOnly(editTxtNumofBathMin.getText()) || !TextUtils.isDigitsOnly(editTxtNumofBathMin.getText())) {
                checkEmpty =false;
                Toast.makeText(getActivity(),"Please Incorrect Inputs",Toast.LENGTH_SHORT).show();
            }
            System.out.println("Girildi 1: "+ city);
            return checkEmpty;
        }
}

