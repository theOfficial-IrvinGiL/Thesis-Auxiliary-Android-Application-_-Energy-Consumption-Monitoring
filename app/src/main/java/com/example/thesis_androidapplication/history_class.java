package com.example.thesis_androidapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class history_class extends AppCompatActivity {
    theDatabase db;
    TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.history);

        db = new theDatabase(this);
        textInfo = findViewById(R.id.textInfoNi);
        readHistory();
    }
    private void readHistory(){
        Cursor cursor = db.getAllInfo();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor != null && cursor.getCount()>0){
    while (cursor.moveToNext()){
        stringBuilder.append("USER ID: ").append(cursor.getString(0)).append("\n");
        stringBuilder.append("KWH: ").append(cursor.getString(1)).append("\n");
        stringBuilder.append("AMOUNT: ").append(cursor.getString(2)).append("\n\n");
    }
    textInfo.setText(stringBuilder.toString());
        }else
            Toast.makeText(this, "No Data to retrieved!", Toast.LENGTH_SHORT).show();
    }

}
