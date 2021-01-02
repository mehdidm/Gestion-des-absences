package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gestion_des_absences.adapters.StudentAdapter;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;

public class listEtudiant extends AppCompatActivity {
ListView listEtudiants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);
        listEtudiants=(ListView) findViewById(R.id.etudiantList);
        ArrayList<Student> values = new ArrayList<>();
        values.add(new Student("222222","Meher ben Meher","ddddddd","fffffff","aa"));
        values.add(new Student("2","Meher ben Meher","ddddddd","fffffff","aa"));
        values.add(new Student("3","Meher ben Meher","ddddddd","fffffff","aa"));
        values.add(new Student("4","Meher ben Meher","ddddddd","fffffff","aa"));

        StudentAdapter adapter = new StudentAdapter(this , R.layout.etudiant_item ,values );
        listEtudiants.setAdapter(adapter);   }
}