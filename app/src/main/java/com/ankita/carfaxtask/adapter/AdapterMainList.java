package com.ankita.carfaxtask.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.toolbox.Volley;
import com.ankita.carfaxtask.Description;
import com.ankita.carfaxtask.R;
import com.ankita.carfaxtask.interfaces.AdapterToClick;
import com.ankita.carfaxtask.model.MainScreen;
import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterMainList extends RecyclerView.Adapter<AdapterMainList.MyViewHolder> {

    List<MainScreen> listData = new ArrayList<>();
    private Context context;
    AdapterToClick click;
    private String id2;

    public AdapterMainList(List<MainScreen> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_main_screen,parent,
                false);
        return new AdapterMainList.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final int pos=position;
        final MainScreen dataProvider=listData.get(position);
        holder.year.setText(dataProvider.getYear());
        holder.make.setText(dataProvider.getMake());
        holder.model.setText(dataProvider.getModel());
        holder.trim.setText(dataProvider.getTrim());
        holder.price.setText(dataProvider.getPrice());
        holder.mileage.setText(dataProvider.getMilage());
        id2 = dataProvider.getId();

        holder.location.setText(dataProvider.getLocation());

        String uu = "https://firebasestorage.googleapis.com/v0/b/carfax-for-consumers.appspot.com/o/640x480%2Fabarth-fiat-124-spider-fiat-620.jpeg?alt=media&token=c949768f-ce24-42b5-9f01-324d6667d989";
        String xyz  = dataProvider.getvPhoto().toString();
        Log.d("xxx : ",xyz);
        Glide.with(context).load(xyz).centerCrop().into(holder.imgcar);
        

        holder.calldealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //    click.clicked(v.getBottom());

                Intent intent = new Intent(context, Description.class);
//Log.d("id",id2);
                intent.putExtra("id",id2);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView year,make,model,trim,price,location,mileage;
        ImageView imgcar;
        
        Button calldealer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            year = (TextView)itemView.findViewById(R.id.year);
            make = (TextView)itemView.findViewById(R.id.make);
            model = (TextView)itemView.findViewById(R.id.model);
            trim = (TextView)itemView.findViewById(R.id.trim);
            price = (TextView)itemView.findViewById(R.id.price);
            location = (TextView)itemView.findViewById(R.id.location);
            imgcar = (ImageView) itemView.findViewById(R.id.imgcar);
            mileage = (TextView)itemView.findViewById(R.id.mileage);
            calldealer = (Button)itemView.findViewById(R.id.calldealer);


        }


    }
}