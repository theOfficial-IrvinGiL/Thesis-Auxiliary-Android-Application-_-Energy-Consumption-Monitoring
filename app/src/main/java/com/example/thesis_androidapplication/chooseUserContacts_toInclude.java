package com.example.thesis_androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

public class chooseUserContacts_toInclude extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_contacts_to_include);



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
               Intent thisIntent = new Intent(chooseUserContacts_toInclude.this, second.class);
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
                Snackbar.make(findViewById(android.R.id.content),"Updating process restarted successfully!",Snackbar.LENGTH_SHORT).show();
                Intent thisIntent = new Intent(this, chooseUserContacts_toInclude.class);
                startActivity(thisIntent);
                break;
            /* redirect to the second_activity to cancel the process of updating the energy consumption*/
            case R.id.cancel_process:
                Snackbar.make(findViewById(android.R.id.content),"Updating process has been canceled.",Snackbar.LENGTH_SHORT).show();
                thisIntent = new Intent(this, second.class);
                startActivity(thisIntent);
                break;
            default: // nothing happens
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    /* end of override methods for the use of the toolbar option */
}