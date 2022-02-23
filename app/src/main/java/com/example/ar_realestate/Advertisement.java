package com.example.ar_realestate;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Advertisement {
    private String  advTitle,advStatus,roomNum,warmType,elgForCredit,usingStatus,buildType,itemStatus,stateBuilding,swap,front,fuelType,date,address;
    private  int advId, price,squareMeters,buildingFloors,floorLoc,buildAge,numOfBathr,rentalIncome,dues;

    private long latitude,longitude;
    private int adv_image;

    public Advertisement() {
    }

    public Advertisement(int advId, int adv_image,String advTitle, String address,int pric) {
        this.advTitle = advTitle;
        this.address = address;
        this.price = pric;
        this.adv_image = adv_image;
        this.advId=advId;
    }

    public Advertisement(String advTitle, int price, String advStatus, String roomNum, int squareMeters, int buildingFloors, int floorLoc, int buildAge, String buildType, String itemStatus, String warmType, int numOfBathr, String elgForCredit, String usingStatus, String stateOfBuilding, int rentalIncome, int dues, String swap, String front, String fuelType, String date, String address) {
        this.advTitle = advTitle;
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
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }




    public int getAdv_image() {
        return adv_image;
    }

    public void setAdv_image(int adv_image) {
        this.adv_image = adv_image;
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

    static public ArrayList<Advertisement> getData(Context context){
        ArrayList<Advertisement> advertisementList=new ArrayList<>();

        ArrayList<String> advTitleList=new ArrayList<>();
        ArrayList<String> advStatusList=new ArrayList<>();
        ArrayList<String> roomNumList=new ArrayList<>();
        ArrayList<String> warmTypeList=new ArrayList<>();
        ArrayList<String> elgForCreditList=new ArrayList<>();
        ArrayList<String> usingStatusList=new ArrayList<>();
        ArrayList<String> buildTypeList=new ArrayList<>();
        ArrayList<String> itemStatusList=new ArrayList<>();
        ArrayList<String> stateBuildingList=new ArrayList<>();
        ArrayList<String> swapList=new ArrayList<>();
        ArrayList<String> frontList=new ArrayList<>();
        ArrayList<String> fuelTypeList=new ArrayList<>();
        ArrayList<String> dateList=new ArrayList<>();
        ArrayList<String> addressList=new ArrayList<>();

        ArrayList<Integer> priceList=new ArrayList<>();
        ArrayList<Integer> squareMetersList=new ArrayList<>();
        ArrayList<Integer> buildingFloorsList=new ArrayList<>();
        ArrayList<Integer> floorLocList=new ArrayList<>();
        ArrayList<Integer> buildAgeList=new ArrayList<>();
        ArrayList<Integer> numOfBathrList=new ArrayList<>();
        ArrayList<Integer> rentalIncomeList=new ArrayList<>();
        ArrayList<Integer> duesList=new ArrayList<>();

        ArrayList<Bitmap> ImageList=new ArrayList<>();

        ArrayList<Long>xCoordinateList=new ArrayList<>();
        ArrayList<Long>yCoordinateList=new ArrayList<>();

        try {
          //  SQLiteDatabase database=context.openOrCreateDatabase("Temp",Context.MODE_PRIVATE,null);
            Cursor cursor=MainActivity.db.rawQuery("SELECT * FROM Advertisements",null);

            int advTitleIndex=cursor.getColumnIndex("AdvTitle");
            int ImageIndex=cursor.getColumnIndex("AdvImage");
            int priceIncex=cursor.getColumnIndex("Price");
            int advStatusIndex=cursor.getColumnIndex("AdvStatus");
            int roomNumIndex=cursor.getColumnIndex("RoomNum");
            int squareMeterIndex=cursor.getColumnIndex("SquareMeter");
            int buildingFloorsIndex=cursor.getColumnIndex("BuildingFloors");
            int floorLocIndex=cursor.getColumnIndex("FloorLoc");
            int buildAgeIndex=cursor.getColumnIndex("BuildAge");
            int buildTypeIndex=cursor.getColumnIndex("BuildType");
            int itemStatusIndex=cursor.getColumnIndex("ItemStatus");
            int warmTypeIndex=cursor.getColumnIndex("WarmType");
            int numOfBathroomsIndex=cursor.getColumnIndex("NumOfBathrooms");
            int elgCreditIndex=cursor.getColumnIndex("ElgCredit");
            int usingStatusIndex=cursor.getColumnIndex("UsingStatus");
            int stateBuildingIndex=cursor.getColumnIndex("StateBuilding");
            int rentalIncomeIndex=cursor.getColumnIndex("RentalIncome");
            int duesIndex=cursor.getColumnIndex("Dues");
            int swapIndex=cursor.getColumnIndex("Swap");
            int frontIndex=cursor.getColumnIndex("Front");
            int fuelTypeIndex=cursor.getColumnIndex("FuelType");
            int dateIndex=cursor.getColumnIndex("Date");
            int addressIndex=cursor.getColumnIndex("Address");
            int xCoordinateIndex=cursor.getColumnIndex("xCoordinate");
            int yCoordinateIndex=cursor.getColumnIndex("yCoordinate");

            while (cursor.moveToNext()){
                advTitleList.add(cursor.getString(advTitleIndex));
                advStatusList.add(cursor.getString(advStatusIndex));
                roomNumList.add(cursor.getString(roomNumIndex));
                warmTypeList.add(cursor.getString(warmTypeIndex));
                elgForCreditList.add(cursor.getString(elgCreditIndex));
                usingStatusList.add(cursor.getString(usingStatusIndex));
                buildTypeList.add(cursor.getString(buildTypeIndex));
                itemStatusList.add(cursor.getString(itemStatusIndex));
                stateBuildingList.add(cursor.getString(stateBuildingIndex));
                swapList.add(cursor.getString(swapIndex));
                frontList.add(cursor.getString(frontIndex));
                fuelTypeList.add(cursor.getString(fuelTypeIndex));
                dateList.add(cursor.getString(dateIndex));
                addressList.add(cursor.getString(addressIndex));

                priceList.add(cursor.getInt(priceIncex));
                squareMetersList.add(cursor.getInt(squareMeterIndex));
                buildingFloorsList.add(cursor.getInt(buildingFloorsIndex));
                floorLocList.add(cursor.getInt(floorLocIndex));
                buildAgeList.add(cursor.getInt(buildAgeIndex));
                numOfBathrList.add(cursor.getInt(numOfBathroomsIndex));
                rentalIncomeList.add(cursor.getInt(rentalIncomeIndex));
                duesList.add(cursor.getInt(duesIndex));

                byte[] imageByte=cursor.getBlob(ImageIndex);
                Bitmap imageAdv= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                ImageList.add(imageAdv);

                xCoordinateList.add(cursor.getLong(xCoordinateIndex));
                yCoordinateList.add(cursor.getLong(yCoordinateIndex));

            }
            cursor.close();
            for (int i=0;i<advTitleList.size();i++){
                Advertisement adv=new Advertisement();
                adv.setAdvTitle(advTitleList.get(i));
              //  adv.setAdv_image(ImageList.get(i));
                adv.setPrice(priceList.get(i));
                adv.setAdvStatus(advStatusList.get(i));
                adv.setRoomNum(roomNumList.get(i));
                adv.setSquareMeters(squareMetersList.get(i));
                adv.setBuildingFloors(buildingFloorsList.get(i));
                adv.setFloorLoc(floorLocList.get(i));
                adv.setBuildAge(buildAgeList.get(i));
                adv.setBuildType(buildTypeList.get(i));
                adv.setItemStatus(itemStatusList.get(i));
                adv.setWarmType(warmTypeList.get(i));
                adv.setNumOfBathr(numOfBathrList.get(i));
                adv.setElgForCredit(elgForCreditList.get(i));
                adv.setUsingStatus(usingStatusList.get(i));
                adv.setStateBuilding(stateBuildingList.get(i));
                adv.setRentalIncome(rentalIncomeList.get(i));
                adv.setDues(duesList.get(i));
                adv.setSwap(swapList.get(i));
                adv.setFront(frontList.get(i));
                adv.setFuelType(fuelTypeList.get(i));
                adv.setDate(dateList.get(i));
                adv.setAddress(addressList.get(i));
                adv.setLatitude(xCoordinateList.get(i));
                adv.setLongitude(yCoordinateList.get(i));



                advertisementList.add(adv);

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return advertisementList;
    }
}