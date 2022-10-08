package com.clinic.cholecystitis.service;
import java.util.Iterator;

import DB.DB;
import org.springframework.stereotype.Service;

import com.clinic.cholecystitis.model.Cholecystitis;

import com.clinic.cholecystitis.model.ethnicityDef;
@Service
public class CholecystitisService {

    DB database;
    CholecystitisService(){
        database = new DB();
    }

    public String createInstance(Cholecystitis record,String hospitalName){
        record.setHospitalName(hospitalName);
        int id = database.push(record);
        return String.valueOf(id);
    }

    public void fillDummy(){
        database.push(new Cholecystitis(ethnicityDef.Latino,30, false, 1F,1.2F,2.2F,3.3F,4.4F,5.5F,5.5F,"HUS"));
        database.push(new Cholecystitis(ethnicityDef.AfricanAmerican,45, true, 5.3F,6.34F,1.49F,3.29F,1.54F,8.18F,2.8F,"HUS"));
        database.push(new Cholecystitis(ethnicityDef.Asian,70,false , 8.27F,4.85F,0.85F,4.54F,3.12F,5.06F,4.68F,"HUS"));
        database.push(new Cholecystitis(ethnicityDef.White,56, true, 3.47F,8.08F,5.34F,7.05F,2.73F,7.57F,9.59F,"HUS"));
    }
    public String serialize(int id){
        String output = "";
        Iterator<Cholecystitis> iter = database.getIter();
        while (iter.hasNext()) {
            Cholecystitis tmp = iter.next();
            if(id==-1){
                if(output.length()>1)
                    output+=";";
                output+=tmp.toString();
            }
            else
                if(tmp.getId()==id) {
                    output = tmp.toString();
                    break;
                }
        }
        return output;
    }
    public boolean delete(int id){
        boolean res = false;
        Iterator<Cholecystitis> iter = database.getIter();
        while (iter.hasNext()) {
            Cholecystitis tmp = iter.next();
            if(tmp.getId()==id) {
                database.remove(tmp);
                res = true;
            break;
            }
        }
        return res;
    }
}