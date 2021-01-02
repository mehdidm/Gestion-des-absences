package com.example.gestion_des_absences.classes;

public class Session {

    private String subject;
    private String Time;
    private String classname;


    public Session() {

    }

    public Session(String subject, String Time, String classname) {
        this.subject = subject;
        this.Time = Time;
        this.classname = classname;
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
}
