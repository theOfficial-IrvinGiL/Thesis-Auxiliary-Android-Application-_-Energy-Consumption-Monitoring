package com.example.thesis_androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

        public class MainActivity extends AppCompatActivity implements View.OnClickListener{


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

}