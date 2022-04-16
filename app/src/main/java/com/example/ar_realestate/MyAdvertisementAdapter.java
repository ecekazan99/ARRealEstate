package com.example.ar_realestate;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 29/09/16.
 */

public class MyAdvertisementAdapter extends RecyclerView.Adapter<MyAdvertisementAdapter.ViewHolder> {
public static Boolean clickAdvUpdate=false;
    ArrayList<Advertisement> adv;
    private Context context;
    private OnItemClickListener listener;

    public static Bitmap selectedİmg,smallestedImg;

    public static String advTitle, advStatus, roomNum, warmType, elgForCredit, usingStatus, buildType, itemStatus, stateBuilding, swap, front, fuelType, date, address,city,town;
    public static int advId,price, squareMeters, buildingFloors, floorLoc, buildAge, numOfBathr, rentalIncome, dues;

    public static double latitude,longitude;

    public MyAdvertisementAdapter(ArrayList<Advertisement> adv, Context context) {
        this.adv = adv; this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_adv_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdvertisementAdapter.ViewHolder holder, int position) {
        holder.img.setImageBitmap(adv.get(position).getAdvImage());
        holder.titlee.setText(adv.get(position).getAdvTitle());
        holder.addresss.setText(adv.get(position).getAddress());
        holder.pricee.setText(String.valueOf(adv.get(position).getPrice()));
       int pos=position;
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                popup.inflate(R.menu.option_myadv);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.adv_update){
                            clickAdvUpdate=true;
                            advId=adv.get(pos).getAdvId();
                            advTitle=adv.get(pos).getAdvTitle();
                            advStatus=adv.get(pos).getAdvStatus();
                            roomNum=adv.get(pos).getRoomNum();
                            warmType=adv.get(pos).getWarmType();
                            elgForCredit=adv.get(pos).getElgForCredit();
                            usingStatus=adv.get(pos).getUsingStatus();
                            buildType=adv.get(pos).getBuildType();
                            itemStatus=adv.get(pos).getItemStatus();
                            stateBuilding=adv.get(pos).getStateBuilding();
                            swap=adv.get(pos).getSwap();
                            front=adv.get(pos).getFront();
                            fuelType=adv.get(pos).getFuelType();
                            date=adv.get(pos).getDate();
                            address=adv.get(pos).getAddress();
                            city=adv.get(pos).getCity();
                            town=adv.get(pos).getTown();
                            price=adv.get(pos).getPrice();
                            squareMeters=adv.get(pos).getSquareMeters();
                            buildingFloors=adv.get(pos).getBuildingFloors();
                            floorLoc=adv.get(pos).getFloorLoc();
                            buildAge=adv.get(pos).getBuildAge();
                            numOfBathr=adv.get(pos).getNumOfBathr();
                            rentalIncome=adv.get(pos).getRentalIncome();
                            dues=adv.get(pos).getDues();
                            latitude=adv.get(pos).getLatitude();
                            longitude=adv.get(pos).getLongitude();
                            selectedİmg=adv.get(pos).getAdvImage();
                            replaceFragment(new MyAdvUpdateFragment());
                        }
                        else if(menuItem.getItemId()==R.id.delete_adv){
                            int deleteAdvId=adv.get(pos).getAdvId();
                           String sqlQuery ="DELETE FROM Advertisements WHERE AdvId = '"+deleteAdvId+"'";
                            MainActivity.db.execSQL(sqlQuery);
                             sqlQuery="DELETE FROM UserAdvertisement WHERE AdvId = '"+deleteAdvId+"'";
                            MainActivity.db.execSQL(sqlQuery);
                            replaceFragment(new MyAdvertisementFragment());
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView titlee, addresss, pricee;
        public TextView buttonViewOption;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.image);
            titlee=itemView.findViewById(R.id.title);
            addresss=itemView.findViewById(R.id.address);
            pricee=itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if(listener!=null && position!=RecyclerView.NO_POSITION ){
                        listener.onItemClick(adv.get(position));
                    }
                }
            });
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Advertisement adv);
    }
    public void setOnItemClickListener(MyAdvertisementAdapter.OnItemClickListener listener){
        this.listener=listener;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }
}
