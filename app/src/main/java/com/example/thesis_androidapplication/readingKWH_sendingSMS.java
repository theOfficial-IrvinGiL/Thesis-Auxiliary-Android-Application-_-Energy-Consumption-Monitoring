package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class readingKWH_sendingSMS extends AppCompatActivity {

    /*database helper class*/
    MyDatabaseHelper myDB = new MyDatabaseHelper(this);


    /* initialized to be used for displaying the cost calculated from the last activity*/
    protected TextView displayCost;

    /* used for getting the date from the Intent on last activity*/
    protected String reading_date , energy_cost;

    List <String> list =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_kwh_sending_sms);

        /*assigning the id of textview*/
        displayCost = findViewById(R.id.display_cost_textview);

        /*initializing bundle*/
        Bundle bundle = getIntent().getExtras();

        /*getting the intent values from last activity*/
        reading_date = bundle.getString("reading_date");
        energy_cost = bundle.getString("energy_cost");

        /*display the cost values on the textview*/
        displayCost.setText(energy_cost);

        /*listview code*/
        ListView listView = findViewById(R.id.listView);
        /*arraylist declaration*/


        /*loop for putting the cursor value into the arraylist*/
        loadContact_Names();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected: "+position, Toast.LENGTH_SHORT).show();
            }
        });


        /*listview code ends here*/












    }

    private void loadContact_Names(){
        //initialize cursor to use in loading the data from the sql lite database
        Cursor cursor = myDB.loadContactNames_forEnergyUpdate();
        if(cursor.getCount() == 0){  //if the cursor is empty, display toast message
            Toast.makeText(this, "No contact names retrieved", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){ //if cursor has data then add to the array to be diplayed
                list.add(cursor.getString(0));
            }
        }

    }
}