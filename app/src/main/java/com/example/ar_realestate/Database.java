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

       // db.execSQL("CREATE TABLE Advertisements

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Advertisements");

        onCreate(db);
    }
}
