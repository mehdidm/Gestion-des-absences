package com.example.gestion_des_absences.classes;

public class History {

    private String subject;
    private String studentName;

    public History() {
    }

    public History(String subject, String studentName) {
        this.subject = subject;
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }


    public String getStudentName() {
        return studentName;
    }
}