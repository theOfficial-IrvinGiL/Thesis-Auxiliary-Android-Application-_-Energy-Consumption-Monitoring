package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContacts extends AppCompatActivity {

    EditText nes_contactName, new_contactNumber;
    Button update_button, delete_button;

//    String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);
    }
}