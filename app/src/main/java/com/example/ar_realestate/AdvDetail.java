package com.example.ar_realestate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class AdvDetail {
    private String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address;
    private int advId,price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;

    private long latitude, longitude;

    // imageyı düzelltim int türünden bitmap türüne
    private Bitmap adv_image;
    public AdvDetail(int advId, Bitmap adv_image,String advTitle, String address,int pric) {
        this.advTitle = advTitle;
        this.address = address;
        this.price = pric;
        this.adv_image=adv_image;
        this.advId=advId;
    }

    public AdvDetail(String advTitle,Bitmap adv_image, int price, String advStatus, String roomNum, int squareMeters, int buildingFloors, int floorLoc, int buildAge, String buildType, String itemStatus, String warmType, int numOfBathr, String elgForCredit, String usingStatus, String stateOfBuilding, int rentalIncome, int dues, String swap, String front, String fuelType, String date, String address) {
        this.advTitle = advTitle;
        this.adv_image=adv_image;
        this.price = price;
        this.advStatus = advStatus;
        this.roomNum = roomNum;
        this.squareMeters = squareMeters;
        this.buildingFloors = buildingFloors;
        this.floorLoc = floorLoc;
        this.buildAge = buildAge;
        this.buildType = buildType;
        this.itemStatus = itemStatus;
        this.warmType = warmType;
        this.numOfBathr = numOfBathr;
        this.elgForCredit = elgForCredit;
        this.usingStatus = usingStatus;
        this.stateBuilding = stateOfBuilding;
        this.rentalIncome = rentalIncome;
        this.dues = dues;
        this.swap = swap;
        this.front = front;
        this.fuelType = fuelType;
        this.date = date;
        this.address = address;
    }

    public Bitmap getAdv_image() {
        return adv_image;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public String getAdvStatus() {
        return advStatus;
    }

    public String getWarmType() {
        return warmType;
    }

    public String getElgForCredit() {
        return elgForCredit;
    }

    public String getUsingStatus() {
        return usingStatus;
    }

    public int getPrice() {
        return price;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public int getFloorLoc() {
        return floorLoc;
    }

    public int getBuildAge() {
        return buildAge;
    }

    public int getNumOfBathr() {
        return numOfBathr;
    }

    public int getRentalIncome() {
        return rentalIncome;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public String getBuildType() {
        return buildType;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public String getSwap() {
        return swap;
    }

    public String getFront() {
        return front;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public int getBuildingFloors() {
        return buildingFloors;
    }

    public int getDues() {
        return dues;
    }

    public String getStateBuilding() {
        return stateBuilding;
    }

}