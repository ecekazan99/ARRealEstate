package com.example.ar_realestate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "FuelType TEXT,Date DATE,Address TEXT,xCoordinate REAL,yCoordinate REAL );");

        db.execSQL("CREATE TABLE IF NOT EXISTS  AdvertisementImage( ImageId  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "advImage  BLOB, AdvId INTEGER,  FOREIGN KEY (AdvId) REFERENCES Advertisements(AdvId));");

        db.execSQL("CREATE TABLE IF NOT EXISTS UserInformation (UserId INTEGER PRIMARY KEY AUTOINCREMENT,UserName TEXT NOT NULL," +
                " UserSurname TEXT NOT NULL, MailAddress TEXT NOT NULL,Password TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Advertisements;");
        db.execSQL("DROP TABLE IF EXISTS AdvertisementImage;");
        db.execSQL("DROP TABLE IF EXISTS UserInformation;");
        onCreate(db);
    }
}
