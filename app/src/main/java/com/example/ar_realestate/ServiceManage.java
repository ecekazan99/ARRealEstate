package com.example.ar_realestate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceManage {

    private static final String NAMESPACE="http://tempuri.org/";
    private static final String METHOD_NAME_ADDAdvertisement="addAdvertisement";
    private static final String URL="http://193.140.150.95/deuarSrv/WebService1.asmx";
    private SoapObject soapObject;
    private SoapSerializationEnvelope soapSerializationEnvelope;
    private HttpTransportSE httpTransportSE;


    public void addAdvertisement(String advTitle, int price, String advStatus, String roomNum, int squareMeter, int buildingFloors, int floorLoc, int buildAge, String buildType, String itemStatus, String warmType, int numOfBathRooms, String elgCredit, String usingStatus, String stateBuilding, int rentalIncome, int dues, String swap, String front, String fuelType, Date date, String address, String city, String town, double xCoordinate, double yCoordinate)
    {

        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_ADDAdvertisement);
            soapObject.addProperty("advTitle", advTitle);
            System.out.println("Priceeeee "+price);
            soapObject.addProperty("price", price);
            soapObject.addProperty("advStatus", advStatus);
            soapObject.addProperty("roomNum", roomNum);
            soapObject.addProperty("squareMeter", squareMeter);
            soapObject.addProperty("buildingFloors", buildingFloors);
            soapObject.addProperty("floorLoc", floorLoc);
            soapObject.addProperty("buildAge", buildAge);
            soapObject.addProperty("buildType", buildType);
            soapObject.addProperty("itemStatus", itemStatus);
            soapObject.addProperty("warmType", warmType);
            soapObject.addProperty("numOfBathRooms", numOfBathRooms);
            soapObject.addProperty("elgCredit", elgCredit);
            soapObject.addProperty("usingStatus", usingStatus);
            soapObject.addProperty("stateBuilding", stateBuilding);
            soapObject.addProperty("rentalIncome", rentalIncome);
            soapObject.addProperty("dues", dues);
            soapObject.addProperty("swap", swap);
            soapObject.addProperty("front", front);
            soapObject.addProperty("fuelType", fuelType);

            PropertyInfo pi = new PropertyInfo();
            pi.setName("date");
            pi.setValue(date.toString());
            pi.setType(Date.class);
            soapObject.addProperty(pi);

            System.out.println(date);
            soapObject.addProperty("address", address);
            soapObject.addProperty("city", city);
            soapObject.addProperty("town", town);
            soapObject.addProperty("xCoordinate", Double. toString(xCoordinate));
            soapObject.addProperty("yCoordinate",Double. toString(yCoordinate));

            soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.encodingStyle = "utf-8";
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_ADDAdvertisement,soapSerializationEnvelope);

            SoapObject response=(SoapObject) soapSerializationEnvelope.getResponse();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
