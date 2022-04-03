package com.example.thesis_androidapplication;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateContacts extends AppCompatActivity {

    /*instantiate the activity*/
    Activity activity = UpdateContacts.this;

    EditText new_contactName, new_contactNumber, new_passcode;
    Button update_button, delete_button;

    String id, userName, contactNumber, passcode;

    //variables for getting the deleted passcode and contact number
    String del_number, del_passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);

        new_contactName = findViewById(R.id.new_contact_name2);
        new_contactNumber = findViewById(R.id.new_contact_number2);
        new_passcode = findViewById(R.id.new_contact_passcode);


        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.delete_button);

        //call the getAndSEt Method
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = new_contactName.getText().toString().trim();
                contactNumber = new_contactNumber.getText().toString().trim();

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateContacts.this);
                myDB.updateData(id, userName, contactNumber);

                activity.setResult(2);
                finish();

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog(); //show confirm dialog for deleting
            }
        });


    }

    //for getting the sent data from intent
    void getAndSetIntentData() {
        if (getIntent().hasExtra("contact_id") &&
                getIntent().hasExtra("contact_name") &&
                getIntent().hasExtra("contact_number") &&
                getIntent().hasExtra("contact_passcode")) {

            id = getIntent().getStringExtra("contact_id");
            userName = getIntent().getStringExtra("contact_name");
            contactNumber = getIntent().getStringExtra("contact_number");
            passcode = getIntent().getStringExtra("contact_passcode");


            //setting intent data

            new_contactName.setText(userName);
            new_contactNumber.setText(contactNumber);
            new_passcode.setText(passcode);
        } else {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
    }

    // showing confirm dialog on clicking the delete button
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Contact: " + userName + " ?");
        builder.setMessage("Are you sure you want to delete this contact? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get the data from text box
                del_passcode = new_passcode.getText().toString().trim();
                del_number = new_contactNumber.getText().toString().trim();
                putDeletedData_toClipboard(); //put the acquired data into clipboard

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateContacts.this);
                myDB.deleteContact(id);



                /*set the result code*/
                activity.setResult(3);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //show confirm dialog
        builder.create().show();
    }

    /* method to override the back button to return to parent*/
    @Override
    public void onBackPressed() {
        finish();

       
    }

    /**
     * code for loading the data that was deleted into the clipboard for
     * deleting to the main unit
     */
    void putDeletedData_toClipboard() {
        //write code for copying the generated passcode into the clipboard
        String serialData = del_passcode + "," + del_number + ","; //concatenate the acquired data

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("SerialData", serialData);
        clipboard.setPrimaryClip(clip);

        //then display an message to remind the admin to use the serial android application
        Toast.makeText(getApplicationContext(), "Contact deleted successfully", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Formatted Serial data copied to clipboard!", Toast.LENGTH_SHORT).show();


    }


}