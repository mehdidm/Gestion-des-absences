package com.example.gestion_des_absences.classes;

public class History {

    private String subject;
    private String date;
    private String classeName;

    public History() {
    }

    public History(String subject, String date, String classeName) {
        this.subject = subject;
        this.date = date;
        this.classeName = classeName;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getClasseName() {
        return classeName;
    }
}