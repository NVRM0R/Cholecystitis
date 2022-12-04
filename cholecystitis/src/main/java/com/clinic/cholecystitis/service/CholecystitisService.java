package com.clinic.cholecystitis.service;
import java.util.Iterator;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;

import DB.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.cholecystitis.model.Cholecystitis;

import com.clinic.cholecystitis.model.ethnicityDef;
@Service
public class CholecystitisService {

    DB database;

    CholecystitisService() {
        database = new DB();
    }


    @Autowired
    MessageSource messages;

    public String createInstance(Cholecystitis record, String hospitalName, Locale locale) {
        String responseMessage = null;
        if (record != null) {
            record.setHospitalName(hospitalName);
            int id = database.push(record);
            responseMessage = String.format(messages.getMessage("record.create.message", null, locale), record.getRecordID());
        }
        return responseMessage;
    }

    public void fillDummy() {
        database.push(new Cholecystitis(ethnicityDef.Latino, 30, false, 1F, 1.2F, 2.2F, 3.3F, 4.4F, 5.5F, 5.5F, "HUS",1));
        database.push(new Cholecystitis(ethnicityDef.AfricanAmerican, 45, true, 5.3F, 6.34F, 1.49F, 3.29F, 1.54F, 8.18F, 2.8F, "HUS",1));
        database.push(new Cholecystitis(ethnicityDef.Asian, 70, false, 8.27F, 4.85F, 0.85F, 4.54F, 3.12F, 5.06F, 4.68F, "HUS",2));
        database.push(new Cholecystitis(ethnicityDef.White, 56, true, 3.47F, 8.08F, 5.34F, 7.05F, 2.73F, 7.57F, 9.59F, "HUS",3));
    }


    public String serialize(int id,String hospitalName) {
        String output = "";
        Iterator<Cholecystitis> iter = database.getIter();
        while (iter.hasNext()) {
            Cholecystitis tmp = iter.next();
            if (id == tmp.getPatientID() && hospitalName.equals(tmp.getHospitalName())) {
                if (output.length() > 1)
                    output += ";";
                output += tmp.toString();
            }
        }
        if(output.length()==0)
            output = "empty";
        return output;
    }

    public Cholecystitis getById(int id){
        Iterator<Cholecystitis> iter = database.getIter();
        Cholecystitis output = null;
        while (iter.hasNext()) {
            Cholecystitis tmp = iter.next();
            if (tmp.getRecordID() == id) {
                output = tmp;
                break;
            }
        }
        return output;
    }
    public String delete(int id, String hospitalName, Locale locale){
        String responseMessage = null;
        boolean deleted = false;
        Iterator<Cholecystitis> iter = database.getIter();
        while (iter.hasNext()) {
            Cholecystitis tmp = iter.next();
            if(tmp.getRecordID()==id) {
                database.remove(tmp);
                deleted = true;
                break;
            }
        }
        if(deleted)
            responseMessage = String.format(messages.getMessage("record.delete.message", null, locale), id);
        else
            responseMessage = String.format(messages.getMessage("record.delete.message.not.found", null, locale), id);
        return responseMessage;
    }
}