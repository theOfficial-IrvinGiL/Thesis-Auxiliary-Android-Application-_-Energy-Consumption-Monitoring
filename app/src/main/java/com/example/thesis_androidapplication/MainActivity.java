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

    /*initialize to be used to get input in the alert dialog*/
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

            default:


                break;
        }
    }

    /*the on create method*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *       Cast and use onclicklistener instead of onclick method
         * */

        ((Button) findViewById(R.id.manageEnergy_Button)).setOnClickListener(this);
        ((Button) findViewById(R.id.viewDataHistory_Button)).setOnClickListener(this);
//        ((Button) findViewById(R.id.instructionManual_Button)).setOnClickListener(this);



        /*Scan db if the contents are less than 10
         * and if the number of contents are =10 then the input dialog prompts up*/
        int  code_population = numberOfCodeContent();
        if(code_population <= 9){
            /*do nothing*/
        }else{
            productAlertDialog();
            ((Button) findViewById(R.id.manageEnergy_Button)).setEnabled(false);
            ((Button) findViewById(R.id.viewDataHistory_Button)).setEnabled(false);
//            ((Button) findViewById(R.id.instructionManual_Button)).setEnabled(false);
        }


    }

    /*on back pressed trigger method*/
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


    /*input alert dialog method
    * that prompts up to show that the product code has not been activated*/
    void productAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Required: Activation Code.");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Please enter the code provided by the developers to activate the application.");

        input = new EditText(this);     // instantiate the edit text
        builder.setView(input);     //declare as view in the input alert dialog

        /*set the positive button*/
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String user_input = input.getText().toString().trim();  //get values from the edittext object
                Cursor cursor = myDB.scanProductCodes(user_input);  //using a database helper method to scan the input for similarities in the database

                if (cursor.getCount() == 0) {  //if the cursor is empty, display toast message
                    //display alert dialog that pops when there is no matching code in the database
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("The product code you have entered is invalid");
                    builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            productAlertDialog();
                        }
                    });


                    //show dialog
                    builder.create().show();
                } else {
                    // if the input code has a similar input in the database then delete the data to make the row number 9
                    myDB.deleteValuesFromCodeDB(user_input);
                    /*enable the buttons*/
                    ((Button) findViewById(R.id.manageEnergy_Button)).setEnabled(true);
                    ((Button) findViewById(R.id.viewDataHistory_Button)).setEnabled(true);
//                    ((Button) findViewById(R.id.instructionManual_Button)).setEnabled(true);

                }


            }
        });


        //show alert dialog
        builder.create().show();
    }


    /*method used along with the db helper method to scan the number of code contents in the database */
    protected int numberOfCodeContent(){
        int number = 0;
        Cursor cursor = myDB.rescanCodeTable();
        if(cursor.getCount() == 0){ return number = 0; }
        else{ return  number = cursor.getCount(); }



    }

}