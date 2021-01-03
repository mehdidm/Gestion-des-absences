package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestion_des_absences.adapters.DatabaseHelper;

public class history extends AppCompatActivity {
    DatabaseHelper MyDB;
EditText editText;
Button btnE;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        editText=findViewById(R.id.imput);
        MyDB = new DatabaseHelper(this);
intent= getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        viewAbsents();
    }
    public void viewAbsents() {
        btnE=findViewById(R.id.btn_id);
        btnE.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //String subject= editText.getText().toString();
                        String subject= intent.getStringExtra("Subject");
                        Cursor res = MyDB.getEliminations(subject);
                        if (res.getCount() == 0) {
                            // show message
                            showResult("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("student name :" + res.getString(0) + "\n");
                            buffer.append("nombre absences :" + res.getString(1) + "\n");
                        }

                        // Show all data
                        showResult("Data", buffer.toString());
                    }
                }
        );
    }
    public void showResult(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}