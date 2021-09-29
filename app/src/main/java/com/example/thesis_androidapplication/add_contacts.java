package com.example.thesis_androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class add_contacts extends AppCompatActivity {

    EditText new_contact_name, new_contact_number;

    Button TosaveButton;

    MyDatabaseHelper myDB = new MyDatabaseHelper(this);


    //to determine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        //initialize get id
        new_contact_name = findViewById(R.id.new_contact_name);
        new_contact_number = findViewById(R.id.new_contact_number);

        TosaveButton =  findViewById(R.id.saveButton);





        TosaveButton.setOnClickListener(new View.OnClickListener() { //save button listener
            @Override
            public void onClick(View view) {

                //to determine the characters inputs to be only 11 characters
                String scanThisName = new_contact_name.getText().toString().trim();
                String scanThisContact = new_contact_number.getText().toString().trim();
                int countNumbers = 0;
                for(int i = 0; i < scanThisContact.length(); i++)
                {
                    if(scanThisContact.charAt(i) != ' ')
                        countNumbers++;
                }

                //condition to determine if input is >11
                if (countNumbers >= 10 && countNumbers <=11) { //save to database if number is less than 11 and not less than 10
                    if(scanThisName.isEmpty()){
                        emptyNameWarning();

                    }else{
                        //scan the db for existing contacts
                        Cursor cursor = myDB.searchDBforExistingContact(new_contact_number.getText().toString().trim());
                        if(cursor.getCount() == 0){
                            myDB.add_thiscontact(new_contact_name.getText().toString().trim(),
                                    new_contact_number.getText().toString().trim());

                            /* snackbar notification */
                            Snackbar.make(findViewById(android.R.id.content),"Contact added successfully!",Snackbar.LENGTH_SHORT).show();



                        }else{
                            Toast.makeText(add_contacts.this, "Contact already exists!", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                else{
                    contactWarning();
                }
            }

        });
    }

    //warning for error in contact number Input
    void contactWarning (){
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
    void emptyNameWarning(){
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


    /*void searchThisContactOnDB(String contact){
        Cursor cursor = myDB.searchDBforExistingContact(contact);
        if(cursor.getCount() == 0){
            myDB.add_thiscontact(new_contact_name.getText().toString().trim(),
                    new_contact_number.getText().toString().trim());
        }else{
            Toast.makeText(this, "Contact already exists!", Toast.LENGTH_SHORT).show();

        }

    }*/

}