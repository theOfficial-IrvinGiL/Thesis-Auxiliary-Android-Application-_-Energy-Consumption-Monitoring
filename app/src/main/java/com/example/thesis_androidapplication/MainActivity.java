package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//sample comment
    public void manageMethod(View manageButton){
        Intent i =new Intent(this, secondActivity.class); //initializing the intent
        startActivity(i);  //start the next activity
    }
}
//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