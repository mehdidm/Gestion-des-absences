package com.example.gestion_des_absences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestion_des_absences.adapters.DatabaseHelper;
import com.example.gestion_des_absences.adapters.StudentAdapter;
import com.example.gestion_des_absences.classes.Session;

import java.util.ArrayList;

public class MainActivity extends Activity  {
    DatabaseHelper MyDB;
Spinner groupes , seances ,matieres;
String groupe,seance,matiere;
Button btnDone,btnView,btnAB,btnNote;
ArrayList<String> arraylist_Groupes;
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
        btnAB=findViewById(R.id.btnE);
        btnNote=findViewById(R.id.btn_notes);
        MyDB = new DatabaseHelper(this);

        arraylist_Groupes = new ArrayList<>();
        arraylist_Groupes.add("Dsi31");
        arraylist_Groupes.add("DSi32");
        arraylist_Groupes.add("Sem31");
        arraylist_adapter_groupes = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arraylist_Groupes);
        groupes.setAdapter(arraylist_adapter_groupes);
        groupes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                 groupe = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();

            }
        });


        ArrayList<String> arraylist_Seances;
        seances = (Spinner) findViewById(R.id.Seances);
        arraylist_Seances = new ArrayList<>();
        arraylist_Seances.add("S1");
        arraylist_Seances.add("S2");
        arraylist_Seances.add("S3");
        arraylist_adapter_seances = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arraylist_Seances);
        seances.setAdapter(arraylist_adapter_seances);

        seances.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                 seance = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<String> arraylist_Matieres;
        matieres = (Spinner) findViewById(R.id.Matieres);
        arraylist_Matieres = new ArrayList<>();
        arraylist_Matieres.add("Conception");
        arraylist_Matieres.add("Dev mobile");
        arraylist_Matieres.add("Dev Web");
        arraylist_adapter_matieres = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arraylist_Matieres);
        matieres.setAdapter(arraylist_adapter_matieres);
        matieres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                matiere = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
        AddData(groupe,matiere,seance);
        viewAll();
        getAbsences();
        viewNotes();
    }
        public void AddData(String groupe, String matiere, String seance) {
            btnDone.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = MyDB.insertSession(MainActivity.this.groupe, MainActivity.this.matiere, MainActivity.this.seance);
                            Intent myIntent = new Intent(MainActivity.this, listEtudiant.class);
                            myIntent.putExtra("Subject",MainActivity.this.matiere);
                            MainActivity.this.startActivity(myIntent);
                            if (isInserted == true)
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            else
                                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }


    public void viewAll() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = MyDB.getAllData("sessions_table");

                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Groupe :" + res.getString(1) + "\n");
                            buffer.append("Matiere :" + res.getString(2) + "\n");
                            buffer.append("Seance :" + res.getString(3) + "\n");

                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void viewNotes() {
        btnNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = MyDB.getAllData("Notes_table");

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

    public void getAbsences(){
        btnAB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //MyDB.getAbsences();
                        Intent myIntent = new Intent(MainActivity.this, history.class);
                        myIntent.putExtra("Subject",MainActivity.this.matiere);
                        MainActivity.this.startActivity(myIntent);
                    }
                }
        );

    }


}