package com.example.gestion_des_absences.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        CheckBox checkBox;
        checkBox= convertView.findViewById(R.id.checkBox);
        MyDB = new DatabaseHelper(context);

        Student currentStudent =getItem(position);
        nom.setText(currentStudent.getFullname());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                   MyDB.updateData(currentStudent.getCIN(),"present");
                }
                else{
                    MyDB.updateData(currentStudent.getCIN().toString(),"absent");
                    MyDB.insertHistoryStudents(currentStudent.getFullname(),"web dev");

                }
            }
        });


        return convertView;
    }


}
