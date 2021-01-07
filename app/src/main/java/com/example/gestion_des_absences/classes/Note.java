package com.example.gestion_des_absences.classes;

public class Note {
    private String studentName;
    private String subject;
    private String DS;
    private String Examen;
    private String Moyenne;

    public Note() {
    }

    public Note(String studentName, String subject,String DS,String Examen,String moyenne) {
        this.studentName=studentName;
        this.subject = subject;
        this.DS =DS;
        this.Examen = Examen;
        this.Moyenne = moyenne;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public String getDS() {
        return DS;
    }

    public String getExamen() {
        return Examen;
    }

    public String getMoyenne() {
        return Moyenne;
    }
}