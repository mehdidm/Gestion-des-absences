package com.example.gestion_des_absences;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.gestion_des_absences.adapters.StudentAdapter;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;

public class MainActivity extends Activity  {

Spinner groupes , seances ,matieres;
ArrayList<String> arraylist_Groupes;
ArrayAdapter<String> arraylist_adapter_groupes,
    arraylist_adapter_matieres, arraylist_adapter_seances
       ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        groupes=(Spinner)findViewById(R.id.Groupes);

        arraylist_Groupes = new ArrayList<>();
        arraylist_Groupes.add("Dsi31");
        arraylist_Groupes.add("DSi32");
        arraylist_Groupes.add("Sem31");
        arraylist_adapter_groupes = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arraylist_Groupes );
        groupes.setAdapter(arraylist_adapter_groupes);


        ArrayList<String> arraylist_Seances;
        seances=(Spinner)findViewById(R.id.Seances);
        arraylist_Seances = new ArrayList<>();
        arraylist_Seances.add("S1");
        arraylist_Seances.add("S2");
        arraylist_Seances.add("S3");
        arraylist_adapter_seances = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arraylist_Seances );
        seances.setAdapter(arraylist_adapter_seances);




        ArrayList<String> arraylist_Matieres;
        matieres=(Spinner)findViewById(R.id.Matieres);
        arraylist_Matieres = new ArrayList<>();
        arraylist_Matieres.add("Conception");
        arraylist_Matieres.add("Dev mobile");
        arraylist_Matieres.add("Dev Web");
        arraylist_adapter_matieres = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arraylist_Matieres );
        matieres.setAdapter(arraylist_adapter_matieres);

}
}