package com.example.ar_realestate;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
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

import com.example.ar_realestate.databinding.FragmentMyAdvUpdateBinding;
import com.example.ar_realestate.databinding.FragmentUserProfileBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdvUpdateFragment extends Fragment implements OnMapReadyCallback {
    private FragmentMyAdvUpdateBinding binding;
    Advertisement advertisement;
    GoogleMap gMap;
    private TextView txtViewDate;
    private EditText editTxtTitle,editTxtPrice, editTxtSquareMt,editTxtBuildingFloors, editTxtFloorLoc,editTxtBuildAge,editTxtNumofBath,
            editTxtRentalIncome,editTxtDues,editTxtAddress;
    private ImageView imageAdv;
    private Bitmap selectedİmg,smallestedImg;


    private Spinner spinnerAdvStatus,spinnerRoomNum, spinnerBuildType,spinnerItemStatus,spinnerWarmType,spinnerElgbCredit,spinnerUsingStatus, spinnerStateOfBuilding,spinnerSwap,spinnerFront,spinnerFuelType,spinnerCity,spinnerTown;

    static  String tempStatus;
    String advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address,city,town;
    int price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;
    long latitude,longitude;

    private int imgNoPermissionCod=0,imgPermissionCod=1;
    private String cityName="";
    private CitiesAndTownInsert citiesAndTownInsert;
    ArrayAdapter<String> adapterTown;
    ArrayAdapter<String> adapterCity;
    public static int cityId;
    public static String[] cities=new String[81];
    public static ArrayList<String> districties=new ArrayList<>();


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

         imageAdv=(ImageView)binding.addAdvImage;
        binding.addAdvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity().getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},imgNoPermissionCod);
                }
                else{

                    Intent imageGet=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(imageGet,imgPermissionCod);
                }
            }
        });
        initFirst();
        binding.updateAdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int advId=MyAdvertisementFragment.id;
                int updateAdv=0;
                init();
                Database database=new Database(getContext());
                try {
                    advTitle=editTxtTitle.getText().toString();
                    txtViewDate=(TextView) binding.addAdvEditTextDate;
                    txtViewDate=(TextView) binding.addAdvEditTextDate;
                    txtViewDate.setText(getTodayDate());
                    txtViewDate.setText(getTodayDate());
                   // date=txtViewDate.toString();
                    address=editTxtAddress.getText().toString();
                    price= Integer.parseInt( editTxtPrice.getText().toString());
                    squareMeters=Integer.parseInt(editTxtSquareMt.getText().toString());
                    buildingFloors=Integer.parseInt(editTxtBuildingFloors.getText().toString());
                    floorLoc=Integer.parseInt(editTxtFloorLoc.getText().toString());
                    buildAge=Integer.parseInt(editTxtBuildAge.getText().toString());
                    numOfBathr=Integer.parseInt(editTxtNumofBath.getText().toString());
                    rentalIncome=Integer.parseInt(editTxtRentalIncome.getText().toString());
                    dues=Integer.parseInt(editTxtDues.getText().toString());
                    advStatus=spinnerAdvStatus.getSelectedItem().toString();
                    roomNum=spinnerRoomNum.getSelectedItem().toString();
                    buildType=spinnerBuildType.getSelectedItem().toString();
                    itemStatus=spinnerItemStatus.getSelectedItem().toString();
                    warmType=spinnerWarmType.getSelectedItem().toString();
                    elgForCredit=spinnerElgbCredit.getSelectedItem().toString();
                    usingStatus=spinnerUsingStatus.getSelectedItem().toString();
                    stateBuilding=spinnerStateOfBuilding.getSelectedItem().toString();
                    swap=spinnerSwap.getSelectedItem().toString();
                    front=spinnerFront.getSelectedItem().toString();
                    fuelType=spinnerFuelType.getSelectedItem().toString();
                    city=spinnerCity.getSelectedItem().toString();
                    town=spinnerTown.getSelectedItem().toString();
                }catch (Exception e ){
                    System.out.println("HATAAAAAAAAAAAAA");
                }
                ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
                if(selectedİmg==null)
                {
                    System.out.println("Resimmmmmm Yokkkkkkkkkkkkkkkkkk");
                    selectedİmg=MyAdvertisementFragment.advDetail.getAdv_image();
                }
                smallestedImg=imageSmall(selectedİmg);
                smallestedImg.compress(Bitmap.CompressFormat.PNG,75,outputStream);
                byte[] kayıtedilecekImage=outputStream.toByteArray();

                updateAdv=database.updateMyAdv(advId,advTitle,kayıtedilecekImage,price,advStatus,roomNum,squareMeters,buildingFloors,floorLoc,buildAge,buildType,itemStatus,
                        warmType,numOfBathr,elgForCredit,usingStatus,stateBuilding,rentalIncome,dues,swap,front,fuelType,date,address,city,town,latitude,longitude);

                if(updateAdv==1){
                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyAccountFragment myAccountFragment=new MyAccountFragment();
                            FragmentManager fragmentManager=getFragmentManager();
                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,myAccountFragment);
                            fragmentTransaction.commit();
                        }
                    }).setNegativeButton("No",null );

                    AlertDialog alert=builder.create();
                    alert.show();
                }
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
            tempStatus=adapterAdvStatus.getItem(spinnerPosition).toString();
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
       // binding.addAdvEditTextDate.setText(MyAdvertisementFragment.advDetail.getDate());
        binding.addAdvEditTextAddress.setText((MyAdvertisementFragment.advDetail.getAddress()));

        getCities();
        spinnerCity=(Spinner)binding.addAdvSpinnerCity;
        adapterCity=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,cities);
        System.out.println("Şehirrrrrrrrrrrrrr 11111");
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        if (MyAdvertisementFragment.advDetail.getCity()!= null) {
            int spinnerPosition = adapterCity.getPosition(MyAdvertisementFragment.advDetail.getCity());
            cityName=adapterCity.getItem(spinnerPosition);
            spinnerCity.setSelection(spinnerPosition);

            spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    city=adapterView.getItemAtPosition(i).toString();
                    districties.clear();
                    getTown(city);
                    spinnerTown=(Spinner)binding.addAdvSpinnerTown;
                    adapterTown=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,districties);
                    adapterTown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTown.setAdapter(adapterTown);
                    if (MyAdvertisementFragment.advDetail.getTown()!= null) {
                        int spinnerPositionTown = adapterTown.getPosition(MyAdvertisementFragment.advDetail.getTown());
                        spinnerTown.setSelection(spinnerPositionTown);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }

    }
    public void init(){ // initialize part
        citiesAndTownInsert=new CitiesAndTownInsert();
        citiesAndTownInsert.insertCity();
        editTxtTitle=(EditText) binding.addAdvEditTextAdvTitle;
        editTxtPrice=(EditText) binding.addAdvEditTextPrice;
        spinnerAdvStatus=(Spinner)binding.addAdvSpinnerAdvStatus;
        spinnerRoomNum=(Spinner) binding.addAdvSpinnerRoomNum;
        editTxtSquareMt=(EditText) binding.addAdvEditTextSquareMeter;
        editTxtBuildingFloors=(EditText)binding.addAdvEditTextBuildingFloors;
        editTxtFloorLoc=(EditText) binding.addAdvEditTextFloorLoc;
        editTxtBuildAge=(EditText) binding.addAdvEditTextBuildAge;
        spinnerBuildType=(Spinner)binding.addAdvSpinnerBuildType;
        spinnerItemStatus=(Spinner)binding.addAdvSpinnerItemStatus;
        spinnerWarmType=(Spinner) binding.addAdvSpinnerWarmType;
        editTxtNumofBath=(EditText) binding.addAdvEditTextNumOfBath;
        spinnerElgbCredit=(Spinner) binding.addAdvSpinnerElgCredit;
        spinnerUsingStatus=(Spinner) binding.addAdvSpinnerUsingStatus;
        spinnerStateOfBuilding=(Spinner)binding.addAdvSpinnerStateBuilding;
        editTxtRentalIncome=(EditText)binding.addAdvEditTextRentalIncome;
        editTxtDues=(EditText)binding.addAdvEditTextDues;
        spinnerSwap=(Spinner)binding.addAdvSpinnerSwap;
        spinnerFront=(Spinner)binding.addAdvSpinnerFront;
        spinnerFuelType=(Spinner)binding.addAdvSpinnerFuelType;
        txtViewDate=(TextView) binding.addAdvEditTextDate;
        txtViewDate.setText(getTodayDate());
        editTxtAddress=(EditText)binding.addAdvEditTextAddress;
        spinnerCity=(Spinner)binding.addAdvSpinnerCity;
        spinnerTown=(Spinner)binding.addAdvSpinnerTown;

        imageAdv=(ImageView) binding.addAdvImage;


    }
    private String getTodayDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    public boolean advControl(){

        Boolean checkEmpty =true;
        if(advStatus.equals("Select Adv Status") || buildType.equals("Select Build Type") || itemStatus.equals("Select Item Status") ||
                elgForCredit.equals("Select Elg Credit") || usingStatus.equals("Select Using Status") || stateBuilding.equals("Select State Building")||
                swap.equals("Select Swap")|| front.equals("Select Front") || fuelType.equals("Select Fuel Type") ||
                roomNum.equals("Select Room Number") || warmType.equals("Select Warm Type") || city.equals("Select City") ){
            checkEmpty =false;

            Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(advTitle)  || TextUtils.isEmpty(address)|| TextUtils.isEmpty(editTxtPrice.getText().toString())||
                TextUtils.isEmpty(editTxtSquareMt.getText().toString())||TextUtils.isEmpty(editTxtBuildingFloors.getText().toString())||
                TextUtils.isEmpty(editTxtFloorLoc.getText().toString()) ||TextUtils.isEmpty(editTxtBuildAge.getText().toString())||
                TextUtils.isEmpty(editTxtNumofBath.getText().toString())||TextUtils.isEmpty(editTxtRentalIncome.getText().toString())||TextUtils.isEmpty(editTxtDues.getText().toString())){

            checkEmpty=false;
            Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

        }else if(!TextUtils.isDigitsOnly(editTxtPrice.getText()) || !TextUtils.isDigitsOnly(editTxtSquareMt.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildingFloors.getText()) || !TextUtils.isDigitsOnly(editTxtFloorLoc.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildAge.getText()) || !TextUtils.isDigitsOnly(editTxtRentalIncome.getText())||
                !TextUtils.isDigitsOnly(editTxtDues.getText()) || !TextUtils.isDigitsOnly(editTxtNumofBath.getText())) {
            checkEmpty =false;
            Toast.makeText(getActivity(),"Please Incorrect Inputs",Toast.LENGTH_SHORT).show();
        }
        System.out.println("Girildi 1: "+ city);
        return checkEmpty;
    }
    private Bitmap imageSmall(Bitmap img) {
        return Bitmap.createScaledBitmap(img,300,220,true);
    }

    public void selectImage(View view) {
        if(ContextCompat.checkSelfPermission(getActivity().getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},imgNoPermissionCod);
        }
        else{

            Intent imageGet=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageGet,imgPermissionCod);

            // Select one more images from galery
         /*   Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select images"), imgPermissionCod);*/


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults){


        if(requestCode==imgNoPermissionCod)
        {
            if(grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent imageGet = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imageGet,imgPermissionCod);
            }
        }

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==imgPermissionCod){
            if(resultCode==-1 && data !=null){
                Uri imgUrl=data.getData();

                try {
                    if(Build.VERSION.SDK_INT>=28){

                        // One more than images uri (not work well)
                      /*  for (int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            mArrayUri.add(imageUri);
                        }
                        for (int j = 0; j < mArrayUri.size(); j++) {
                            ImageDecoder.Source imgSource = ImageDecoder.createSource(this.getContentResolver(), mArrayUri.get(j));
                            selectedİmg = ImageDecoder.decodeBitmap(imgSource);
                            imageAdv.setImageBitmap(selectedİmg);
                        }*/
                        ImageDecoder.Source imgSource=ImageDecoder.createSource(getActivity().getContentResolver(),imgUrl);
                        selectedİmg=ImageDecoder.decodeBitmap(imgSource);
                        imageAdv.setImageBitmap(selectedİmg);
                    }else{
                        selectedİmg= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imgUrl);
                        imageAdv.setImageBitmap(selectedİmg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                System.out.println(latLng.latitude+" : "+latLng.longitude);
                //latitude=latLng.latitude;
                //   longitude=latLng.longitude;

                gMap.clear();

                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                gMap.addMarker(markerOptions);


            }
        });
    }

    public static  void getCities()
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
    public  static void getTown(String cityName){
        String selectSquery="SELECT CityId FROM Cities WHERE CityName = '"+cityName+"'";
        Cursor cursor=MainActivity.db.rawQuery(selectSquery,null);
        while (cursor.moveToNext()){
            cityId=Integer.parseInt(cursor.getString(0));
            System.out.println("City idddd"+cursor.getString(0));
            System.out.println("City Name"+cityName);
            cursor.close();
        }

        selectSquery="SELECT * FROM District WHERE CityId = '"+cityId+"'";
        cursor=MainActivity.db.rawQuery(selectSquery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            districties.add(cursor.getString(1));
            System.out.println("District Name :" + cursor.getString(1));
            cursor.moveToNext();

        }
    }

}
