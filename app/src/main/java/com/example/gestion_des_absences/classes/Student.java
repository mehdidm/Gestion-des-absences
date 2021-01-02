package com.example.gestion_des_absences.classes;

public class Student {

    private String ID;
    private String fullname;
    private String  status ;
    private String Ds;
    private String Examen;


    public Student(String ID, String fullname, String status) {
        this.ID = ID;
        this.fullname = fullname;
        this.status = status;

    }



    public String getCIN() {
        return ID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

//    public String getDs() {
//        return Ds;
//    }
//
//    public String getExamen() {
//        return Examen;
//    }
}
