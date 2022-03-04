package com.example.ar_realestate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ar_realestate.databinding.FragmentMyAdvUpdateBinding;
import com.example.ar_realestate.databinding.FragmentUserProfileBinding;
import com.google.android.gms.maps.GoogleMap;

public class MyAdvUpdateFragment extends Fragment {
    private FragmentMyAdvUpdateBinding binding;
    Advertisement advertisement;
    GoogleMap gMap;
    private TextView txtViewDate;
    private EditText editTxtTitle,editTxtPrice, editTxtSquareMt,editTxtBuildingFloors, editTxtFloorLoc,editTxtBuildAge,editTxtNumofBath,
            editTxtRentalIncome,editTxtDues,editTxtAddress;
    private ImageView imageAdv,imageAdv2,imageAdv3;
    private Bitmap selectedİmg,smallestedImg,firstImage;

    private EditText inputUserName,inputUserSurname,inputUserMail, inputPassword,inputNewPassword;

    private Spinner spinnerAdvStatus,spinnerRoomNum, spinnerBuildType,spinnerItemStatus,spinnerWarmType,spinnerElgbCredit,spinnerUsingStatus, spinnerStateOfBuilding,spinnerSwap,spinnerFront,spinnerFuelType,spinnerCity,spinnerTown;

    private int imgNoPermissionCod=0,imgPermissionCod=1;

    public MyAdvUpdateFragment() {
        // Required empty public constructor
    }


    public static MyAdvUpdateFragment newInstance(String param1, String param2) {
        MyAdvUpdateFragment fragment = new MyAdvUpdateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentMyAdvUpdateBinding.inflate(inflater,container,false);

        Intent intent=getActivity().getIntent();
        advertisement=(Advertisement)intent.getSerializableExtra("Advertisements");

        initFirst();
        binding.updateAdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    public void initFirst(){

        binding.addAdvEditTextAdvTitle.setText(MyAdvertisementFragment.advDetail.getAdvTitle());
        binding.addAdvImage.setImageBitmap(MyAdvertisementFragment.advDetail.getAdv_image());
        binding.addAdvEditTextPrice.setText(String.valueOf(MyAdvertisementFragment.advDetail.getPrice()));

        spinnerAdvStatus=(Spinner)binding.addAdvSpinnerAdvStatus;
        ArrayAdapter<CharSequence> adapterAdvStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Adv_Status, android.R.layout.simple_spinner_item);
        adapterAdvStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdvStatus.setAdapter(adapterAdvStatus);
        if (MyAdvertisementFragment.advDetail.getAdvStatus() != null) {
            int spinnerPosition = adapterAdvStatus.getPosition(MyAdvertisementFragment.advDetail.getAdvStatus());
            spinnerAdvStatus.setSelection(spinnerPosition);
        }

        spinnerRoomNum=(Spinner)binding.addAdvSpinnerRoomNum;
        ArrayAdapter<CharSequence> adapterRoomNum=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.RoomNumber, android.R.layout.simple_spinner_item);
        adapterRoomNum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNum.setAdapter(adapterRoomNum);
        if (MyAdvertisementFragment.advDetail.getRoomNum() != null) {
            int spinnerPosition = adapterRoomNum.getPosition(MyAdvertisementFragment.advDetail.getRoomNum());
            spinnerRoomNum.setSelection(spinnerPosition);
        }
        binding.addAdvEditTextSquareMeter.setText(String.valueOf(MyAdvertisementFragment.advDetail.getSquareMeters()));
        binding.addAdvEditTextBuildingFloors.setText(String.valueOf(MyAdvertisementFragment.advDetail.getBuildingFloors()));
        binding.addAdvEditTextFloorLoc.setText(String.valueOf(MyAdvertisementFragment.advDetail.getFloorLoc()));
        binding.addAdvEditTextBuildAge.setText(String.valueOf(MyAdvertisementFragment.advDetail.getBuildAge()));

