package com.example.gestion_des_absences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion_des_absences.adapters.DatabaseHelper;
import com.example.gestion_des_absences.adapters.StudentAdapter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends Activity  {
    DatabaseHelper MyDB;
    Intent loginIntent,intentGRP,adapterIntent;
Spinner groupes , seances ,matieres;
String groupe,seance,matiere;
Button btnDone,btnView,btnAB,btnNote;
TextView Tname;
//declaration arraylist et adapter for classes list
ArrayList<String> arraylist_Groupes,arraylist_Seances,arraylist_Matieres;

ArrayAdapter<String> arraylist_adapter_groupes,
    arraylist_adapter_matieres, arraylist_adapter_seances
       ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupes = (Spinner) findViewById(R.id.Groupes);
        btnDone= findViewById(R.id.btnDone);
        btnView=findViewById(R.id.btnView);
//        btnAB=findViewById(R.id.btnE);
//        btnNote=findViewById(R.id.btn_notes);
        MyDB = new DatabaseHelper(this);
        loginIntent=getIntent();
        Tname=findViewById(R.id.name);
        Tname.setText(loginIntent.getStringExtra("username"));
        arraylist_Groupes = new ArrayList<>();

        prepareDataGRoupes();
        groupes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                 groupe = parent.getItemAtPosition(position).toString();
                 intentGRP= new Intent(MainActivity.this,listEtudiant.class);
                 intentGRP.putExtra("groupe" ,
                         groupe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();

            }
        });



        matieres = (Spinner) findViewById(R.id.Matieres);
        arraylist_Matieres = new ArrayList<>();
//        arraylist_Matieres.add("Conception");
//        arraylist_Matieres.add("Dev mobile");
//        arraylist_Matieres.add("Dev Web");
        prepareDataMatieres();
//        arraylist_adapter_matieres = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arraylist_Matieres);
//        matieres.setAdapter(arraylist_adapter_matieres);
        matieres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                matiere = parent.getItemAtPosition(position).toString();
                adapterIntent = new Intent(MainActivity.this, StudentAdapter.class);
                adapterIntent.putExtra("matiere",matiere);
                intentGRP.putExtra("matiere" ,
                        matiere);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        AddData(groupe,matiere,currentDate,loginIntent.getStringExtra("username"));
        viewAll();
//        viewAbsents(matiere);
//        viewNotes();
    }


    //choix de seance et groupe et matiere et insertion dans la table sessions_table
        public void AddData(String groupe, String matiere, String seance,String teacher) {
            btnDone.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = MyDB.insertSession(MainActivity.this.groupe, MainActivity.this.matiere,seance,teacher);
                            startActivity(intentGRP);
//                            myIntent.putExtra("Subject",MainActivity.this.matiere);
//                            MainActivity.this.startActivity(myIntent);
                            if (isInserted == true)
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            else
                                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
//affichage de table des seances

    public void viewAll() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Cursor res = MyDB.getAllData("enseignement_table");
                        Cursor res = MyDB.getTeacherSessions(loginIntent.getStringExtra("username"));

                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Groupe :" + res.getString(1) + "\n");
                            buffer.append("Matiere :" + res.getString(2) + "\n");
                            buffer.append("Date :" + res.getString(3) + "\n");

                        }

                        // Show all data
                        showMessage("Liste des seances", buffer.toString());
                    }
                }
        );
    }
//affichage de table notes_table
//    public void viewNotes() {
//        btnNote.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = MyDB.getNotes(matiere);
//
//                        if (res.getCount() == 0) {
//                            // show message
//                            showMessage("Error", "Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("student name :" + res.getString(4) + "\n");
//                            buffer.append("Matiere :" + res.getString(2) + "\n");
//                            buffer.append("note Ds :" + res.getString(1) + "\n");
//                            buffer.append("note Examen :" + res.getString(3) + "\n");
//                            buffer.append("moyenne generale :" + res.getString(5)+ "\n");
//
//                        }
//
//                        // Show all data
//                        showMessage("Notes", buffer.toString());
//                    }
//                }
//        );
//
//    }
    //affichage des liste des absences et nombre des absences de chaque matiere choisi
//    public void viewAbsents(String matiere) {
//        btnAB.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = MyDB.getEliminations(matiere);
//                        if (res.getCount() == 0) {
//                            // show message
//                            showMessage("Error", "Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("student name :" + res.getString(0) + "\n");
//                            buffer.append("nombre absences :" + res.getString(1) + "\n");
//                        }
//
//                        // Show all data
//                        showMessage("Liste absences", buffer.toString());
//                    }
//                }
//        );
//    }

//fonction d'affichage de pop up
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
//put data into spinner
    public void prepareDataGRoupes() {
        arraylist_Groupes = (ArrayList<String>) MyDB.getAllclasses();
        //adapter for spinner
        arraylist_adapter_groupes = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, arraylist_Groupes);
        //attach adapter to spinner
        groupes.setAdapter(arraylist_adapter_groupes);

    }


    public void prepareDataMatieres() {
        arraylist_Matieres = (ArrayList<String>) MyDB.getAllmatieres();
        //adapter for spinner
        arraylist_adapter_matieres = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, arraylist_Matieres);
        //attach adapter to spinner
        matieres.setAdapter(arraylist_adapter_matieres);

    }



}