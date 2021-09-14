package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class add_contacts extends AppCompatActivity {

    private Button TosaveButton, TocancelButton;

    View snackview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        TosaveButton =  findViewById(R.id.saveButton);
        TocancelButton = findViewById(R.id.thiscancelbutton);

        snackview= findViewById(android.R.id.content);  //for snackbar use

        TosaveButton.setOnClickListener(new View.OnClickListener() { //save button listener
            @Override
            public void onClick(View view) {
                Snackbar.make(snackview, "New contact has been saved!", Snackbar.LENGTH_SHORT).show();
//                Intent i =  new Intent(add_contacts.this, manage_contacts.class);
//                startActivity(i);
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