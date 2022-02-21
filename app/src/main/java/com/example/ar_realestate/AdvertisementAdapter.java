package com.example.ar_realestate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.myviewholder>{

    ArrayList<Advertisement> adv;

    public AdvertisementAdapter(ArrayList<Advertisement> adv) {
        this.adv = adv;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisement_card,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.img.setImageResource(adv.get(position).getAdv_image());
        holder.titlee.setText(adv.get(position).getAdvTitle());
        holder.addresss.setText(adv.get(position).getAddress());
        holder.pricee.setText(adv.get(position).getPric());
    }

    @Override
    public int getItemCount() {
        return adv.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titlee, addresss, pricee;

        public myviewholder (@NonNull View itemView){
            super(itemView);

            img=itemView.findViewById(R.id.image);
            titlee=itemView.findViewById(R.id.title);
            addresss=itemView.findViewById(R.id.address);
            pricee=itemView.findViewById(R.id.price);
        }
    }
}
