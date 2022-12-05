package com.clinic.cholecystitis.repository;
import com.clinic.cholecystitis.model.Cholecystitis;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
@Repository
public interface CholecystitisRepository extends CrudRepository<Cholecystitis,String>{
    public List<Cholecystitis> findByhospitalName(String hospitalName);
    public Cholecystitis findByHospitalNameAndPatientID(String hospitalName,int patientID);
    public List<Cholecystitis> selectByPatientID(int PatientID);
}
