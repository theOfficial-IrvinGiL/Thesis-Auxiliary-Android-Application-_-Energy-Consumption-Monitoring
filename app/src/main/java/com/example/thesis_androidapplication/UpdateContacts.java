package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContacts extends AppCompatActivity {

    EditText new_contactName, new_contactNumber;
    Button update_button, delete_button;

    String id, userName, contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);

        new_contactName = findViewById(R.id.new_contact_name2);
        new_contactNumber = findViewById(R.id.new_contact_number2);

        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.delete_button);

        //call the getAndSEt Method
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName =  new_contactName.getText().toString().trim();
                contactNumber = new_contactNumber.getText().toString().trim();

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateContacts.this);
                myDB.updateData(id, userName, contactNumber);
                finish();

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("contact_id") &&
                getIntent().hasExtra("contact_name") &&
                getIntent().hasExtra("contact_number")){

            id = getIntent().getStringExtra("contact_id");
            userName = getIntent().getStringExtra("contact_name");
            contactNumber = getIntent().getStringExtra("contact_number");

            //setting intent data

            new_contactName.setText(userName);
            new_contactNumber.setText(contactNumber);
        }else{
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }


    }
}