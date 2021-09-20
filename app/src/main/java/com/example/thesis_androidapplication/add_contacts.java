package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;

public class add_contacts extends AppCompatActivity {

    EditText new_contact_name, new_contact_number;

    Button TosaveButton, TocancelButton;

//    View snackview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        //initialize get id
        new_contact_name = findViewById(R.id.new_contact_name);
        new_contact_number = findViewById(R.id.new_contact_number);

        TosaveButton =  findViewById(R.id.saveButton);
        TocancelButton = findViewById(R.id.thiscancelbutton);

//        snackview= findViewById(android.R.id.content);  //for snackbar use

        TosaveButton.setOnClickListener(new View.OnClickListener() { //save button listener
            @Override
            public void onClick(View view) {
               MyDatabaseHelper myDB = new MyDatabaseHelper(add_contacts.this);
               myDB.add_thiscontact(new_contact_name.getText().toString().trim(),
                       Integer.valueOf(new_contact_number.getText().toString().trim()));
            }
        });

        TocancelButton.setOnClickListener(new View.OnClickListener() {  //cancel button listener
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(add_contacts.this, manage_contacts.class);
                startActivity(i);
            }
        });



    }
}