package com.example.ar_realestate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.myviewholder>{

    ArrayList<Advertisement> adv;
    private Context context;
    private OnItemClickListener listener;

    public AdvertisementAdapter(ArrayList<Advertisement> adv, Context context) {
        this.adv = adv; this.context = context;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisement_card,parent,false);
        return new myviewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
       /* holder.img.setImageBitmap(adv.get(position).getAdvImage());*/
        holder.titlee.setText(adv.get(position).getAdvTitle());
        holder.addresss.setText(adv.get(position).getAddress());
        holder.pricee.setText(String.valueOf(adv.get(position).getPrice()));
    }
    @Override
    public int getItemCount() {
        return adv.size();
    }
    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titlee, addresss, pricee;
        LinearLayout clickDetail;
        public myviewholder (@NonNull View itemView){
            super(itemView);
           /* img=itemView.findViewById(R.id.image);*/
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
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Advertisement adv);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


}
