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
    public String createInstance(Cholecystitis record, String hospitalName, Locale locale) {
        String responseMessage = null;
        if (record != null) {
            record.setHospitalName(hospitalName);
            cholecystitisRepository.save(record);
            responseMessage = String.format(messages.getMessage("record.create.message", null, locale));
        }
        return responseMessage;
    }

    public void fillDummy(String hospitalName,Locale locale) {
        createInstance(new Cholecystitis(ethnicityDef.Latino, 30, false, 1F, 1.2F, 2.2F, 3.3F, 4.4F, 5.5F, 5.5F, null,1),  hospitalName,locale);
        createInstance(new Cholecystitis(ethnicityDef.AfricanAmerican, 45, true, 5.3F, 6.34F, 1.49F, 3.29F, 1.54F, 8.18F, 2.8F, null,1),  hospitalName,locale);
        createInstance(new Cholecystitis(ethnicityDef.Asian, 70, false, 8.27F, 4.85F, 0.85F, 4.54F, 3.12F, 5.06F, 4.68F,  null,1),  hospitalName,locale);
        createInstance(new Cholecystitis(ethnicityDef.White, 56, true, 3.47F, 8.08F, 5.34F, 7.05F, 2.73F, 7.57F, 9.59F,  hospitalName,1),  hospitalName,locale);
    }


    public String serialize(int id,String hospitalName) {
        String output = "";
        List<Cholecystitis> result = cholecystitisRepository.selectByPatientID(id);
        for (Cholecystitis record : result){
            if (id == record.getPatientID() && hospitalName.equals(record.getHospitalName())) {
                if (output.length() > 1)
                    output += ";";
                output += record.toString();
            }
        }
        if(output.length()==0)
            output = "empty";
        return output;
    }

    public Cholecystitis getById(int id,String hospitalName){
        Cholecystitis record = cholecystitisRepository.findByHospitalNameAndPatientID(hospitalName,id);
        if(record == null)
            throw new IllegalArgumentException(String.format(messages.getMessage("search.error.message",null,null),hospitalName,id));
        return record;
    }
    public String delete(int RecordID, String hospitalName, Locale locale){
        String responseMessage = null;
        Cholecystitis record = new Cholecystitis();
        record.setHospitalName(hospitalName);
        record.setRecordID(RecordID);
        cholecystitisRepository.delete(record);

        responseMessage = String.format(messages.getMessage("record.delete.message", null, locale), RecordID);
        return responseMessage;
    }

    public String edit(int recordID,String field,String value){
        return String.format("Selected field: [%s] and desired value [%s]",field,value);
    }
}