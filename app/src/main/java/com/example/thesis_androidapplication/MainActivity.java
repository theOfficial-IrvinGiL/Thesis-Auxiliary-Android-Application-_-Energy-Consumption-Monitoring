package com.example.thesis_androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*instantiate constructor for database helper*/
    MyDatabaseHelper myDB = new MyDatabaseHelper(this);

    EditText input;




  /*  uses the onclick listener and switch conditional statement to decide what actions to make
    according to what button id is clicked */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manageEnergy_Button:
                Intent i =new Intent(this, second.class); //initializing the intent
                startActivity(i);  //start the next activity

                break;
            case R.id.viewDataHistory_Button:
                i =new Intent(this, ViewDataHistoryDates.class);
                startActivity(i);

                break;
            case R.id.instructionManual_Button:
                Toast.makeText(this,"You have selected instructions manual",Toast.LENGTH_SHORT).show();

                break;
            default:


                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *       Cast and use onclicklistener instead of onclick method
         * */

        ((Button) findViewById(R.id.manageEnergy_Button)).setOnClickListener(this);
        ((Button) findViewById(R.id.viewDataHistory_Button)).setOnClickListener(this);
        ((Button) findViewById(R.id.instructionManual_Button)).setOnClickListener(this);

        /*Scan db if the contents are less than 10*/
         /*scanDBifCodeIsLessThanTen();*/

        productAlertDialog();

        /*if(codeDataIsLessThanTen == true){
//          Create an Intent that will start the Menu-Activity.
//                    Intent mainIntent = new Intent(splashScreen.this, MainActivity.class);
//                    splashScreen.this.startActivity(mainIntent);
//                    splashScreen.this.finish();

        }else{
//                    call the alert method to check for product activation code
            productAlertDialog();
        }
*/
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /*-----------------------------------------------------------------------------*/

    void productAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Required: Activation Code.");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Please enter the code provided by the developers to activate the application.");

        input = new EditText(this);
        builder.setView(input);
        /*set the positive button*/
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               /* String user_input = input.getText().toString().trim();
                Cursor cursor = myDB.scanProductCodes(user_input);

                if(cursor.getCount() == 0){  //if the cursor is empty, display toast message
                   *//* display alert dialog that pops when there is no matching code in the database*//*
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("The product code you have entered is invalid");
                    builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            productAlertDialog();
                        }
                    });


                    //show confirm dialog
                    builder.create().show();
//
//                    if(cursor.getCount() < 10){
//                        Toast.makeText(getApplicationContext(), "Application activated!", Toast.LENGTH_SHORT).show();
//
//                        // open next activity through intent
//                        Intent mainIntent = new Intent(splashScreen.this, MainActivity.class);
//                        splashScreen.this.startActivity(mainIntent);
//                        splashScreen.this.finish();
//
//                    }else{
//
//                    }
                }

                else{
                   // if the input code has a similar input in the database then delete the data to make the row number 9
                    myDB.deleteValuesFromCodeDB(user_input);

                   // open next activity through intent
//                    Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
//                    splashScreen.this.startActivity(mainIntent);
//                    splashScreen.this.finish();
                }*/



            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();   //dismiss the dialog
            }
        });

        //show alert dialog
        builder.create().show();
/////////////////////////////////////////////////////////////////////////////////////////////////



    }

    /*protected void scanDBifCodeIsLessThanTen(){

        Cursor cursor = myDB.rescanCodeTable();
        if(cursor.getCount() == 0){
            Toast.makeText(this, cursor.getCount(), Toast.LENGTH_SHORT).show();
           *//* return trigger = true;*//*

        }else{
            Toast.makeText(this, cursor.getCount(), Toast.LENGTH_SHORT).show();
           *//* return trigger = false;*//*
           *//* productAlertDialog();*//*
        }



    }*/

}