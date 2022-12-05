package com.clinic.cholecystitis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name="records")
public class Cholecystitis extends RepresentationModel<Cholecystitis> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordid;
    @Column(name="patientid",nullable = false)
    private int patientid;
    @Column(name="hospitalname",nullable = false)
    private String hospitalname;
    @Column(name="ethnicity",nullable = false)
    private ethnicityDef ethnicity;
    @Column(name="age",nullable = false)
    private int age;
    @Column(name="sex",nullable = false)
    private boolean sex;
    @Column(name="cholesterol",nullable = false)
    private float cholesterol;
    @Column(name="wbc",nullable = false)
    private float wbc;
    @Column(name="ne",nullable = false)
    private float ne;
    @Column(name="ly",nullable = false)
    private float ly;
    @Column(name="mo",nullable = false)
    private float mo;
    @Column(name="eo",nullable = false)
    private float eo;
    @Column(name="ba",nullable = false)
    private float ba;

    private String comment;
    public Cholecystitis withComment(String comment){
        this.setComment(comment);
        return this;
    }
    public Cholecystitis(){}
    public Cholecystitis(ethnicityDef ethnicity, int age, boolean sex, float cholesterol, float wbc, float ne,
                         float ly, float mo, float eo, float ba, String hospitalname,int patientid){
        this.age = age;
        this.ethnicity = ethnicity;
        this.sex = sex;
        this.cholesterol = cholesterol;
        this.wbc = wbc;
        this.ne = ne;
        this.ly = ly;
        this.mo = mo;
        this.eo = eo;
        this.ba = ba;
        this.hospitalname = hospitalname;
        this.patientid = patientid;
    }
}