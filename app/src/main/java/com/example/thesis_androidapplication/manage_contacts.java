package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class manage_contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contacts);


        //code for floating action button
        /*
        * color of the floating action button can be modified on the colors.xml
        * */



        FloatingActionButton toAddcontacts = findViewById(R.id.addContactsFAbutton);
        toAddcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisIntent =new Intent(manage_contacts.this, add_contacts.class);
                startActivity(thisIntent);
            }
         });

    }
}