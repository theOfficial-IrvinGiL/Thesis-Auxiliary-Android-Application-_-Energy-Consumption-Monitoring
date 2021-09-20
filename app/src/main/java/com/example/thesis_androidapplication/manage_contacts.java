package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class manage_contacts extends AppCompatActivity {
    RecyclerView recyclerview;
    FloatingActionButton toAddcontacts;

    MyDatabaseHelper myDB;
    ArrayList<String> contact_id, contact_userName, contact_userContact;

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
                startActivity(thisIntent);
            }
         });

        //initialize objects and arraylist
        //for getting data from database
        myDB = new MyDatabaseHelper(manage_contacts.this);
        contact_id = new ArrayList<>();
        contact_userName = new ArrayList<>();
        contact_userContact = new ArrayList<>();



        storeDataInArrays();

        //the recycler view object to retrieve from database
        customAdapter = new CustomAdapterForContact(manage_contacts.this, contact_id, contact_userName, contact_userContact);
        recyclerview.setAdapter(customAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(manage_contacts.this));


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

            }

        }

    }




}