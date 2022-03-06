package com.example.ar_realestate;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class Advertisement implements Serializable {
    private String  advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address,city,town;
    private  int advId, price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;

    private long latitude,longitude;
    //private int adv_image;

    private  Bitmap advImage;
    private byte[] kayıtImage;

    public Advertisement() {
    }

    // imageyi bitmap olarak değiştirdim
    public Advertisement(int advId, Bitmap adv_image,String advTitle, String address,int pric) {
        this.advTitle = advTitle;
        this.address = address;
        this.price = pric;
        this.advImage = adv_image;
        this.advId=advId;
    }

    public byte[] getKayıtImage() {
        return kayıtImage;
    }

    public void setKayıtImage(byte[] kayıtImage) {
        this.kayıtImage = kayıtImage;
    }

    public Advertisement(String advTitle, Bitmap advImage, int price, String advStatus, String roomNum, int squareMeters, int buildingFloors, int floorLoc, int buildAge, String buildType, String itemStatus, String warmType, int numOfBathr, String elgForCredit, String usingStatus, String stateOfBuilding, int rentalIncome, int dues, String swap, String front, String fuelType, String date, String address, String city, String town) {
        this.advTitle = advTitle;
        this.advImage=advImage;
        this.price=price;
        this.advStatus=advStatus;
        this.roomNum=roomNum;
        this.squareMeters=squareMeters;
        this.buildingFloors=buildingFloors;
        this.floorLoc=floorLoc;
        this.buildAge=buildAge;
        this.buildType=buildType;
        this.itemStatus=itemStatus;
        this.warmType=warmType;
        this.numOfBathr=numOfBathr;
        this.elgForCredit=elgForCredit;
        this.usingStatus=usingStatus;
        this.stateBuilding=stateOfBuilding;
        this.rentalIncome=rentalIncome;
        this.dues=dues;
        this.swap=swap;
        this.front=front;
        this.fuelType=fuelType;
        this.date=date;
        this.address=address;
        this.city=city;
        this.town=town;
    }

    public String getTown() { return town; }
    public void setTown(String town) { this.town = town; }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getAdvId() {
        return advId;
    }
    public void setAdvId(int advId) {
        this.advId = advId;
    }
    public Bitmap getAdvImage() {
        return advImage;
    }
    public void setAdvImage(Bitmap advImage) {
        this.advImage = advImage;
    }
    public String getAdvTitle() {
        return advTitle;
    }
    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }
    public String getAdvStatus() {
        return advStatus;
    }
    public void setAdvStatus(String advStatus) {
        this.advStatus = advStatus;
    }
    public String getWarmType() {
        return warmType;
    }
    public void setWarmType(String warmType) {
        this.warmType = warmType;
    }
    public String getElgForCredit() {
        return elgForCredit;
    }
    public void setElgForCredit(String elgForCredit) {
        this.elgForCredit = elgForCredit;
    }
    public String getUsingStatus() {
        return usingStatus;
    }
    public void setUsingStatus(String usingStatus) {
        this.usingStatus = usingStatus;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    public int getSquareMeters() {
        return squareMeters;
    }
    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }
    public int getFloorLoc() {
        return floorLoc;
    }
    public void setFloorLoc(int floorLoc) {
        this.floorLoc = floorLoc;
    }
    public int getBuildAge() {
        return buildAge;
    }
    public void setBuildAge(int buildAge) {
        this.buildAge = buildAge;
    }
    public int getNumOfBathr() {
        return numOfBathr;
    }
    public void setNumOfBathr(int numOfBathr) {
        this.numOfBathr = numOfBathr;
    }
    public int getRentalIncome() {
        return rentalIncome;
    }
    public void setRentalIncome(int rentalIncome) {
        this.rentalIncome = rentalIncome;
    }
    public long getLatitude() {
        return latitude;
    }
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
    public long getLongitude() {
        return longitude;
    }
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
    public String getBuildType() {
        return buildType;
    }
    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }
    public String getItemStatus() {
        return itemStatus;
    }
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
    public String getSwap() {
        return swap;
    }
    public void setSwap(String swap) {
        this.swap = swap;
    }
    public String getFront() {
        return front;
    }
    public void setFront(String front) {
        this.front = front;
    }
    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getBuildingFloors() {
        return buildingFloors;
    }
    public void setBuildingFloors(int buildingFloors) {
        this.buildingFloors = buildingFloors;
    }
    public int getDues() {
        return dues;
    }
    public void setDues(int dues) {
        this.dues = dues;
    }
    public String getStateBuilding() {
        return stateBuilding;
    }
    public void setStateBuilding(String stateBuilding) {
        this.stateBuilding = stateBuilding;
    }

}
