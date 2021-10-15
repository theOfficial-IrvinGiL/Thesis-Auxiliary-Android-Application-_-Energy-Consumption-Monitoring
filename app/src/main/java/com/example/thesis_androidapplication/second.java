package com.example.thesis_androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class second extends AppCompatActivity implements View.OnClickListener{

    MyDatabaseHelper myDB = new MyDatabaseHelper(this);

    @Override
    public void onClick(View clickView) {
        switch (clickView.getId()){
            case R.id.manage_contacts_card:
                Intent toAnotherAct =  new Intent(this, manage_contacts.class);
                startActivity(toAnotherAct);
                break;

            case R.id.update_energy_card:

                /*check if the contact database is empty*/
                Cursor cursor =  myDB.readAllData();
                if(cursor.getCount() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Empty contacts:");
                    builder.setMessage("You have no contacts, please add contact numbers to use the update energy feature.");

                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


                    //show confirm dialog
                    builder.create().show();


                }else{
                    /*go to the update energy consumption activity*/
                    toAnotherAct =  new Intent(second.this, update_energy.class);
                    startActivity(toAnotherAct);
                }


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