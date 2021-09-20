package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_contacts extends AppCompatActivity {

    EditText new_contact_name, new_contact_number;

    Button TosaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        //initialize get id
        new_contact_name = findViewById(R.id.new_contact_name);
        new_contact_number = findViewById(R.id.new_contact_number2);

        TosaveButton =  findViewById(R.id.saveButton);



        TosaveButton.setOnClickListener(new View.OnClickListener() { //save button listener
            @Override
            public void onClick(View view) {
               MyDatabaseHelper myDB = new MyDatabaseHelper(add_contacts.this);
               myDB.add_thiscontact(new_contact_name.getText().toString().trim(),
                       Integer.valueOf(new_contact_number.getText().toString().trim()));
//               Intent intent = new Intent(add_contacts.this, manage_contacts.class);
//               intent.putExtra()
            }
        });




    }
}