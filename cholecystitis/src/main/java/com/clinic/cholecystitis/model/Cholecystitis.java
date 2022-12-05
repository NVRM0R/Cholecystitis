package com.clinic.cholecystitis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@Table(name="Records")
public class Cholecystitis extends RepresentationModel<Cholecystitis> {

    @Id
    @Column(name="recordID",nullable = false)
    private int recordID;
    @Column(name="patientID",nullable = false)
    private int patientID;
    @Column(name="hospitalName",nullable = false)
    private String hospitalName;
    @Column(name="ethnicity",nullable = false)
    private ethnicityDef ethnicity;
    @Column(name="age",nullable = false)
    private int age;
    @Column(name="sex",nullable = false)
    private boolean sex;
    @Column(name="cholecterol",nullable = false)
    private float cholesterol;
    @Column(name="WBC",nullable = false)
    private float WBC;
    @Column(name="NE",nullable = false)
    private float NE;
    @Column(name="LY",nullable = false)
    private float LY;
    @Column(name="MO",nullable = false)
    private float MO;
    @Column(name="EO",nullable = false)
    private float EO;
    @Column(name="BA",nullable = false)
    private float BA;
    public Cholecystitis(){}
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