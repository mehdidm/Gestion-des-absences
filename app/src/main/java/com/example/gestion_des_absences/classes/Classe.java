package com.example.gestion_des_absences.classes;

public class Classe {
    private String Code_class;
    private String desc_class;


    public Classe(String code_class,String desc_class){
        this.Code_class=code_class;
        this.desc_class=desc_class;
    }


    public String getDesc_class() {
        return desc_class;
    }

    public String getCode_class() {
        return Code_class;
    }
}
