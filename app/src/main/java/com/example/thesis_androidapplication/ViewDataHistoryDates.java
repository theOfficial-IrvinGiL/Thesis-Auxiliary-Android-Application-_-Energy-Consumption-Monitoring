package com.example.thesis_androidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewDataHistoryDates extends AppCompatActivity {

    /*instantiate the activity*/
    Activity activity = ViewDataHistoryDates.this;


    /*initializing database helper*/
    MyDatabaseHelper myDB = new MyDatabaseHelper(this);

    /*instantiating the list*/
    List<String> list =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_history_dates);

        /*instantiate and assign id to listview */
        ListView listView = findViewById(R.id.list_of_dates);

        /*calls the load method to get the dates from the database*/
        loadDates();

        /*instantiate array adapter and put the data from the list array into the arrayAdapter*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                /*code for intent*/
                Intent thisIntent = new Intent(ViewDataHistoryDates.this, viewDataConsumption.class);

                Bundle bundle =new Bundle();
                bundle.putString("date", list.get(position));


                thisIntent.putExtras(bundle);
                /*the reason why the activity is instantiated is so that we can make use of the startActivityforResult method*/
                activity.startActivityForResult(thisIntent,1);
            }
        });



    }

    /*override method to recreate when a child activity is finished*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
           recreate();
        }
    }

    /*method to load the date on the listview*/
    protected void loadDates(){
        //initialize cursor to use in loading the data from the sql lite database
        Cursor cursor = myDB.getHistoryDates();
        if(cursor.getCount() == 0){  //if the cursor is empty, display toast message
            Toast.makeText(this, "No dates retrieved", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){ //if cursor has data then add to the array to be displayed
                list.add(cursor.getString(0));
            }
        }
    }
}