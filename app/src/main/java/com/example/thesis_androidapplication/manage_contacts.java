package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class manage_contacts extends AppCompatActivity {
RecyclerView recyclerview;
    FloatingActionButton toAddcontacts;



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

    }
}