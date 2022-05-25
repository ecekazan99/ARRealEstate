package com.example.ar_realestate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
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

import com.example.ar_realestate.databinding.FragmentAddAdvBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class AddAdvFragment extends Fragment implements OnMapReadyCallback {
    private FragmentAddAdvBinding binding;
    GoogleMap gMap;
    private TextView txtViewDate;
    private EditText editTxtTitle,editTxtPrice, editTxtSquareMt,editTxtBuildingFloors, editTxtFloorLoc,editTxtBuildAge,editTxtNumofBath,
            editTxtRentalIncome,editTxtDues,editTxtAddress;

    private ImageView imageAdv,imageAdv2,imageAdv3;
    private Bitmap selectedİmg,smallestedImg,firstImage,firstSelectedImage;
    private Button btnSubmitAdv;
    ArrayList<Uri> mArrayUri;
    private Button next,previous;
    private int position=0;
    public static int imageCount=0;
    private ArrayList<Bitmap>imagesSelect;
    private ArrayList<Bitmap>imagesSmall;
    public static  Date endDate;
    static public boolean add_Adv=false;
    static public AdvDetail advDetailLast;

    private Spinner spinnerAdvStatus,spinnerRoomNum, spinnerBuildType,spinnerItemStatus,spinnerWarmType,spinnerElgbCredit,spinnerUsingStatus, spinnerStateOfBuilding,spinnerSwap,spinnerFront,spinnerFuelType,spinnerCity,spinnerTown;
    private int imgNoPermissionCod=0,imgPermissionCod=1;

    public static String advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address,city,town;
    int price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;
    Double latitude;
    Double longitude;

    private CitiesAndTownInsert citiesAndTownInsert;

    public static int cityId;
    public static int userId;
    public static int advId;
    public static String[] cities=new String[81];
    public static ArrayList<String> districties=new ArrayList<>();
    ArrayAdapter<String> adapterCities;
    ArrayAdapter<String> adapterTowns;

    public AddAdvFragment() {
        // Required empty public constructor
    }
    private void init(){ // initialize part
        citiesAndTownInsert=new CitiesAndTownInsert();
        citiesAndTownInsert.insertCity();
        getCities();
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
        mArrayUri=new ArrayList<>();
        imagesSelect=new ArrayList<Bitmap>();
        imagesSelect=new ArrayList<Bitmap>();
        next=(Button)binding.next;
        previous=(Button)binding.previous;
        btnSubmitAdv=(Button) binding.addAdvBtnSubmit;

        ArrayAdapter<CharSequence>adapterAdvStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.Adv_Status, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterBuildType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.BuildingType, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterItemStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.ItemStatus, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterElgbCredit=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.ElgForCredit, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterUsingStatus=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.UsingStatus, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterStateOfBuilding=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.StateBuilding, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterSwap=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Swap, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterFront=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Front, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterFuelType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.FuelType, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterRoomNum=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.RoomNumber, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence>adapterWarmType=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.WarmType, android.R.layout.simple_spinner_item);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddAdvBinding.inflate(inflater, container, false);
        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");
        if(user==null){
            FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,new LoginFragment());
            fragmentTransaction.commit();
        }
        else{
            userId=user.getUserId();
            SupportMapFragment supportMapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
            supportMapFragment.getMapAsync(this);
            init();
            binding.addAdvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ContextCompat.checkSelfPermission(getActivity().getBaseContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                imgNoPermissionCod);
                    }
                    else{
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select images"), imgPermissionCod);
                    }
                }
            });
            binding.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position<mArrayUri.size()-1)
                    {
                        position++;
                        imageAdv.setImageURI(mArrayUri.get(position));
                    }
                }
            });
            binding.previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position>0){
                        position--;
                        imageAdv.setImageURI(mArrayUri.get(position));
                    }
                }
            });

            binding.addAdvBtnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAdv(view);
                }
            });
        }

        return binding.getRoot();
    }
    private String getTodayDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        return strDate;
    }
    public boolean advInputControl(){
        Boolean checkEmpty =true;
        if(MainActivity.database.checkAdvTitleExist(advTitle)==false) {
            checkEmpty=false;
            Toast.makeText(getActivity(), "You cannot use this adv title", Toast.LENGTH_SHORT).show();
        }
        if(advStatus.equals("Select Adv Status") || buildType.equals("Select Build Type") || itemStatus.equals("Select Item Status") ||
                elgForCredit.equals("Select Elg Credit") || usingStatus.equals("Select Using Status") || stateBuilding.equals("Select State Building")||
                swap.equals("Select Swap")|| front.equals("Select Front") || fuelType.equals("Select Fuel Type") ||
                roomNum.equals("Select Room Number") || warmType.equals("Select Warm Type") || city.equals("Select City") ){
            checkEmpty =false;
            Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(advTitle)  || TextUtils.isEmpty(address)|| TextUtils.isEmpty(editTxtPrice.getText().toString())||
                TextUtils.isEmpty(editTxtSquareMt.getText().toString())||TextUtils.isEmpty(editTxtBuildingFloors.getText().toString())||
                TextUtils.isEmpty(editTxtFloorLoc.getText().toString()) ||TextUtils.isEmpty(editTxtBuildAge.getText().toString())||
                TextUtils.isEmpty(editTxtNumofBath.getText().toString())||TextUtils.isEmpty(editTxtRentalIncome.getText().toString())||
                TextUtils.isEmpty(editTxtDues.getText().toString())){
            checkEmpty=false;
            Toast.makeText(getActivity(),"Please fill in the blanks",Toast.LENGTH_SHORT).show();
        }else if(!TextUtils.isDigitsOnly(editTxtPrice.getText()) || !TextUtils.isDigitsOnly(editTxtSquareMt.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildingFloors.getText()) || !TextUtils.isDigitsOnly(editTxtFloorLoc.getText()) ||
                !TextUtils.isDigitsOnly(editTxtBuildAge.getText()) || !TextUtils.isDigitsOnly(editTxtRentalIncome.getText())||
                !TextUtils.isDigitsOnly(editTxtDues.getText()) || !TextUtils.isDigitsOnly(editTxtNumofBath.getText())) {
            checkEmpty =false;
            Toast.makeText(getActivity(),"Please Incorrect Inputs",Toast.LENGTH_SHORT).show();
        }
        return checkEmpty;
    }

    public void saveAdv(View view) {
        try {
            advTitle=editTxtTitle.getText().toString();
            date=getTodayDate();
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
            System.out.println("ERROR");
        }
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        smallestedImg=imageSmall(firstSelectedImage);
        smallestedImg.compress(Bitmap.CompressFormat.PNG,75,outputStream);
        byte[] Image=outputStream.toByteArray();
        if(advInputControl()==false){
            advInputControl();
        }
        if(advInputControl()==true){
            try {
                MainActivity.database.onCreate(MainActivity.db);
                String sqlQuery="INSERT INTO Advertisements (AdvTitle,AdvImage,Price,AdvStatus,RoomNum,SquareMeter,BuildingFloors,FloorLoc" +
                        ",BuildAge,BuildType,ItemStatus,WarmType,NumOfBathrooms,ElgCredit,UsingStatus,StateBuilding,RentalIncome,Dues,Swap,Front," +
                        "FuelType,Date,Address,Cities,Town,xCoordinate,yCoordinate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = MainActivity.db.compileStatement(sqlQuery);
                statement.bindString(1,advTitle);
                statement.bindBlob(2,Image);
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
                statement.bindString(24,city);
                statement.bindString(25,town);
                statement.bindDouble(26, latitude);
                statement.bindDouble(27, longitude);
                statement.execute();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String infi = df.format(date);
                System.out.println(date);

                try {
                    endDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(infi).getTime());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final ServiceManage serviceManage=new ServiceManage();
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                      serviceManage.addAdvertisement(advTitle,price,advStatus,roomNum,squareMeters,buildingFloors,floorLoc,buildAge,buildType,itemStatus,warmType,numOfBathr,elgForCredit,usingStatus,stateBuilding,rentalIncome,dues,
                                swap,front,fuelType,endDate,address,city,town,latitude,longitude);
                    }
                });
                thread.start();


                Object_Clear();
                Toast.makeText(getActivity(),"Adding Advertisement Successful ",Toast.LENGTH_SHORT).show();
                add_Adv=true;
                int lastInsertedAdvId = 0;
                Cursor c=MainActivity.db.rawQuery("select last_insert_rowid()",null);
                if (c!= null && c.moveToFirst()) {
                    lastInsertedAdvId = c.getInt(0);
                }
                c.close();
                advId=lastInsertedAdvId;
                System.out.println(lastInsertedAdvId);
                for (int i=0;i<imageCount;i++) {
                    ByteArrayOutputStream outputStreamMulti =new ByteArrayOutputStream();
                    smallestedImg=imageSmall(imagesSelect.get(i));
                    smallestedImg.compress(Bitmap.CompressFormat.PNG,75,outputStreamMulti);
                    byte[] imageMulti=outputStreamMulti.toByteArray();
                    String sqlQueryImage = "INSERT INTO AdvertisementImage (AdvImage,AdvId)VALUES(?,?)";
                    SQLiteStatement statementImg = MainActivity.db.compileStatement(sqlQueryImage);
                    statementImg.bindBlob(1, imageMulti);
                    statementImg.bindString(2, String.valueOf(lastInsertedAdvId));
                    statementImg.execute();
                }

               String sqlQueryUserAdv="INSERT INTO UserAdvertisement (UserId,AdvId)VALUES(?,?);";
                SQLiteStatement statementUserAdv = MainActivity.db.compileStatement(sqlQueryUserAdv);
                statementUserAdv.bindString(1,String.valueOf(userId));
                statementUserAdv.bindString(2,String.valueOf(advId));
                statementUserAdv.execute();

                if(add_Adv==true)
                {
                    advDetailLast=new AdvDetail(advId,advTitle,smallestedImg,price,advStatus,roomNum,squareMeters,buildingFloors,floorLoc,
                            buildAge,buildType,itemStatus,warmType,numOfBathr,elgForCredit,usingStatus,
                            stateBuilding,rentalIncome,dues,swap,front,fuelType,date.toString(),address,city,town);

                    replaceFragment(new AdvDetailFragment());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private Bitmap imageSmall(Bitmap img) {
        return Bitmap.createScaledBitmap(img,300,220,true);
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
        int count=0;
        if(requestCode==imgPermissionCod){
            if(resultCode==-1 && data !=null){
                try {
                    Uri imgUrl=data.getData();

                    if(data.getClipData()!=null)
                    {
                        count = data.getClipData().getItemCount();
                        for (int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            mArrayUri.add(imageUri);
                        }
                        for (int j = 0; j < mArrayUri.size(); j++) {
                            if(Build.VERSION.SDK_INT>=28)
                            {
                                ImageDecoder.Source imgSource=ImageDecoder.createSource(getActivity().getContentResolver(),mArrayUri.get(j));
                                imagesSelect.add(ImageDecoder.decodeBitmap(imgSource));
                            }
                            else
                            {
                                imagesSelect.add(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mArrayUri.get(j)));
                            }
                            firstSelectedImage = imagesSelect.get(0);
                            selectedİmg=imagesSelect.get(j);
                            imageAdv.setImageBitmap(selectedİmg);
                            imageCount++;

                        }
                    }
                    else if(data.getClipData()==null) {
                        imageCount=1;
                        imagesSelect.add(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imgUrl));
                        selectedİmg= imagesSelect.get(0);
                        firstSelectedImage = imagesSelect.get(0);
                        imageAdv.setImageBitmap(selectedİmg);
                    }

                    btnSubmitAdv.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void Object_Clear(){
        editTxtTitle.setText("");
        editTxtAddress.setText("");
        editTxtPrice.setText("");
        editTxtDues.setText("");
        editTxtBuildingFloors.setText("");
        editTxtBuildAge.setText("");
        editTxtFloorLoc.setText("");
        editTxtRentalIncome.setText("");
        editTxtSquareMt.setText("");
        editTxtNumofBath.setText("");
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
        imageAdv.setImageBitmap(firstImage);
        btnSubmitAdv.setEnabled(false);
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
                latitude=  latLng.latitude;
                longitude= latLng.longitude;
                gMap.clear();
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                gMap.addMarker(markerOptions);
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }


}