package com.example.thesis_androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class second extends AppCompatActivity implements View.OnClickListener{



    @Override
    public void onClick(View clickView) {
        switch (clickView.getId()){
            case R.id.manage_contacts_card:
                Intent toAnotherAct =  new Intent(this, manage_contacts.class);
                startActivity(toAnotherAct);
                break;

            case R.id.update_energy_card:
                toAnotherAct =  new Intent(this, update_energy.class);
                startActivity(toAnotherAct);
                break;

            default:
                break;



        }
    }

    CardView update_energy_card, manage_contacts_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        update_energy_card = findViewById(R.id.update_energy_card);
        manage_contacts_card = findViewById(R.id.manage_contacts_card);

        update_energy_card.setOnClickListener(this);
        manage_contacts_card.setOnClickListener(this);

    }

    /* override method to return to parent activity*/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(second.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}