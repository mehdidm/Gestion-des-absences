package com.example.gestion_des_absences.classes;

public class Matiere {

    private String code_mat;
    private String desc_mat;
    private String vol_hr_mat;
    private String coef_mat;


    public Matiere(String code_mat,String desc_mat,String vol_hr_mat,String coef_mat){
        this.code_mat=code_mat;
        this.desc_mat=desc_mat;
        this.vol_hr_mat=vol_hr_mat;
        this.coef_mat=coef_mat;
    }


    public String getCode_mat() {
        return code_mat;
    }

    public String getCoef_mat() {
        return coef_mat;
    }

    public String getDesc_mat() {
        return desc_mat;
    }

    public String getVol_hr_mat() {
        return vol_hr_mat;
    }
}
