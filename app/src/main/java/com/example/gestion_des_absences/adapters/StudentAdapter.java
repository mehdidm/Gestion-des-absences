package com.example.gestion_des_absences.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gestion_des_absences.R;
import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
          TextView nom= convertView.findViewById(R.id.nomEtPrenom);
        EditText ds= convertView.findViewById(R.id.noteDs);
        EditText ex= convertView.findViewById(R.id.noteEx);
        CheckBox absence = convertView.findViewById(R.id.checkBox);

        Student currentStudent =getItem(position);
        nom.setText(currentStudent.getFullname());
        ds.setText(currentStudent.getDs());
        ex.setText(currentStudent.getExamen());


        return convertView;
    }
}
