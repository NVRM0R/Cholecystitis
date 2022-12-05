package com.clinic.cholecystitis.repository;

import com.clinic.cholecystitis.model.Cholecystitis;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
@Repository
public interface CholecystitisRepository extends CrudRepository<Cholecystitis,String>{
    public Cholecystitis findByRecordid(int patientid);
    public Cholecystitis findByHospitalnameAndRecordid(String hospitelname,int patientid);
    public List<Cholecystitis> findByPatientid(int patientid);
}