package com.example.thesis_androidapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class readingKWH_sendingSMS extends AppCompatActivity {

    /*database helper class*/
    MyDatabaseHelper myDB = new MyDatabaseHelper(this);


    /* initialized to be used for displaying the cost calculated from the last activity*/
    protected TextView displayCost;

   TextInputLayout display_name, display_contact;


    /* used for getting the date from the Intent on last activity*/
    protected String reading_date , energy_cost;

    List <String> list =new ArrayList<>();

    /*holder variable for the chosen values to send sms*/
    private String chosen_name, chosen_contact;

    /*button object initialized*/
    Button calculate_and_send, clear_contents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_kwh_sending_sms);

        /*assigning the id of textview*/
        displayCost = findViewById(R.id.display_cost_textview);
        display_name = findViewById(R.id.view_name);
        display_contact = findViewById(R.id.view_contact);
        calculate_and_send = findViewById(R.id.calcAndSend_SMS);

        /*buttons*/
        clear_contents = findViewById(R.id.clear_contents);
        calculate_and_send = findViewById(R.id.calcAndSend_SMS);

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
                /*get the index position number of the chosen item in the listview*/
                chosen_name = list.get(position);
                display_name.getEditText().setText(chosen_name); //display the name in the textview

                /*display toast message*/
                Toast.makeText(readingKWH_sendingSMS.this, "Selected: "+chosen_name, Toast.LENGTH_SHORT).show();

                /*code to retrieve the corresponding contact number of the
                * chosen name in the database*/
                Cursor cursor = myDB.loadUserContactNumber(chosen_name);
                if(cursor.getCount() == 0){  //if the cursor is empty, display toast message
                    Toast.makeText(getApplicationContext(), "No contact names retrieved", Toast.LENGTH_SHORT).show();
                }else{
                    while(cursor.moveToNext()){ //if cursor has data then add to the variable to be displayed
                        chosen_contact = cursor.getString(0);
                    }
                }

                /*display the contact number on the editText*/
                display_contact.getEditText().setText(chosen_contact);
            }
        });
        /*listview code ends here*/


        /*button onclickListener*/
        calculate_and_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        /*listener to clear contents on the edittext view*/
        clear_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_name.getEditText().setText("");
                display_contact.getEditText().setText("");
            }
        });




    }


    /*code for the action toolbar*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "selected home", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    /*end of code for action toolbar*/

    /*method for the function on loading the contact names form the database
    * into the listview of the activity*/
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

    /*method for the back key press and prevent jumbling of activity*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have pressed the back button! You are currently in the " +
                "middle of *Updating the Energy Consumption* process. If want to go to the home activity, press the home icon above.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //show confirm dialog
        builder.create().show();

        return;
    }






}