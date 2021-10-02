package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class readingKWH_sendingSMS extends AppCompatActivity {

    /* initialized to be used for displaying the cost calculated from the last activity*/
    protected TextView displayCost;

    protected String reading_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_kwh_sending_sms);

        Intent intent = getIntent();
        reading_date = intent.getStringExtra("reading_date");

        displayCost = findViewById(R.id.display_cost_textview);
        displayCost.setText(intent.getStringExtra("energy_cost"));

//        reading_date.equals(getIntent().getExtras().getString("reading_date"));
//




    }
}