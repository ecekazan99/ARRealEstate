package com.example.ar_realestate;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.AdvHolder> {
    private ArrayList<Advertisement> advList;
    private Context context;
    private OnItemClickListener listener;

    public AdvertisementAdapter(ArrayList<Advertisement> advList,Context context) {
        this.advList = advList;
        this.context = context;
    }


    @NonNull
    @Override
    public AdvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adv_item,parent,false);

        return new AdvHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvHolder holder, int position) {
        Advertisement advertisement=advList.get(position);
        holder.setData(advertisement);

    }

    @Override
    public int getItemCount() {
        return advList.size();
    }

    class AdvHolder extends RecyclerView.ViewHolder {
        TextView txtAdvTitle,txtAdvPrice;
        ImageView imgAdv;


        public AdvHolder(@NonNull View itemView) {
            super(itemView);

            txtAdvTitle=(TextView)itemView.findViewById(R.id.adv_item_textViewAdvTitle);
            txtAdvPrice=(TextView)itemView.findViewById(R.id.adv_item_textViewAdvPrice);
            imgAdv=(ImageView)itemView.findViewById(R.id.advItem_imageViewAdv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if(listener!=null && position!=RecyclerView.NO_POSITION ){
                        listener.onItemClick(advList.get(position));
                    }
                }
            });


        }
        public void setData(Advertisement advertisement){
            this.txtAdvTitle.setText(advertisement.getAdvTitle());
            this.txtAdvPrice.setText(advertisement.getPrice());
            this.imgAdv.setImageBitmap(advertisement.getAdv_image());


        }
    }

    public interface OnItemClickListener{
        void onItemClick(Advertisement adv);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


}
