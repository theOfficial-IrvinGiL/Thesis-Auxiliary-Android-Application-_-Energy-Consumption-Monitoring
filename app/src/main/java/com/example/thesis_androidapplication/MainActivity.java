package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

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
                Toast.makeText(this,"You have selected view data history",Toast.LENGTH_SHORT).show();

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

}