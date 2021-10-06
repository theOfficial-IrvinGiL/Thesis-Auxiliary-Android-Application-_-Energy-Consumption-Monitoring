package com.example.thesis_androidapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterForConsumptionHistory extends RecyclerView.Adapter<CustomAdapterForConsumptionHistory.MyViewHolder> {

    private Context context;

    //add activity object
    Activity activity;


    private ArrayList userName, energyConsumed, billToPay, readingDate,costOfEnergy;
    //constructor

    /*userName
                ,energyConsumed, billToPay,readingDate,costOfEnergy*/

    CustomAdapterForConsumptionHistory(Activity activity, Context context, ArrayList userName, ArrayList energyConsumed, ArrayList billToPay, ArrayList readingDate, ArrayList costOfEnergy){
        this.activity = activity;
        this.context = context;

        this.userName = userName;
        this.energyConsumed = energyConsumed;
        this.billToPay = billToPay;
        this.readingDate = readingDate;
        this.costOfEnergy = costOfEnergy;

    }








    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*instantiate layout inflater*/
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.energy_consumption_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterForConsumptionHistory.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*assign the holder to get the values and from the arraylist*/

//        holder.display_userid_text.setText(String.valueOf(contact_id.get(position)));
        holder.username.setText(String.valueOf(userName.get(position)));
        holder.energy_Consumed.setText(String.valueOf(energyConsumed.get(position)));
        holder.reading_Date.setText(String.valueOf(readingDate.get(position)));
        holder.bill_toPay.setText(String.valueOf(billToPay.get(position)));
        holder.energy_Cost.setText(String.valueOf(costOfEnergy.get(position)));


    }

    @Override
    public int getItemCount() {
        return readingDate.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        /*instantiate textview objects*/
       TextView username, energy_Consumed, reading_Date, bill_toPay, energy_Cost;

        LinearLayout energyLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            /*assigning the view id to the objects*/
            username = itemView.findViewById(R.id.data_username);
            energy_Consumed = itemView.findViewById(R.id.data_energy_consumed);
            reading_Date = itemView.findViewById(R.id.data_date_of_reading);
            bill_toPay = itemView.findViewById(R.id.data_rate_to_pay);
            energy_Cost = itemView.findViewById(R.id.data_cost_of_energy);

            /*assigning object layout*/
            energyLayout = itemView.findViewById(R.id.energyLayout);







        }
    }



}
