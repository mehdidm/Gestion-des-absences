package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion_des_absences.adapters.DatabaseHelper;
import com.example.gestion_des_absences.adapters.StudentAdapter;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;

public class listEtudiant extends AppCompatActivity {

    DatabaseHelper MyDB;
    ListView listEtudiants;
    TextView grpName,matName;
    CheckBox checkBox;
    Intent myintent,matIntent;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyDB = new DatabaseHelper(this);
        checkBox=findViewById(R.id.checkBox);
        myintent=getIntent();
        //matIntent=getIntent();
        String groupe = myintent.getStringExtra("groupe");
        String matiere=myintent.getStringExtra("matiere");
        setContentView(R.layout.activity_list_etudiant);


        matName=findViewById(R.id.matName);
        grpName=findViewById(R.id.grpName);
        grpName.setText(groupe);
        matName.setText(matiere);
        listEtudiants=(ListView) findViewById(R.id.etudiantList);

        ArrayList<Student> values = (ArrayList<Student>) MyDB.getStudents(groupe);

        StudentAdapter adapter = new StudentAdapter(this,R.layout.etudiant_item,values);
        listEtudiants.setAdapter(adapter);



    }



}