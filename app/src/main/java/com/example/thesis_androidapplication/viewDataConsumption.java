package com.example.thesis_androidapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class viewDataConsumption extends AppCompatActivity {
    /*instantiate recyclerview*/
    RecyclerView data_recyclerview;

    /*instantiate the database helper object*/
    MyDatabaseHelper myDB;

    /*create an arraylist to hold the values from the database*/
    ArrayList<String> userName, energyConsumed, billToPay, readingDate, costOfEnergy;

    /*instantiate the customAdapter for the history feature*/
    CustomAdapterForConsumptionHistory another_customAdapter;

   /*global instantiation of date variable*/
   private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_consumption);

        /*Assign the recycler view id to the object*/
        data_recyclerview = findViewById(R.id.consumptionRecyclerView);

        /*get the bundle from the intent from last activity*/
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("date");


        //initialize objects and arraylist
        //for getting data from database
        myDB = new MyDatabaseHelper(viewDataConsumption.this);
        userName = new ArrayList<>();
        energyConsumed = new ArrayList<>();
        billToPay = new ArrayList<>();
        readingDate = new ArrayList<>();
        costOfEnergy = new ArrayList<>();

        /*call the method to add the values to the arrays*/
        recoverAndAddInArrays(date);

        /*the recycler view object to retrieve from database*/
        another_customAdapter = new CustomAdapterForConsumptionHistory(viewDataConsumption.this, viewDataConsumption.this, userName
                ,energyConsumed, billToPay,readingDate,costOfEnergy);
        data_recyclerview.setAdapter(another_customAdapter);
        data_recyclerview.setLayoutManager(new LinearLayoutManager(viewDataConsumption.this));
        /*recyclerview codes ends here*/

    }

    /*method used to get the values from the data history database into the arrays*/
    void recoverAndAddInArrays(String date) {
        Cursor cursor = myDB.getEnergyData(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Retrieved", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                readingDate.add(cursor.getString(0));
                userName.add(cursor.getString(1));
                energyConsumed.add(cursor.getString(2));
                costOfEnergy.add(cursor.getString(3));
                billToPay.add(cursor.getString(4));

            }
        }
    }

    /*override method for the button on the actionbar of the activity*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete_data_history, menu);
        return true;
    }

    /*this section of the code handles the function for the button on the actionbar
    * the DELETE button*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_button:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete Data History:");
                builder.setMessage("Are you sure you want to delete this data history?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*code to delete the data from the database*/
                        myDB.deleteEnergyData_withDate(date);
                        /*set the request code*/
                       setResult(1);
                       setResult(1,getIntent());
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*does nothing*/

                    }
                });

                //show confirm dialog
                builder.create().show();

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    /*end of override method for the action bar*/
}