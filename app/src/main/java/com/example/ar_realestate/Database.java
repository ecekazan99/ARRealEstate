package com.example.ar_realestate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="RealEstate";
    private static final int VERSION=1;

    public Database(Context c){

        super(c, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Advertisements (AdvId INTEGER PRIMARY KEY AUTOINCREMENT,AdvTitle TEXT NOT NULL UNIQUE," +
                "AdvImage BLOB,Price INTEGER,AdvStatus TEXT,RoomNum TEXT,SquareMeter INTEGER,BuildingFloors INTEGER," +
                "FloorLoc INTEGER,BuildAge INTEGER,BuildType TEXT,ItemStatus TEXT,WarmType TEXT,NumOfBathrooms INTEGER," +
                "ElgCredit TEXT,UsingStatus TEXT,StateBuilding TEXT,RentalIncome INTEGER,Dues INTEGER,Swap TEXT,Front TEXT," +
                "FuelType TEXT,Date TEXT,Address TEXT,Cities TEXT,Town TEXT,xCoordinate REAL,yCoordinate REAL );");

        db.execSQL("CREATE TABLE IF NOT EXISTS  AdvertisementImage( ImageId  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "advImage  BLOB, AdvId INTEGER,  FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId));");

        db.execSQL("CREATE TABLE IF NOT EXISTS UserInformation (UserId INTEGER PRIMARY KEY AUTOINCREMENT,UserName TEXT NOT NULL," +
                " UserSurname TEXT NOT NULL, MailAddress TEXT NOT NULL,Password TEXT NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Favorite (UserId INTEGER,AdvId INTEGER,FavoriteStatus INTEGER,FOREIGN KEY (UserId) REFERENCES UserInformation(UserId), FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId));");

        db.execSQL("CREATE TABLE IF NOT EXISTS UserPastAdvertisement (UserId INTEGER,AdvId INTEGER,FOREIGN KEY (UserId) REFERENCES UserInformation(UserId), FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId));");

        db.execSQL("CREATE TABLE IF NOT EXISTS Cities (CityId INTEGER PRIMARY KEY AUTOINCREMENT,CityName TEXT NOT NULL UNIQUE);");

        db.execSQL("CREATE TABLE IF NOT EXISTS District(DistrictId INTEGER PRIMARY KEY AUTOINCREMENT,DistrictName TEXT NOT NULL,CityId INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS  UserAdvertisement( UserId INTEGER,AdvId INTEGER, FOREIGN KEY (UserId) REFERENCES UserInformation(UserId), FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Advertisements;");
        db.execSQL("DROP TABLE IF EXISTS AdvertisementImage;");
        db.execSQL("DROP TABLE IF EXISTS UserInformation;");
        db.execSQL("DROP TABLE IF EXISTS Cities;");
        db.execSQL("DROP TABLE IF EXISTS District;");
        db.execSQL("DROP TABLE IF EXISTS UserAdvertisement;");
        db.execSQL("DROP TABLE IF EXISTS Favorite;");
        db.execSQL("DROP TABLE IF EXISTS UserPastAdvertisement;");
        onCreate(db);
    }

    public boolean checkEmailExist(String email){

        try{
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM UserInformation " +
                    "WHERE MailAddress = ?",new String[]{email});

            if(cursor.moveToFirst()){
                return false;
            }
        }catch (Exception e){
            return true;
        }
        return true;
    }

    public User loginUser(String email, String password){
        User user=null;
        try{
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
          Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM UserInformation " +
                  "WHERE MailAddress = ? AND Password = ?",new String[]{email,password});

          if(cursor.moveToFirst()){
              user=new User();
              user.setUserId(cursor.getInt(0));
              user.setUserName(cursor.getString(1));
              user.setUserSurname(cursor.getString(2));
              user.setMailAddress(cursor.getString(3));
              user.setPassword(cursor.getString(4));
          }

        }catch (Exception e){
            user=null;
        }
        return user;
    }

    public int updateUser(int id,String name, String surname, String email, String password, String newPassword){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("UserName",name);
            values.put("UserSurname",surname);
            values.put("MailAddress",email);
            values.put("Password",password);

            db.update("UserInformation", values, "UserId=?", new String[]{String.valueOf(id)});
            db.close();

        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    public int updateMyAdv(int advId, String advTitle, byte[]  advImage, int price, String advStatus, String roomNum, int squareMeter,int buildingFloors,
                           int floorLoc,int buildAge,String buildType,String itemStatus,String warmTpe,int numOfBathrooms,String elgCredit,
                           String usingStatus,String stateBuilding,int rentalIncome, int dues, String swap,String front,String fuelType,String date,
                           String address, String city,String town, long xCoordinate, long yCoordinate){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("AdvTitle",advTitle);
            values.put("AdvImage",advImage);
            values.put("Price",price);
            values.put("AdvStatus",advStatus);
            values.put("RoomNum",roomNum);
            values.put("SquareMeter",squareMeter);
            values.put("BuildingFloors",buildingFloors);
            values.put("FloorLoc",floorLoc);
            values.put("BuildAge",buildAge);
            values.put("BuildType",buildType);
            values.put("ItemStatus",itemStatus);
            values.put("WarmType",warmTpe);
            values.put("NumOfBathrooms",numOfBathrooms);
            values.put("ElgCredit",elgCredit);
            values.put("UsingStatus",usingStatus);
            values.put("StateBuilding",stateBuilding);
            values.put("RentalIncome",rentalIncome);
            values.put("Dues",dues);
            values.put("Swap",swap);
            values.put("Front",front);
            values.put("FuelType",fuelType);
            values.put("Date",date);
            values.put("Address",address);
            values.put("Cities",city);
            values.put("Town",town);
            values.put("xCoordinate",xCoordinate);
            values.put("yCoordinate",yCoordinate);

            db.update("Advertisements", values, "AdvId=?", new String[]{String.valueOf(advId)});
            db.close();

        }catch (Exception e){
            return 0;
        }
        return 1;
    }
    public boolean checkAdvTitleExist(String advTitle){

        try{
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM Advertisements " +
                    "WHERE AdvTitle = ?",new String[]{advTitle});

            if(cursor.moveToFirst()){
                return false;
            }
        }catch (Exception e){
            return true;
        }
        return true;
    }

}
