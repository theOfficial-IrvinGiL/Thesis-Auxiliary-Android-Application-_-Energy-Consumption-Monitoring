package com.example.thesis_androidapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterForContact extends RecyclerView.Adapter<CustomAdapterForContact.MyViewHolder> {

    private Context context;

    //add activity object
    Activity activity;

    private ArrayList contact_id, contact_userName, contact_userContact, contact_passcode;

    //constructor
    CustomAdapterForContact(Activity activity, Context context, ArrayList contact_id,
                            ArrayList contact_userName, ArrayList contact_userContact, ArrayList contact_passcode){
        this.activity = activity;
        this.context = context;

        this.contact_id = contact_id;
        this.contact_userName = contact_userName;
        this.contact_userContact = contact_userContact;
        this.contact_passcode = contact_passcode;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.manage_contacts_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterForContact.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set the text
        holder.display_userid_text.setText(String.valueOf(contact_id.get(position)));
        holder.display_username_txt.setText(String.valueOf(contact_userName.get(position)));
        holder.display_usercontact_txt.setText(String.valueOf(contact_userContact.get(position)));

        holder.contactRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass the data from the recycler view into intent to the next (update) activity
                Intent intent = new Intent(context, UpdateContacts.class);
                intent.putExtra("contact_id", String.valueOf(contact_id.get(position)));
                intent.putExtra("contact_name", String.valueOf(contact_userName.get(position)));
                intent.putExtra("contact_number", String.valueOf(contact_userContact.get(position)));
                intent.putExtra("contact_passcode", String.valueOf(contact_passcode.get(position)));



                activity.startActivityForResult(intent, 1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return contact_userContact.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView display_userid_text, display_username_txt, display_usercontact_txt;

        //linear layout
        LinearLayout contactRowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            display_userid_text = itemView.findViewById(R.id.display_user_id);
            display_username_txt =itemView.findViewById(R.id.display_username);
            display_usercontact_txt = itemView.findViewById(R.id.display_usercontact);

            contactRowLayout = itemView.findViewById(R.id.contactRowLayout);

        }
    }
}
