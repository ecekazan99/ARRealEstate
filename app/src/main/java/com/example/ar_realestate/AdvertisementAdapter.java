package com.example.ar_realestate;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.pricee.setText(String.valueOf(adv.get(position).getAdvId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(view.getContext(), "sill",Toast.LENGTH_SHORT).show();

             //   Intent advDetail=new Intent(view.getContext(), Add_AdvActivity.class);
              //  view.getContext().startActivity(advDetail);
            }
        });
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

            img=itemView.findViewById(R.id.image);
            titlee=itemView.findViewById(R.id.title);
            addresss=itemView.findViewById(R.id.address);
            pricee=itemView.findViewById(R.id.price);

            clickDetail=itemView.findViewById(R.id.clickDetail);
        }
    }


}
