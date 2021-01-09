package com.example.gestion_des_absences.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gestion_des_absences.classes.Student;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME1 = "students_table";
    public static final String TABLE_NAME2 = "sessions_table";
    public static final String TABLE_NAME3 = "Notes_table";
    public static final String TABLE_NAME4 = "history_table";
    public static final String TABLE_NAME5 = "teachers_table";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FULLNAME TEXT,STATUS TEXT)");
        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CLASSNAME TEXT,SUBJECT TEXT,TIME TEXT)");
        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DS TEXT,SUBJECT TEXT,EXAMEN TEXT,STUDENTNAME TEXT)");
        db.execSQL("create table " + TABLE_NAME4 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUDENTNAME TEXT,SUBJECT TEXT)");
        db.execSQL("create table " + TABLE_NAME5 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME5);

    }

    public boolean insertSession(String classname,String subject,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CLASSNAME",classname);
        contentValues.put("SUBJECT",subject);
        contentValues.put("TIME",time);
        long result = db.insert(TABLE_NAME2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

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




    public Cursor getAllData(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Cursor getEliminations(String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT STUDENTNAME,COUNT(*) FROM history_table WHERE SUBJECT == "
                +" +subject "+" GROUP BY STUDENTNAME ORDER BY 2 Desc";
        Cursor res = db.rawQuery(query,null);
        return res;
    }


    public ArrayList<Student> getStudents(){

        ArrayList<Student> student_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] field = {"ID","FULLNAME","STATUS"};
        Cursor c = db.query("students_table", field, null, null, null, null, null);

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

    public boolean updateData(String id,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        //contentValues.put("FULLNAME",fullname);
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
