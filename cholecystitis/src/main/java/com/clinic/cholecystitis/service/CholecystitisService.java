package com.clinic.cholecystitis.service;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.clinic.cholecystitis.config.ServiceConfig;
import com.clinic.cholecystitis.repository.CholecystitisRepository;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.cholecystitis.model.Cholecystitis;

import com.clinic.cholecystitis.model.ethnicityDef;
@Service
public class CholecystitisService {


    @Autowired
    MessageSource messages;
    @Autowired
    private CholecystitisRepository cholecystitisRepository;
    @Autowired
    ServiceConfig config;
    public Cholecystitis createInstance(Cholecystitis cholecystitis, String hospitalname, Locale locale) {
        if (cholecystitis != null) {
            cholecystitis.setHospitalname(hospitalname);
            cholecystitisRepository.save(cholecystitis);
        }
        return cholecystitis.withComment(config.getProperty());
    }

    public void fillDummy(String hospitalname,Locale locale) {
        createInstance(new Cholecystitis(ethnicityDef.Latino, 30, false, 1F, 1.2F, 2.2F, 3.3F, 4.4F, 5.5F, 5.5F, null,1),  hospitalname,locale);
        createInstance(new Cholecystitis(ethnicityDef.AfricanAmerican, 45, true, 5.3F, 6.34F, 1.49F, 3.29F, 1.54F, 8.18F, 2.8F, null,1),  hospitalname,locale);
        createInstance(new Cholecystitis(ethnicityDef.Asian, 70, false, 8.27F, 4.85F, 0.85F, 4.54F, 3.12F, 5.06F, 4.68F,  null,1),  hospitalname,locale);
        createInstance(new Cholecystitis(ethnicityDef.White, 56, true, 3.47F, 8.08F, 5.34F, 7.05F, 2.73F, 7.57F, 9.59F,  null,1),  hospitalname,locale);
    }


    public String serialize(int id,String hospitalname) {
        String output = "";
        List<Cholecystitis> result = cholecystitisRepository.findByPatientid(id);
        for (Cholecystitis record : result){
            if (id == record.getPatientid() && hospitalname.equals(record.getHospitalname())) {
                if (output.length() > 1)
                    output += ";";
                output += record.toString();
            }
        }
        if(output.length()==0)
            output = "empty";
        return output;
    }

    public Cholecystitis getById(int recordid,String hospitalname,Locale locale) {
        Cholecystitis record = cholecystitisRepository.findByRecordid(recordid);
        if (null == record) {
            throw new IllegalArgumentException(String.format(messages.getMessage("search.error.message", null, locale), hospitalname,recordid));
        }
        return record.withComment(config.getProperty());
    }

    public String delete(int RecordID, String hospitalname, Locale locale){
        String responseMessage = null;
        Cholecystitis record = new Cholecystitis();
        record.setHospitalname(hospitalname);
        record.setRecordid(RecordID);
        cholecystitisRepository.delete(record);

        responseMessage = String.format(messages.getMessage("record.delete.message", null, locale), RecordID);
        return responseMessage;
    }

    public String edit(int recordID,String field,String value,Locale locale){
        Cholecystitis record;
        try {
            record = getById(recordID, null,locale);

        switch (field){
            case "patientid": record.setPatientid(Integer.parseInt(value));
                break;
            case "hospitalname": record.setHospitalname(value);
                break;
            case "ethicity": record.setEthnicity(ethnicityDef.valueOf(value));
                break;
            case "age": record.setAge(Integer.parseInt(value));
                break;
            case "sex": record.setSex(Boolean.parseBoolean(value));
                break;
            case "cholesterol": record.setCholesterol(Float.parseFloat(value));
                break;
            case "wbc": record.setWbc(Float.parseFloat(value));
                break;
            case "ne": record.setNe(Float.parseFloat(value));
                break;
            case "ly": record.setLy(Float.parseFloat(value));
                break;
            case "mo": record.setMo(Float.parseFloat(value));
                break;
            case "eo": record.setEo(Float.parseFloat(value));
                break;
            case "ba": record.setBa(Float.parseFloat(value));
                break;

        }
        cholecystitisRepository.save(record);
        }
        catch(Exception e){
            throw new IllegalArgumentException(String.format(messages.getMessage("record.not.found.message", null, null),recordID));
        }
        return String.format(messages.getMessage("record.update.message",null,locale),record.getRecordid());
    }
}