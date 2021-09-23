package com.example.thesis_androidapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update_class extends AppCompatActivity {


    EditText editKwh, editAmount;
    theDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);

        db = new theDatabase(this);
        Button saveButton = findViewById(R.id.SaveButton);
        editKwh = findViewById(R.id.kwhEdit);
        editAmount = findViewById(R.id.amountEdit);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertInfo();
            }
        });
    }

    private void insertInfo (){
        String kwh = editKwh.getText().toString();
        String amount = editAmount.getText().toString();
        boolean result = db.insertingInfo(kwh,amount);

        if (result)
            Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Ooops. Save Failed", Toast.LENGTH_SHORT).show();
    }
}

