package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class splashScreen extends AppCompatActivity {
    /*this is a splashscreen activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*hides the actionbar*/
        getSupportActionBar().hide();

        /*code to handle the splash screen timming and executes the next activity in 2 seconds*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splashScreen.this, MainActivity.class);
                splashScreen.this.startActivity(mainIntent);
                splashScreen.this.finish();
            }
        }, 2000);


    }
}