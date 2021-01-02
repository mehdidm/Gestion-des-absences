package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gestion_des_absences.adapters.DatabaseHelper;
import com.example.gestion_des_absences.adapters.StudentAdapter;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;

public class listEtudiant extends AppCompatActivity {

    DatabaseHelper MyDB;

    ListView listEtudiants;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //variables
        MyDB = new DatabaseHelper(this);

        setContentView(R.layout.activity_list_etudiant);
        listEtudiants=(ListView) findViewById(R.id.etudiantList);


        ArrayList<Student> values = (ArrayList<Student>) MyDB.getStudents();

        StudentAdapter adapter = new StudentAdapter(this,R.layout.etudiant_item,values);
        listEtudiants.setAdapter(adapter);   }
}