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
    private static final String METHOD_NAME_GETPRODUCT="getAdvertisements";
    private static final String METHOD_NAME_LOGINUSER="loginUser";
    private static final String METHOD_NAME_SIGNUPUSER="addNewUser";
    private static final String METHOD_NAME_ADDAdvertisement="addAdvertisement";
    private static final String METHOD_NAME_adduserAdv="addUserAdvertisement";
    private static final String METHOD_NAME_lastInsertAdvId="lastInsertAdvId";
    private static final String METHOD_NAME_CHECKEMAILEXIST="checkEmailExist";
    private static final String URL="http://193.140.150.95/deuarSrv/WebService1.asmx";

    private SoapObject soapObject;
    private SoapSerializationEnvelope soapSerializationEnvelope;
    private HttpTransportSE httpTransportSE;
    public Advertisement advertisement;
    public List<Advertisement>advertisementList;

    public User user;

    public static Bitmap convertStringToBitmap(String string) {
        byte[] byteArray1;
        byteArray1 = Base64.decode(string, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                byteArray1.length);
        return bmp;
    }


    public  User loginUser(String mailAddress, String password){
        user=new User();
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_LOGINUSER);
        soapObject.addProperty("mailAddress", mailAddress.toString());
        soapObject.addProperty("password", password.toString());

        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.encodingStyle = "utf-8";
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_LOGINUSER,soapSerializationEnvelope);
            SoapObject response=(SoapObject) soapSerializationEnvelope.bodyIn;
            SoapObject  responsePr = (SoapObject) response.getProperty(0);

            user.setUserId(Integer.parseInt(responsePr.getProperty("UserId").toString()));
            user.setUserName(responsePr.getProperty("UserName").toString());
            user.setUserSurname(responsePr.getProperty("UserSurname").toString());
            user.setMailAddress(responsePr.getProperty("MailAddress").toString());
            user.setPassword(responsePr.getProperty("Password").toString());


        }catch (Exception e){
            e.printStackTrace();
            user=null;
        }

        //System.out.println(user);
        return user;
    }

    public void addNewUser(String userName, String userSurname, String mailAddress, String password){
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_SIGNUPUSER);
        soapObject.addProperty("userName", userName.toString());
        soapObject.addProperty("userSurname", userSurname.toString());
        soapObject.addProperty("mailAddress", mailAddress.toString());
        soapObject.addProperty("password", password.toString());

        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.encodingStyle = "utf-8";
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_SIGNUPUSER,soapSerializationEnvelope);

            SoapObject response=(SoapObject) soapSerializationEnvelope.getResponse();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
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
    public void addUserAdvertisement(String userId, String advId)
    {
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_adduserAdv);
        soapObject.addProperty("userId", userId.toString());
        soapObject.addProperty("advId", advId.toString());


        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.encodingStyle = "utf-8";
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_adduserAdv,soapSerializationEnvelope);

            SoapObject response=(SoapObject) soapSerializationEnvelope.getResponse();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Servis tarafındaki lastInsertId güncellendi
    public String lastInsertAdvId(){
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_lastInsertAdvId);
        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;
        String lastInserId="";
        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_lastInsertAdvId,soapSerializationEnvelope);
            SoapObject response=(SoapObject) soapSerializationEnvelope.getResponse();
            SoapObject responsePr=(SoapObject) response.getProperty(0);

            lastInserId=responsePr.getProperty("AdvId").toString();

        }catch (Exception e){
            e.printStackTrace();

        }

        return lastInserId;
    }
    public String checkEmailExist(String mailAddress){

        String flag="false";
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_CHECKEMAILEXIST);
        soapObject.addProperty("mailAddress", mailAddress.toString());

        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.encodingStyle = "utf-8";
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_CHECKEMAILEXIST,soapSerializationEnvelope);
            SoapObject response=(SoapObject) soapSerializationEnvelope.bodyIn;
            flag= response.getProperty(0).toString();
            // System.out.println("asdfghgfd  "+response.getProperty(0).toString());

        }catch (Exception e){
            e.printStackTrace();

        }

        return flag;
    }

    public List<Advertisement> getAdvertisement(){
        advertisement=new Advertisement();
        advertisementList=new ArrayList<>();
        soapObject=new SoapObject(NAMESPACE,METHOD_NAME_GETPRODUCT);
        soapSerializationEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet=true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        httpTransportSE=new HttpTransportSE(URL);
        httpTransportSE.debug=true;

        try{
            httpTransportSE.call(NAMESPACE+METHOD_NAME_GETPRODUCT,soapSerializationEnvelope);
            SoapObject response=(SoapObject) soapSerializationEnvelope.getResponse();
            for (int i=0;i<response.getPropertyCount();i++){
                SoapObject responsePr=(SoapObject) response.getProperty(i);
                advertisement=new Advertisement();
                advertisement.setAdvId(Integer.parseInt(responsePr.getProperty("AdvId").toString()));
                advertisement.setAdvTitle(responsePr.getProperty("AdvTitle").toString());

               /* byte[] imageByte = cursorTemp.getBlob(ImageIndex);
                Bitmap imageAdv = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                newAdv.setAdvImage(imageAdv);
                /*advertisement.setAdvImage();*/
                /*advertisement.setAdvImage(convertStringToBitmap(responsePr.getProperty("AdvImage").toString()));*/
                advertisement.setPrice(Integer.parseInt(responsePr.getProperty("Price").toString()));
                advertisement.setAdvStatus(responsePr.getProperty("AdvStatus").toString());
                advertisement.setRoomNum(responsePr.getProperty("RoomNum").toString());

               advertisement.setSquareMeters(Integer.parseInt(responsePr.getProperty("SquareMeter").toString()));
                advertisement.setBuildingFloors(Integer.parseInt(responsePr.getProperty("BuildingFloors").toString()));
                advertisement.setFloorLoc(Integer.parseInt(responsePr.getProperty("FloorLoc").toString()));
                advertisement.setBuildAge(Integer.parseInt(responsePr.getProperty("BuildAge").toString()));
               advertisement.setBuildType(responsePr.getProperty("BuildType").toString());
                advertisement.setItemStatus(responsePr.getProperty("ItemStatus").toString());
                advertisement.setWarmType(responsePr.getProperty("WarmType").toString());
                advertisement.setNumOfBathr(Integer.parseInt(responsePr.getProperty("NumOfBathrooms").toString()));
                advertisement.setElgForCredit(responsePr.getProperty("ElgCredit").toString());
                advertisement.setUsingStatus(responsePr.getProperty("UsingStatus").toString());
                advertisement.setStateBuilding(responsePr.getProperty("StateBuilding").toString());
                advertisement.setRentalIncome(Integer.parseInt(responsePr.getProperty("RentalIncome").toString()));
                advertisement.setDues(Integer.parseInt(responsePr.getProperty("Dues").toString()));
                advertisement.setSwap(responsePr.getProperty("Swap").toString());
                advertisement.setFront(responsePr.getProperty("Front").toString());

                advertisement.setFuelType(responsePr.getProperty("FuelType").toString());
                advertisement.setDate(responsePr.getProperty("Date").toString());

                advertisement.setAddress(responsePr.getProperty("Address").toString());


               advertisement.setCity(responsePr.getProperty("Cities").toString());

                advertisement.setTown(responsePr.getProperty("Town").toString());
                /* advertisement.setLatitude(Double.parseDouble(responsePr.getProperty("XCoordinate").toString()));
                advertisement.setLongitude(Double.parseDouble(responsePr.getProperty("YCoordinate").toString()));*/
                advertisementList.add(advertisement);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println("asdfgh"+advertisementList);
        return advertisementList;
    }
}
