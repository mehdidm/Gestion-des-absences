package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion_des_absences.adapters.DatabaseHelper;
import com.example.gestion_des_absences.ui.login.LoginActivity;

public class authentification extends AppCompatActivity {

    EditText userName,password;
    Button btnSubmit;
    DatabaseHelper MyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        MyDB = new DatabaseHelper(this);

        userName=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btnSubmit=findViewById(R.id.login);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isExist = MyDB.checkUserExist(userName.getText().toString(), password.getText().toString());

                if(isExist){
                    Intent intent = new Intent(authentification.this, MainActivity.class);
                    intent.putExtra("username", userName.getText().toString());
                    startActivity(intent);
                } else {
                    password.setText(null);
                    Toast.makeText(authentification.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}