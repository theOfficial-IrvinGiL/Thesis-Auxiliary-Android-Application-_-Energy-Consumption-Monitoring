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

public class update_energy extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    /* used for the datePickerDialog*/
    private EditText editTextDate;
    private DatePickerDialog datePickerDialog;

    private TextView totalKWH, totalBill;
    private Button proceedButton;

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
                    Snackbar.make(findViewById(android.R.id.content),"Proceed to next activity: To be developed!",Snackbar.LENGTH_SHORT).show();

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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

    /* override methods for the toolbar option */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_option_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            /* to restart the process at the beginning page; in this case here ⏬*/
            case R.id.restart_process:
                Intent thisIntent = new Intent(this, update_energy.class);
                startActivity(thisIntent);
                break;
            /* redirect to the second_activity to cancel the process of updating the energy consumption*/
            case R.id.cancel_process:
                thisIntent = new Intent(this, second.class);
                startActivity(thisIntent);
                break;
            default: // nothing happens
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    /* end of override methods for the use of the toolbar option */

    /*method for error display in case of empty input*/
    protected void displayError_forBlankItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning: Blank Input");
        builder.setMessage("It seems that you have a blank input. Kindly review and fill up the inputs required.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}