package com.example.thesis_androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.text.DecimalFormat;

public class update_energy extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    /* used for the datePickerDialog*/
    private EditText editTextDate;
    private DatePickerDialog datePickerDialog;

    private TextView totalKWH, totalBill;
    private Button proceedButton;

    /*data type declarations*/
    private double energyCost;

    /*for number value format cause*/
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    /*declaration for getting the date value*/
    EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_energy);

        proceedButton =findViewById(R.id.proceed_button);

        totalKWH = findViewById(R.id.input_totalKWH_used);
        totalBill = findViewById(R.id.input_totalBill_amount);

        /*for the calendar. To use for the date picker functionality*/
        editTextDate =findViewById(R.id.date_picker_dialog);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(update_energy.this, this, year, month, day);
        datePickerDialog.setCancelable(true);

        /*proceed-button onclicklistener*/
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalKWH.getText().toString().isEmpty()){
                    /*display error msg method*/
                    displayError_forBlankItem();
                }
                else if(totalBill.getText().toString().isEmpty()){
                    displayError_forBlankItem();
                }else if(editTextDate.getText().toString().isEmpty()){
                    displayError_forBlankItem();
                }else{  /*proceed to next activity if all textview inputs are complete*/
                    try {
                        double bill = Double.parseDouble(totalBill.getText().toString());
                        double energyConsumed = Double.parseDouble(totalKWH.getText().toString());

                        dateText = findViewById(R.id.date_picker_dialog);

                        /*pass to calculate method*/
                        energyCost = calculateEnergyCost(bill, energyConsumed);
                        Snackbar.make(findViewById(android.R.id.content),"Cost calculated Successfully = " + df2.format(energyCost),Snackbar.LENGTH_SHORT).show();
                        Intent thisIntent = new Intent(getApplicationContext(), readingKWH_sendingSMS.class);

                        String cost = df2.format(energyCost), date = dateText.getText().toString().trim();

                        /*set the intent information*/
                        thisIntent.putExtra("energy_cost",cost);
                        thisIntent.putExtra("reading_date", date);



                        /*start the next activity*/
                        startActivity(thisIntent);

                    } catch (Exception e){
                        Snackbar.make(findViewById(android.R.id.content),"Error encountered during calculation process!",Snackbar.LENGTH_SHORT).show();
                    }


                }
            }
        });

        /*date picker onclick listener*/
        editTextDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        /* code for the datepickerDialog ends here*/



    }

    /* method used along with the datepickerdialog function*/
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month+=1;
        String date = dayOfMonth + "/" + month + "/" + year;
        editTextDate.setText(date);
    }

    /* override method for when pressing the back button inside the update the energy consumption
    * process*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel update consumption");
        builder.setMessage("You have pressed the back button! Doing so cancels this process. What do you want to do? ");
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate(); //recreate the whole activity
            }
        });
        builder.setNegativeButton("Cancel process", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Intent thisIntent = new Intent(update_energy.this, second.class);
               startActivity(thisIntent);
                //shuts the activity down and revert to the second activity
            }
        });
        //show confirm dialog
        builder.create().show();

        return;
    }



    /*method for error display in case of empty input*/
    protected void displayError_forBlankItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning: Blank Input");
        builder.setMessage("It seems that you have a blank input. Kindly review and fill up the inputs required.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    /* method for calculating the rate of energy consumption*/
    private double calculateEnergyCost(double total_bill, double energy_consumed){
        double energyCost = total_bill / energy_consumed;
        return energyCost;
    }
}