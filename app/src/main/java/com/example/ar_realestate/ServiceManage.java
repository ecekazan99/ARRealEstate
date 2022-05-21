package com.example.ar_realestate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class ServiceManage {

    private static final String NAMESPACE="http://tempuri.org";
    private static final String METHOD_NAME_GETPRODUCT="getAdvertisements";
    private static final String SOAP_ACTION_GETPRODUCT="http://tempuri.org/getAdvertisements";
    private static final String URL="http://193.140.150.95/deuarSrv/WebService1.asmx";

    private SoapObject soapObject;
    private SoapSerializationEnvelope soapSerializationEnvelope;
    private HttpTransportSE httpTransportSE;
    public Advertisement advertisement;
    public List<Advertisement>advertisementList;

    public static Bitmap convertStringToBitmap(String string) {
        byte[] byteArray1;
        byteArray1 = Base64.decode(string, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                byteArray1.length);
        return bmp;
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
            httpTransportSE.call(SOAP_ACTION_GETPRODUCT,soapSerializationEnvelope);
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
        System.out.println(advertisementList);
        return advertisementList;
    }
}