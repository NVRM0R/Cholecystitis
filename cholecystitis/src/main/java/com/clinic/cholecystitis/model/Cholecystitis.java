package com.clinic.cholecystitis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
@ToString
public class Cholecystitis extends RepresentationModel<Cholecystitis> {

    private int recordID;
    private int patientID;
    private String hospitalName;

    private ethnicityDef ethnicity;
    private int age;
    private boolean sex;
    private float cholesterol;
    private float WBC, NE, LY, MO, EO, BA;

    public Cholecystitis(ethnicityDef ethnicity, int age, boolean sex, float cholesterol, float WBC, float NE,
                         float LY, float MO, float EO, float BA, String hospitalName,int patientID){
        this.age = age;
        this.ethnicity = ethnicity;
        this.sex = sex;
        this.cholesterol = cholesterol;
        this.WBC = WBC;
        this.NE = NE;
        this.LY = LY;
        this.MO = MO;
        this.EO = EO;
        this.BA = BA;
        this.hospitalName = hospitalName;
        this.patientID = patientID;
    }
}