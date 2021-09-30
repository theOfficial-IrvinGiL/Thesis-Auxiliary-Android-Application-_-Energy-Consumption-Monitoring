package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class second extends AppCompatActivity implements View.OnClickListener{



    @Override
    public void onClick(View clickView) {
        switch (clickView.getId()){
            case R.id.updateButton:
                Intent toAnotherAct =  new Intent(this, update_energy.class);
                startActivity(toAnotherAct);
                break;

            case R.id.manageContactsButton:
                toAnotherAct =  new Intent(this, manage_contacts.class);
                startActivity(toAnotherAct);
                break;

            default:
                break;



        }
    }
    private Button updateUsers, manageCons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        updateUsers =  findViewById(R.id.updateButton);
        manageCons = findViewById(R.id.manageContactsButton);

        updateUsers.setOnClickListener(this);
        manageCons.setOnClickListener(this);
    }
}