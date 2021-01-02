package com.example.gestion_des_absences.classes;

public class Note {
    private String studentID;
    private String subject;
    private String DS;
    private String Examen;

    public Note() {
    }

    public Note(String studentID, String subject,String DS,String Examen) {
        this.subject = subject;
        this.DS =DS;
        this.Examen = Examen;
    }

    public String getStudentID() {
        return studentID;
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
}