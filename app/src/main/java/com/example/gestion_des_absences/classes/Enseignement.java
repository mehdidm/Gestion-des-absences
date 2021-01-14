package com.example.gestion_des_absences.classes;

public class Enseignement {

    private String subject;
    private String Time;
    private String classname;
    private String enseignant;



    public Enseignement(String subject, String Time, String classname,String enseignant) {
        this.subject = subject;
        this.Time = Time;
        this.classname = classname;
        this.enseignant=enseignant;
    }

    public String getSubject() {
        return subject;
    }

    public String getStartTime() {
        return Time;
    }

    public String getClassname() {
        return classname;
    }

    public String getEnseignant() {
        return enseignant;
    }
}
