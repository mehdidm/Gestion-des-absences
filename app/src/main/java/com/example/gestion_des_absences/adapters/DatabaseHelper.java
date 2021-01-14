package com.example.gestion_des_absences.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
//declaration des tableaux

    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME1 = "students_table";
    public static final String TABLE_NAME2 = "enseignement_table";
    public static final String TABLE_NAME3 = "Notes_table";
    public static final String TABLE_NAME4 = "history_table";
    public static final String TABLE_NAME5 = "teachers_table";
    public static final String TABLE_NAME6 = "matiere_table";
    public static final String TABLE_NAME7 = "classe_table";
    public static final String TABLE_NAME8 = "seance_table";


    @Override
    //creation des tableaux dans la base
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FULLNAME TEXT,STATUS TEXT,CLASS TEXT)");
        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TEACHER TEXT,CLASSNAME TEXT,SUBJECT TEXT,TIME TEXT)");
        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DS TEXT,SUBJECT TEXT,EXAMEN TEXT,STUDENTNAME TEXT,MOYENNE TEXT)");
        db.execSQL("create table " + TABLE_NAME4 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUDENTNAME TEXT,SUBJECT TEXT)");
        db.execSQL("create table " + TABLE_NAME5 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_NAME6 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DESC_mat TEXT,VOL_HR_mat TEXT, COEF_mat TEXT)");
        db.execSQL("create table " + TABLE_NAME7 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CODE_class TEXT,DESC_class TEXT)");
        db.execSQL("create table " + TABLE_NAME8 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE_sce TEXT,TYPE_sce TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME6);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME7);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME8);

    }


    //getting all classes from database
    public List<String> getAllclasses()
    {
        List<String> classlist=new ArrayList<>();
        //get readable database
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT DESC_class FROM classe_table",null);
        if(cursor.moveToFirst())
        {
            do {
                classlist.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the database
        db.close();
        return classlist;
    }

    //getting all subjects from database
    public List<String> getAllmatieres()
    {
        List<String> matierelist=new ArrayList<>();
        //get readable database
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT DESC_mat FROM matiere_table",null);
        if(cursor.moveToFirst())
        {
            do {
                matierelist.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the database
        db.close();
        return matierelist;
    }


    //insertion des seances
    public boolean insertSession(String classname,String subject,String time,String teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CLASSNAME",classname);
        contentValues.put("SUBJECT",subject);
        contentValues.put("TIME",time);
        contentValues.put("TEACHER",teacher);
        long result = db.insert(TABLE_NAME2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
//insertion des absences
    public boolean insertHistoryStudents(String studentName,String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("STUDENTNAME",studentName);
        contentValues.put("SUBJECT",subject);
        long result = db.insert(TABLE_NAME4,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



//insertion des notes

    public boolean insertNote(String DS,String Examen,String subject,String studentName,String moyenne) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SUBJECT",subject);
        contentValues.put("DS",DS);
        contentValues.put("EXAMEN",Examen);
        contentValues.put("studentName",studentName);
        contentValues.put("MOYENNE",moyenne);
        long result = db.insert(TABLE_NAME3,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //login
    public boolean checkUserExist(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = {"username"};

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME5, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }



//lire les donnees de la base en passant le nom de table en parametre
    public Cursor getAllData(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    //lire les absences dans chaque matiere

    public Cursor getEliminations(String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT STUDENTNAME,COUNT(*) FROM history_table WHERE SUBJECT == "
                +" +subject "+" GROUP BY STUDENTNAME ORDER BY 2 Desc";
        Cursor res = db.rawQuery(query,null);
        return res;
    }

//lire liste des etudiants dans une liste
    public ArrayList<Student> getStudents(String className){

        ArrayList<Student> student_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] field = {"ID","FULLNAME","STATUS","CLASSE"};
        String query="SELECT * FROM students_table WHERE CLASS ='"+className+"'";
        Cursor c = db.rawQuery(query,null);

        int id = c.getColumnIndex("ID");
        int fname = c.getColumnIndex("FULLNAME");
        int status = c.getColumnIndex("STATUS");

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String Id = c.getString(id);
            String fullname = c.getString(fname);
            String Status = c.getString(status);
            student_list.add(new Student(Id,fullname,Status));
        }

        return student_list;
    }
//marque de presence
    public boolean updateData(String id,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("STATUS",status);
        db.update(TABLE_NAME1, contentValues, "ID = ?",new String[] { id });
        return true;
    }

//    public boolean insertData(String subject,String DS,String Examen) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("DS",DS);
//        contentValues.put("SUBJECT",subject);
//        contentValues.put("EXAMEN",Examen);
//
//        long result = db.insert("Notes_table",null ,contentValues);
//        if(result == -1)
//            return false;
//        else
//            return true;
//    }
}