        spinnerBuildType=(Spinner)binding.addAdvSpinnerBuildType;
        ArrayAdapter<CharSequence> adapterBuildType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.BuildingType, android.R.layout.simple_spinner_item);
        adapterBuildType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuildType.setAdapter(adapterBuildType);
        if (MyAdvertisementFragment.advDetail.getBuildType() != null) {
            int spinnerPosition = adapterBuildType.getPosition(MyAdvertisementFragment.advDetail.getBuildType());
            spinnerBuildType.setSelection(spinnerPosition);
        }

        spinnerItemStatus=(Spinner)binding.addAdvSpinnerItemStatus;
        ArrayAdapter<CharSequence> adapterItemStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.ItemStatus, android.R.layout.simple_spinner_item);
        adapterItemStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemStatus.setAdapter(adapterItemStatus);
        if (MyAdvertisementFragment.advDetail.getItemStatus() != null) {
            int spinnerPosition = adapterItemStatus.getPosition(MyAdvertisementFragment.advDetail.getItemStatus());
            spinnerItemStatus.setSelection(spinnerPosition);
        }

        spinnerWarmType=(Spinner)binding.addAdvSpinnerWarmType;
        ArrayAdapter<CharSequence> adapterWarmType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.WarmType, android.R.layout.simple_spinner_item);
        adapterWarmType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWarmType.setAdapter(adapterWarmType);
        if (MyAdvertisementFragment.advDetail.getWarmType() != null) {
            int spinnerPosition = adapterWarmType.getPosition(MyAdvertisementFragment.advDetail.getWarmType());
            spinnerWarmType.setSelection(spinnerPosition);
        }

        binding.addAdvEditTextNumOfBath.setText(String.valueOf(MyAdvertisementFragment.advDetail.getNumOfBathr()));

        spinnerElgbCredit=(Spinner)binding.addAdvSpinnerElgCredit;
        ArrayAdapter<CharSequence> adapterElgCredit=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.ElgForCredit, android.R.layout.simple_spinner_item);
        adapterElgCredit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerElgbCredit.setAdapter(adapterElgCredit);
        if (MyAdvertisementFragment.advDetail.getElgForCredit() != null) {
            int spinnerPosition = adapterElgCredit.getPosition(MyAdvertisementFragment.advDetail.getElgForCredit());
            spinnerElgbCredit.setSelection(spinnerPosition);
        }

        spinnerUsingStatus=(Spinner)binding.addAdvSpinnerUsingStatus;
        ArrayAdapter<CharSequence> adapterUsingStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.UsingStatus, android.R.layout.simple_spinner_item);
        adapterUsingStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsingStatus.setAdapter(adapterUsingStatus);
        if (MyAdvertisementFragment.advDetail.getUsingStatus() != null) {
            int spinnerPosition = adapterUsingStatus.getPosition(MyAdvertisementFragment.advDetail.getUsingStatus());
            spinnerUsingStatus.setSelection(spinnerPosition);
        }

        spinnerStateOfBuilding=(Spinner)binding.addAdvSpinnerStateBuilding;
        ArrayAdapter<CharSequence> adapterStateBuilding=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.StateBuilding, android.R.layout.simple_spinner_item);
        adapterStateBuilding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStateOfBuilding.setAdapter(adapterStateBuilding);
        if (MyAdvertisementFragment.advDetail.getStateBuilding()!= null) {
            int spinnerPosition = adapterStateBuilding.getPosition(MyAdvertisementFragment.advDetail.getStateBuilding());
            spinnerStateOfBuilding.setSelection(spinnerPosition);
        }

        binding.addAdvEditTextRentalIncome.setText(String.valueOf(MyAdvertisementFragment.advDetail.getRentalIncome()));
        binding.addAdvEditTextDues.setText(String.valueOf(MyAdvertisementFragment.advDetail.getDues()));

        spinnerSwap=(Spinner)binding.addAdvSpinnerSwap;
        ArrayAdapter<CharSequence> adapterSwap=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Swap, android.R.layout.simple_spinner_item);
        adapterSwap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSwap.setAdapter(adapterSwap);
        if (MyAdvertisementFragment.advDetail.getSwap()!= null) {
            int spinnerPosition = adapterSwap.getPosition(MyAdvertisementFragment.advDetail.getSwap());
            spinnerSwap.setSelection(spinnerPosition);
        }

        spinnerFront=(Spinner)binding.addAdvSpinnerFront;
        ArrayAdapter<CharSequence> adapterFront=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Front, android.R.layout.simple_spinner_item);
        adapterFront.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFront.setAdapter(adapterFront);
        if (MyAdvertisementFragment.advDetail.getFront()!= null) {
            int spinnerPosition = adapterFront.getPosition(MyAdvertisementFragment.advDetail.getFront());
            spinnerFront.setSelection(spinnerPosition);
        }

        spinnerFuelType=(Spinner)binding.addAdvSpinnerFuelType;
        ArrayAdapter<CharSequence> adapterFuelType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.FuelType, android.R.layout.simple_spinner_item);
        adapterFuelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapterFuelType);
        if (MyAdvertisementFragment.advDetail.getFuelType()!= null) {
            int spinnerPosition = adapterFuelType.getPosition(MyAdvertisementFragment.advDetail.getFuelType());
            spinnerFuelType.setSelection(spinnerPosition);
        }

        binding.addAdvEditTextAddress.setText((MyAdvertisementFragment.advDetail.getAddress()));

        spinnerCity=(Spinner)binding.addAdvSpinnerCity;
        ArrayAdapter<CharSequence> adapterCity=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Cities, android.R.layout.simple_spinner_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        if (MyAdvertisementFragment.advDetail.getCity()!= null) {
            int spinnerPosition = adapterCity.getPosition(MyAdvertisementFragment.advDetail.getCity());
            spinnerCity.setSelection(spinnerPosition);
        }


        spinnerTown=(Spinner)binding.addAdvSpinnerTown;
        ArrayAdapter<CharSequence> adapterTown=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Cities, android.R.layout.simple_spinner_item);
        adapterTown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTown.setAdapter(adapterTown);
        if (MyAdvertisementFragment.advDetail.getTown()!= null) {
            int spinnerPosition = adapterTown.getPosition(MyAdvertisementFragment.advDetail.getTown());
            spinnerTown.setSelection(spinnerPosition);
        }

    }
}