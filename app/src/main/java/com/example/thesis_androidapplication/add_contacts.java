package com.example.thesis_androidapplication;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class add_contacts extends AppCompatActivity {

    Activity activity = add_contacts.this;

    EditText new_contact_name, new_contact_number;

    Button TosaveButton;

    MyDatabaseHelper myDB = new MyDatabaseHelper(this);

    static String passCode;

    //to determine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        //initialize get id
        new_contact_name = findViewById(R.id.new_contact_name);
        new_contact_number = findViewById(R.id.new_contact_number);

        TosaveButton = findViewById(R.id.saveButton);


        TosaveButton.setOnClickListener(new View.OnClickListener() { //save button listener
            @Override
            public void onClick(View view) {

                //to determine the characters inputs to be only 11 characters
                String scanThisName = new_contact_name.getText().toString().trim();
                String scanThisContact = new_contact_number.getText().toString().trim();
                int countNumbers = 0;
                for (int i = 0; i < scanThisContact.length(); i++) {
                    if (scanThisContact.charAt(i) != ' ')
                        countNumbers++;
                }

                //condition to determine if input is >11
                if (countNumbers == 11) { //save to database if number is less than 11 and not less than 10
                    if (scanThisName.isEmpty()) {
                        emptyNameWarning();

                    } else {
                        //write code for generating random number here
                        /*run while loop to generate and check if the generated
                        numbers already exists in the data base*/
                        while (true) {
                            passCode = generatePasscode();
                            Cursor cursor = myDB.searchDBforExistingPasscode(passCode);
                            if (cursor.getCount() == 0) {
                                break; // break the loop if there is no redundancy of data
                            }
                        }


                        //scan the db for existing contacts
                        Cursor cursor = myDB.searchDBforExistingContact(new_contact_number.getText().toString().trim());
                        if (cursor.getCount() == 0) {
                            myDB.add_thiscontact(new_contact_name.getText().toString().trim(),
                                    new_contact_number.getText().toString().trim(), passCode);

                            //concatenate the data to be copied on clipboard
                            String serialData = passCode + ","
                                    + (new_contact_number.getText().toString().trim()) + ",";

                            //write code for copying the generated passcode into the clipboard
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("SerialData", serialData);
                            clipboard.setPrimaryClip(clip);



                            /* snackbar and toast notification */
                            /* Snackbar.make(findViewById(android.R.id.content),"Contact added successfully!",Snackbar.LENGTH_SHORT).show();*/
                            Toast.makeText(getApplicationContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Formatted Serial data copied to clipboard!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Use the recommended serial application to upload data into the Main Unit to register.", Toast.LENGTH_LONG).show();
                            /*set the result code for the parent activity to identify*/

                            finish();


                        } else {
                            Toast.makeText(add_contacts.this, "Contact already exists!", Toast.LENGTH_SHORT).show();

                        }
                    }

                } else {
                    contactWarning();
                }
            }

        });
    }

    //warning for error in contact number Input
    void contactWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact Input Invalid!");
        builder.setMessage("It seems that your contact input has exceeded or is less than 11 characters.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //create and show builder
        builder.create().show();


    }

    //warning for empty name input
    void emptyNameWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Empty Contact Name!");
        builder.setMessage("It seems that you have not input a contact name.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //create and show builder
        builder.create().show();
    }

    //method for generating random 4 digit number for the passcode
    public static String generatePasscode() {
        // It will generate 4 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        // this will convert any number sequence into 6 character.
        return String.format("%04d", number);

    }


    /* method to override the back button to return to parent*/
    @Override
    public void onBackPressed() {
        finish();

        return;
    }
}