package com.example.ar_realestate;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Add_AdvActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    private TextView txtViewDate;
    private EditText editTxtTitle,editTxtPrice,editTxtRoomNum,
            editTxtSquareMt,editTxtBuildingFloors, editTxtFloorLoc,editTxtBuildAge,editTxtWarmType,editTxtNumofBath,
            editTxtRentalIncome,editTxtDues,editTxtAddress;
    private ImageView imageAdv;
    private Bitmap selectedİmg,smallestedImg,firstImage;
    private Button btnSubmitAdv;

    private Spinner spinnerAdvStatus, spinnerBuildType,spinnerItemStatus,spinnerElgbCredit,spinnerUsingStatus, spinnerStateOfBuilding,spinnerSwap,spinnerFront,spinnerFuelType;

    private int imgNoPermissionCod=0,imgPermissionCod=1;

    String advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address;
    int price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;
    long latitude,longitude;

    private void init(){ // initialize part
        editTxtTitle=(EditText) findViewById(R.id.addAdv_editTextAdvTitle);
        editTxtPrice=(EditText) findViewById(R.id.addAdv_editTextPrice);
        spinnerAdvStatus=(Spinner)findViewById(R.id.addAdv_spinnerAdvStatus);
        editTxtRoomNum=(EditText) findViewById(R.id.addAdv_editTextRoomNum);
        editTxtSquareMt=(EditText) findViewById(R.id.addAdv_editTextSquareMeter);
        editTxtBuildingFloors=(EditText)findViewById(R.id.addAdv_editTextBuildingFloors);
        editTxtFloorLoc=(EditText) findViewById(R.id.addAdv_editTextFloorLoc);
        editTxtBuildAge=(EditText) findViewById(R.id.addAdv_editTextBuildAge);
        spinnerBuildType=(Spinner)findViewById(R.id.addAdv_spinnerBuildType);
        spinnerItemStatus=(Spinner)findViewById(R.id.addAdv_spinnerItemStatus);
        editTxtWarmType=(EditText) findViewById(R.id.addAdv_editTextWarmType);
        editTxtNumofBath=(EditText) findViewById(R.id.addAdv_editTextNumOfBath);
        spinnerElgbCredit=(Spinner) findViewById(R.id.addAdv_spinnerElgCredit);
        spinnerUsingStatus=(Spinner) findViewById(R.id.addAdv_spinnerUsingStatus);
        spinnerStateOfBuilding=(Spinner)findViewById(R.id.addAdv_spinnerStateBuilding);
        editTxtRentalIncome=(EditText)findViewById(R.id.addAdv_editTextRentalIncome);
        editTxtDues=(EditText)findViewById(R.id.addAdv_editTextDues);
        spinnerSwap=(Spinner)findViewById(R.id.addAdv_spinnerSwap);
        spinnerFront=(Spinner)findViewById(R.id.addAdv_spinnerFront);
        spinnerFuelType=(Spinner)findViewById(R.id.addAdv_spinnerFuelType);
        txtViewDate=(TextView) findViewById(R.id.addAdv_editTextDate);
        txtViewDate.setText(getTodayDate());
        editTxtAddress=(EditText)findViewById(R.id.addAdv_editTextAddress);
        imageAdv=(ImageView) findViewById(R.id.add_book_activity_imageViewBookImage);
        btnSubmitAdv=(Button) findViewById(R.id.addAdv_btnSubmit);

        ArrayAdapter<CharSequence>adapterAdvStatus=ArrayAdapter.createFromResource(this,R.array.Adv_Status, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterBuildType=ArrayAdapter.createFromResource(this,R.array.BuildingType, android.R.layout.simple_spinner_item);
        adapterBuildType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBuildType.setAdapter(adapterBuildType);
        spinnerBuildType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                buildType=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterItemStatus=ArrayAdapter.createFromResource(this,R.array.ItemStatus, android.R.layout.simple_spinner_item);
        adapterItemStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerItemStatus.setAdapter(adapterItemStatus);
        spinnerItemStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemStatus=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterElgbCredit=ArrayAdapter.createFromResource(this,R.array.ElgForCredit, android.R.layout.simple_spinner_item);
        adapterElgbCredit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerElgbCredit.setAdapter(adapterElgbCredit);
        spinnerElgbCredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                elgForCredit=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterUsingStatus=ArrayAdapter.createFromResource(this,R.array.UsingStatus, android.R.layout.simple_spinner_item);
        adapterUsingStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsingStatus.setAdapter(adapterUsingStatus);
        spinnerUsingStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usingStatus=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterStateOfBuilding=ArrayAdapter.createFromResource(this,R.array.StateBuilding, android.R.layout.simple_spinner_item);
        adapterStateOfBuilding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStateOfBuilding.setAdapter(adapterStateOfBuilding);
        spinnerStateOfBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateBuilding=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterSwap=ArrayAdapter.createFromResource(this,R.array.Swap, android.R.layout.simple_spinner_item);
        adapterSwap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSwap.setAdapter(adapterSwap);
        spinnerSwap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                swap=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterFront=ArrayAdapter.createFromResource(this,R.array.Front, android.R.layout.simple_spinner_item);
        adapterFront.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFront.setAdapter(adapterFront);
        spinnerFront.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                front=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterFuelType=ArrayAdapter.createFromResource(this,R.array.FuelType, android.R.layout.simple_spinner_item);
        adapterFuelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapterFuelType);
        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fuelType=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adv);
        SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);
        init();

    }

    private String getTodayDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    public boolean advInputControl(){
        Boolean checkEmpty =true;
        if(advStatus.equals("Select Adv Status") || buildType.equals("Select Build Type") || itemStatus.equals("Select Item Status") || elgForCredit.equals("Select Elg Credit") || usingStatus.equals("Select Using Status") || stateBuilding.equals("Select State Building")||swap.equals("Select Swap")|| front.equals("Select Front") || fuelType.equals("Select Fuel Type") ){
            checkEmpty =false;
            Toast.makeText(getApplicationContext(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(advTitle) || TextUtils.isEmpty(roomNum)|| TextUtils.isEmpty(warmType) || TextUtils.isEmpty(address)||
                TextUtils.isEmpty(editTxtPrice.getText().toString())||TextUtils.isEmpty(editTxtSquareMt.getText().toString())||TextUtils.isEmpty(editTxtBuildingFloors.getText().toString())||
                TextUtils.isEmpty(editTxtFloorLoc.getText().toString())||TextUtils.isEmpty(editTxtBuildAge.getText().toString())||TextUtils.isEmpty(editTxtNumofBath.getText().toString())||TextUtils.isEmpty(editTxtRentalIncome.getText().toString())||TextUtils.isEmpty(editTxtDues.getText().toString())){
            checkEmpty=false;
        }
        return checkEmpty;
    }

    public void saveAdv(View view) {

        advTitle=editTxtTitle.getText().toString();
        roomNum=editTxtRoomNum.getText().toString();
        warmType=editTxtWarmType.getText().toString();
        date=txtViewDate.toString();
        address=editTxtAddress.getText().toString();

        price= Integer.parseInt( editTxtPrice.getText().toString());
        squareMeters=Integer.parseInt(editTxtSquareMt.getText().toString());
        buildingFloors=Integer.parseInt(editTxtBuildingFloors.getText().toString());
        floorLoc=Integer.parseInt(editTxtFloorLoc.getText().toString());
        buildAge=Integer.parseInt(editTxtBuildAge.getText().toString());
        numOfBathr=Integer.parseInt(editTxtNumofBath.getText().toString());
        rentalIncome=Integer.parseInt(editTxtRentalIncome.getText().toString());
        dues=Integer.parseInt(editTxtDues.getText().toString());

        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        smallestedImg=imageSmall(selectedİmg);
        smallestedImg.compress(Bitmap.CompressFormat.PNG,75,outputStream);
        byte[] kayıtedilecekImage=outputStream.toByteArray();

        if(advInputControl()==true){
            try {
                // Temp database değişecek şimdilik öylesine koydum
                SQLiteDatabase database=this.openOrCreateDatabase("Temp",MODE_PRIVATE,null);
                database.execSQL("CREATE TABLE IF NOT EXISTS advertisements (AdvId INTEGER PRIMARY KEY AUTOINCREMENT,AdvTitle TEXT,AdvImage BLOB,Price INTEGER,AdvStatus TEXT,RoomNum TEXT,SquareMeter INTEGER,BuildingFloors INTEGER,FloorLoc INTEGER,BuildAge INTEGER,BuildType TEXT,ItemStatus TEXT,WarmType TEXT,NumOfBathrooms INTEGER,ElgCredit TEXT,UsingStatus TEXT,StateBuilding TEXT,RentalIncome INTEGER,Dues INTEGER,Swap TEXT,Front TEXT,FuelType TEXT,Date DATE,Address TEXT,xCoordinate REAL,yCoordinate REAL )");

                String sqlQuery="INSERT INTO advertisements (AdvTitle,AdvImage,Price,AdvStatus,RoomNum,SquareMeter,BuildingFloors,FloorLoc,BuildAge,BuildType,ItemStatus,WarmType,NumOfBathrooms,ElgCredit,UsingStatus,StateBuilding,RentalIncome,Dues,Swap,Front,FuelType,Date,Address,xCoordinate,yCoordinate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = database.compileStatement(sqlQuery);
                statement.bindString(1,advTitle);
                statement.bindBlob(2,kayıtedilecekImage);
                statement.bindString(3, String.valueOf(price));
                statement.bindString(4,advStatus);
                statement.bindString(5,roomNum);
                statement.bindString(6, String.valueOf(squareMeters));
                statement.bindString(7, String.valueOf(buildingFloors));
                statement.bindString(8, String.valueOf(floorLoc));
                statement.bindString(9, String.valueOf(buildAge));
                statement.bindString(10,buildType);
                statement.bindString(11,itemStatus);
                statement.bindString(12,warmType);
                statement.bindString(13, String.valueOf(numOfBathr));
                statement.bindString(14,elgForCredit);
                statement.bindString(15,usingStatus);
                statement.bindString(16,stateBuilding);
                statement.bindString(17, String.valueOf(rentalIncome));
                statement.bindString(18, String.valueOf(dues));
                statement.bindString(19,swap);
                statement.bindString(20,front);
                statement.bindString(21,fuelType);
                statement.bindString(22,date);
                statement.bindString(23,address);
                statement.bindLong(24,latitude);
                statement.bindLong(25,longitude);
                statement.execute();

                Nesneleri_temizle();
                Toast.makeText(getApplicationContext(),"Kayıt başarıyla eklendi",Toast.LENGTH_SHORT).show();


            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    private Bitmap imageSmall(Bitmap img) {
        return Bitmap.createScaledBitmap(img,120,150,true);
    }

    public void selectImage(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},imgNoPermissionCod);
        }
        else{

            Intent imageGet=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageGet,imgPermissionCod);

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==imgPermissionCod){
            if(resultCode==RESULT_OK && data !=null){
                Uri imgUrl=data.getData();

                try {
                    if(Build.VERSION.SDK_INT>=28){
                        ImageDecoder.Source imgSource=ImageDecoder.createSource(this.getContentResolver(),imgUrl);
                        selectedİmg=ImageDecoder.decodeBitmap(imgSource);
                        imageAdv.setImageBitmap(selectedİmg);
                    }else{
                        selectedİmg= MediaStore.Images.Media.getBitmap(this.getContentResolver(),imgUrl);
                        imageAdv.setImageBitmap(selectedİmg);
                    }


                    btnSubmitAdv.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void Nesneleri_temizle(){
        editTxtTitle.setText("");
        editTxtAddress.setText("");
        editTxtPrice.setText("");
        editTxtDues.setText("");
        editTxtBuildingFloors.setText("");
        editTxtBuildAge.setText("");
        editTxtFloorLoc.setText("");
        editTxtRentalIncome.setText("");
        editTxtWarmType.setText("");
        editTxtRoomNum.setText("");
        editTxtSquareMt.setText("");

        // firstImage= BitmapFactory.decodeResource(this.getResources(),R.drawable.);
        imageAdv.setImageBitmap(firstImage);
        btnSubmitAdv.setEnabled(false);
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

                gMap.clear();

                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                gMap.addMarker(markerOptions);


            }
        });
    }
}