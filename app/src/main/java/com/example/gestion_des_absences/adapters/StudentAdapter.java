package com.example.gestion_des_absences.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gestion_des_absences.R;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;


public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> ListView) {
        super(context,
                resource,
                ListView);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
DatabaseHelper MyDB;

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        TextView nom= convertView.findViewById(R.id.nomEtPrenom);
        EditText Ds=convertView.findViewById(R.id.ds);
        EditText Ex = convertView.findViewById(R.id.ex);
        CheckBox checkBox;
        checkBox= convertView.findViewById(R.id.checkBox);

        //declaration de bdd
        MyDB = new DatabaseHelper(context);

        Student currentStudent =getItem(position);
        nom.setText(currentStudent.getFullname());

        //Ds.setText((CharSequence) MyDB.getStudentNotes(currentStudent.getFullname()));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked && Ds.getText().toString().matches("")&&Ex.getText().toString().matches("")){

                    MyDB.updateData(currentStudent.getCIN(),"present");

                    }else if(! isChecked&& Ds.getText().toString().matches("")&&Ex.getText().toString().matches("")){
//
                    MyDB.updateData(currentStudent.getCIN(),"absent");
                    MyDB.insertHistoryStudents(currentStudent.getFullname(),"dev mobile");

                }

                else {
                    MyDB.updateData(currentStudent.getCIN(),"present");
                    MyDB.insertNote(Ds.getText().toString(),Ex.getText().toString(),"conception",currentStudent.getFullname(),getMoyenne(Ds.getText().toString(),Ex.getText().toString()));

                }
            }
        });






        return convertView;
    }

    public String getMoyenne(String x, String y){
        return
               String.valueOf((Double.parseDouble(String.valueOf(x))+Double.parseDouble(String.valueOf(y)))/2) ;
    }



}
