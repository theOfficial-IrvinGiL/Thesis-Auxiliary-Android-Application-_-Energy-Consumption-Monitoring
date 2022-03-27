package com.example.thesis_androidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class manage_contacts extends AppCompatActivity {
    /*instantiate the activity context*/
    Activity activity = manage_contacts.this;

    RecyclerView recyclerview;
    FloatingActionButton toAddcontacts;

    MyDatabaseHelper myDB;
    ArrayList<String> contact_id, contact_userName, contact_userContact, contact_passcode;

    //initialize recycler view object
    CustomAdapterForContact customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contacts);

        toAddcontacts = findViewById(R.id.addContactsFAbutton);
        recyclerview = findViewById(R.id.recyclerView);



        toAddcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisIntent =new Intent(manage_contacts.this, add_contacts.class);
                activity.startActivityForResult(thisIntent,1);
            }
         });

        //initialize objects and arraylist
        //for getting data from database
        myDB = new MyDatabaseHelper(manage_contacts.this);
        contact_id = new ArrayList<>();
        contact_userName = new ArrayList<>();
        contact_userContact = new ArrayList<>();
        contact_passcode = new ArrayList<>();

        storeDataInArrays();

        //the recycler view object to retrieve from database
        customAdapter = new CustomAdapterForContact(manage_contacts.this,manage_contacts.this, contact_id, contact_userName, contact_userContact,contact_passcode);
        recyclerview.setAdapter(customAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(manage_contacts.this));


    }

    //add overide method to reset list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode >= 1 ){recreate();}
        /*code to determine what message to be displayed when the child activity finished() is executed*/



    }



    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data Retrieved", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_userName.add(cursor.getString(1));
                contact_userContact.add(cursor.getString(2));
                contact_passcode.add(cursor.getString(3));

            }

        }

    }




}