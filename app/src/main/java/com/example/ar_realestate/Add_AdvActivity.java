package com.example.ar_realestate;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Add_AdvActivity extends AppCompatActivity implements OnMapReadyCallback {

    /* Button  next;
    ImageSwitcher imageView;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    ArrayList<Uri> mArrayUri;
    int position = 0;
    List <String> imagesEncodedList;*/

     /* private ArrayList<Bitmap>SelectedImg=new ArrayList<>();
    private ArrayList<Bitmap>SmallestedImg=new ArrayList<>();
    private ArrayList<ImageView>Imgs=new ArrayList<>();*/


    GoogleMap gMap;
    private TextView txtViewDate;
    private EditText editTxtTitle,editTxtPrice, editTxtSquareMt,editTxtBuildingFloors, editTxtFloorLoc,editTxtBuildAge,editTxtNumofBath,
            editTxtRentalIncome,editTxtDues,editTxtAddress;
    private ImageView imageAdv,imageAdv2,imageAdv3;
    private Bitmap selectedİmg,smallestedImg,firstImage;
    private Button btnSubmitAdv;

    static public boolean add_Adv=false;
    static public AdvDetail advDetailLast;

    private Spinner spinnerAdvStatus,spinnerRoomNum, spinnerBuildType,spinnerItemStatus,spinnerWarmType,spinnerElgbCredit,spinnerUsingStatus, spinnerStateOfBuilding,spinnerSwap,spinnerFront,spinnerFuelType,spinnerCity,spinnerTown;

    private int imgNoPermissionCod=0,imgPermissionCod=1;

    String advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address,city,town;
    int price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;
    long latitude,longitude;

    private CitiesAndTownInsert citiesAndTownInsert;

    public static int cityId;
    public static String[] cities=new String[81];
    public static ArrayList<String> districties=new ArrayList<>();
    ArrayAdapter<String> adapterCities;
    ArrayAdapter<String> adapterTowns;


    private void init(){ // initialize part
        citiesAndTownInsert=new CitiesAndTownInsert();
        citiesAndTownInsert.insertCity();
        getCities();
        editTxtTitle=(EditText) findViewById(R.id.addAdv_editTextAdvTitle);
        editTxtPrice=(EditText) findViewById(R.id.addAdv_editTextPrice);
        spinnerAdvStatus=(Spinner)findViewById(R.id.addAdv_spinnerAdvStatus);
        spinnerRoomNum=(Spinner) findViewById(R.id.addAdv_spinnerRoomNum);
        editTxtSquareMt=(EditText) findViewById(R.id.addAdv_editTextSquareMeter);
        editTxtBuildingFloors=(EditText)findViewById(R.id.addAdv_editTextBuildingFloors);
        editTxtFloorLoc=(EditText) findViewById(R.id.addAdv_editTextFloorLoc);
        editTxtBuildAge=(EditText) findViewById(R.id.addAdv_editTextBuildAge);
        spinnerBuildType=(Spinner)findViewById(R.id.addAdv_spinnerBuildType);
        spinnerItemStatus=(Spinner)findViewById(R.id.addAdv_spinnerItemStatus);
        spinnerWarmType=(Spinner) findViewById(R.id.addAdv_spinnerWarmType);
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
        spinnerCity=(Spinner)findViewById(R.id.addAdv_spinnerCity);
        spinnerTown=(Spinner)findViewById(R.id.addAdv_spinnerTown);

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

        ArrayAdapter<CharSequence>adapterRoomNum=ArrayAdapter.createFromResource(this,R.array.RoomNumber, android.R.layout.simple_spinner_item);
        adapterRoomNum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNum.setAdapter(adapterRoomNum);
        spinnerRoomNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                roomNum=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence>adapterWarmType=ArrayAdapter.createFromResource(this,R.array.WarmType, android.R.layout.simple_spinner_item);
        adapterWarmType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWarmType.setAdapter(adapterWarmType);
        spinnerWarmType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                warmType=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapterTowns=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,districties);
        adapterTowns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTown.setAdapter(adapterTowns);

        adapterCities=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cities);
        adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCities);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city=adapterView.getItemAtPosition(i).toString();
                districties.clear();
                getTown(city);
                adapterTowns=new ArrayAdapter<String>(Add_AdvActivity.this,android.R.layout.simple_spinner_item,districties);
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
        if(advStatus.equals("Select Adv Status") || buildType.equals("Select Build Type") || itemStatus.equals("Select Item Status") ||
                elgForCredit.equals("Select Elg Credit") || usingStatus.equals("Select Using Status") || stateBuilding.equals("Select State Building")||
                swap.equals("Select Swap")|| front.equals("Select Front") || fuelType.equals("Select Fuel Type") ||
                roomNum.equals("Select Room Number") || warmType.equals("Select Warm Type") || city.equals("Select City") ){
            checkEmpty =false;

            Toast.makeText(getApplicationContext(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(advTitle)  || TextUtils.isEmpty(address)|| TextUtils.isEmpty(editTxtPrice.getText().toString())||
                TextUtils.isEmpty(editTxtSquareMt.getText().toString())||TextUtils.isEmpty(editTxtBuildingFloors.getText().toString())||
            TextUtils.isEmpty(editTxtFloorLoc.getText().toString()) ||TextUtils.isEmpty(editTxtBuildAge.getText().toString())||
                TextUtils.isEmpty(editTxtNumofBath.getText().toString())||TextUtils.isEmpty(editTxtRentalIncome.getText().toString())||TextUtils.isEmpty(editTxtDues.getText().toString())){

            checkEmpty=false;
            Toast.makeText(getApplicationContext(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();

        }else if(!TextUtils.isDigitsOnly(editTxtPrice.getText()) || !TextUtils.isDigitsOnly(editTxtSquareMt.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildingFloors.getText()) || !TextUtils.isDigitsOnly(editTxtFloorLoc.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildAge.getText()) || !TextUtils.isDigitsOnly(editTxtRentalIncome.getText())||
                !TextUtils.isDigitsOnly(editTxtDues.getText()) || !TextUtils.isDigitsOnly(editTxtNumofBath.getText())) {
            checkEmpty =false;
            Toast.makeText(getApplicationContext(),"Please Incorrect Inputs",Toast.LENGTH_SHORT).show();
        }
        System.out.println("Girildi 1: "+ city);
        return checkEmpty;
    }

    public void saveAdv(View view) {

         try {
            advTitle=editTxtTitle.getText().toString();
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
         }catch (Exception e ){
             System.out.println("HATAAAAAAAAAAAAA");
         }
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        smallestedImg=imageSmall(selectedİmg);
        smallestedImg.compress(Bitmap.CompressFormat.PNG,75,outputStream);
        byte[] kayıtedilecekImage=outputStream.toByteArray();

        if(advInputControl()==true){
             try {

                MainActivity.database.onCreate(MainActivity.db);
                String sqlQuery="INSERT INTO Advertisements (AdvTitle,AdvImage,Price,AdvStatus,RoomNum,SquareMeter,BuildingFloors,FloorLoc,BuildAge,BuildType,ItemStatus,WarmType,NumOfBathrooms,ElgCredit,UsingStatus,StateBuilding,RentalIncome,Dues,Swap,Front,FuelType,Date,Address,Cities,Town,xCoordinate,yCoordinate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = MainActivity.db.compileStatement(sqlQuery);
                statement.bindString(1,advTitle);
                statement.bindBlob(2,kayıtedilecekImage);
                statement.bindString(3, String.valueOf(price));
                statement.bindString(4,advStatus);
                System.out.println("Girildi 2");
                statement.bindString(5,roomNum);
                System.out.println("Girildi 3");
                statement.bindString(6, String.valueOf(squareMeters));
                statement.bindString(7, String.valueOf(buildingFloors));
                statement.bindString(8, String.valueOf(floorLoc));
                statement.bindString(9, String.valueOf(buildAge));
                statement.bindString(10,buildType);
                statement.bindString(11,itemStatus);
                statement.bindString(12,warmType);
                System.out.println("Girildi 4");
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
                statement.bindString(24,city);
                statement.bindString(25,town);
                statement.bindLong(26,  latitude);
                statement.bindLong(27, longitude);
                statement.execute();

                Nesneleri_temizle();
                Toast.makeText(getApplicationContext(),"Kayıt başarıyla eklendi",Toast.LENGTH_SHORT).show();
                add_Adv=true;

               /* if(add_Adv==true)
                {
                    System.out.println("jgdfgm");
                    advDetailLast=new AdvDetail(advTitle,smallestedImg,price,advStatus,roomNum,squareMeters,buildingFloors,floorLoc,
                            buildAge,buildType,itemStatus,warmType,numOfBathr,elgForCredit,usingStatus,
                            stateBuilding,rentalIncome,dues,swap,front,fuelType,date,address,city,town);
                    Intent detailIntent=new Intent(this,AdvDetailActivity.class);

                    startActivity(detailIntent);
                }*/
                int lastInsertedAdvId = 0;
                Cursor c=MainActivity.db.rawQuery("select last_insert_rowid()",null);
                if (c!= null && c.moveToFirst()) {
                    lastInsertedAdvId = c.getInt(0); //The 0 is the column index, we only have 1 column, so the index is 0
                }
                c.close();
                // int advTitleIndex=cursor.getColumnIndex("AdvTitle");
                System.out.println("ADV ID Last Insert :"+lastInsertedAdvId);

                String sqlQueryImage="INSERT INTO AdvertisementImage (AdvImage,AdvId)VALUES(?,?)";
                SQLiteStatement statementImg = MainActivity.db.compileStatement(sqlQueryImage);
                statementImg.bindBlob(1,kayıtedilecekImage);
                statementImg.bindString(2,String.valueOf(lastInsertedAdvId));
                statementImg.execute();



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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==imgPermissionCod){
            if(resultCode==RESULT_OK && data !=null){
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
        editTxtSquareMt.setText("");

        spinnerAdvStatus.setSelection(0);
        spinnerBuildType.setSelection(0);
        spinnerFront.setSelection(0);
        spinnerElgbCredit.setSelection(0);
        spinnerFuelType.setSelection(0);
        spinnerItemStatus.setSelection(0);
        spinnerStateOfBuilding.setSelection(0);
        spinnerSwap.setSelection(0);
        spinnerUsingStatus.setSelection(0);
        spinnerCity.setSelection(0);
        spinnerWarmType.setSelection(0);
        spinnerRoomNum.setSelection(0);

        // firstImage= BitmapFactory.decodeResource(this.getResources(),R.drawable.);
        imageAdv.setImageBitmap(firstImage);
        btnSubmitAdv.setEnabled(false);
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
            // System.out.println(cursor.getString(0));
            cursor.moveToNext();

        }

    }
    public void getTown(String cityName){
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
}