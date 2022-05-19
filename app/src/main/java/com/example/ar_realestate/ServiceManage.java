package com.example.ar_realestate;

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
                advertisement.setPrice(Integer.parseInt(responsePr.getProperty("Price").toString()));
                advertisementList.add(advertisement);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println(advertisementList);
        return advertisementList;
    }
}
