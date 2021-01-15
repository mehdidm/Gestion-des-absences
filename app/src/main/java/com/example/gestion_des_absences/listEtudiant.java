package com.example.gestion_des_absences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
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
    Intent myintent;


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

        Button btnAbsence=findViewById(R.id.absence);
        Button btnNotes=findViewById(R.id.note);
        matName=findViewById(R.id.matName);
        grpName=findViewById(R.id.grpName);
        grpName.setText(groupe);
        matName.setText(matiere);
        listEtudiants=(ListView) findViewById(R.id.etudiantList);

        ArrayList<Student> values = (ArrayList<Student>) MyDB.getStudents(groupe);

        StudentAdapter adapter = new StudentAdapter(this,R.layout.etudiant_item,values);
        listEtudiants.setAdapter(adapter);

        btnAbsence.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = MyDB.getEliminations(myintent.getStringExtra("matiere"));
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("student name :" + res.getString(0) + "\n");
                            buffer.append("nombre absences :" + res.getString(1) + "\n");
                        }

                        // Show all data
                        showMessage("Liste absences", buffer.toString());
                    }
                }
        );
        btnNotes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = MyDB.getNotes(matiere);

                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("student name :" + res.getString(4) + "\n");
                            buffer.append("Matiere :" + res.getString(2) + "\n");
                            buffer.append("note Ds :" + res.getString(1) + "\n");
                            buffer.append("note Examen :" + res.getString(3) + "\n");
                            buffer.append("moyenne generale :" + res.getString(5)+ "\n");

                        }

                        // Show all data
                        showMessage("Notes", buffer.toString());
                    }
                }
        );

    }



    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}