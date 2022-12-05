package com.clinic.cholecystitis.controller;

import com.clinic.cholecystitis.model.Cholecystitis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.clinic.cholecystitis.service.CholecystitisService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@RestController
@RequestMapping("/{hospitalName}/")
public class CholecystitisController {

    @Autowired
    private CholecystitisService CholecystitisService;
    @Autowired
    MessageSource messages;
    @GetMapping(value="/test")
    public ResponseEntity<String> test(
            @PathVariable("hospitalName") String hospitalName){
        return ResponseEntity.ok("Welcome to "+hospitalName);
    }

    @GetMapping(value="/fillSomething")
    public ResponseEntity<String> fillData(
            @PathVariable("hospitalName") String hospitalName,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale){
        CholecystitisService.fillDummy(hospitalName,locale);
        return ResponseEntity.ok("\uD83D\uDC4C");
    }

    // Все записи пациента
    @GetMapping(value="/allRecords/{patientID}")
    public ResponseEntity<String> viewOtherRecords(
            @PathVariable("hospitalName") String hospitalName,
            @PathVariable("patientID") int patientID){
        return ResponseEntity.ok(CholecystitisService.serialize(patientID,hospitalName));
    }

    // Все записи пациента
    @PutMapping(value="/edit/{recordID}")
    public ResponseEntity<String> editRecord(
            @PathVariable("hospitalName") String hospitalName,
            @RequestBody HashMap<String, String> record,
            @PathVariable("recordID") int recordID){
        System.out.println("HERE");
        return ResponseEntity.ok(CholecystitisService.edit(recordID,record.get("field"),record.get("value")));
    }

    @PostMapping
    public ResponseEntity<String> createRecord(
            @PathVariable("hospitalName") String hospitalName,
            @RequestBody Cholecystitis request,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        return ResponseEntity.ok(CholecystitisService.createInstance(request, hospitalName, locale));
    }

    @GetMapping(value="/record/{id}")
    public ResponseEntity<Cholecystitis> getRecord(
            @PathVariable("hospitalName") String hospitalName,
            @PathVariable("id") int id,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        System.out.println("GET: "+id);
        Cholecystitis record = CholecystitisService.getById(id,hospitalName);
        record.add(linkTo(methodOn(CholecystitisController.class)
                        .getRecord(hospitalName, id,null))
                        .withSelfRel(),
                linkTo(methodOn(CholecystitisController.class)
                        .viewOtherRecords(hospitalName, id))
                        .withRel(messages.getMessage("record.get.all", null, locale)),
                linkTo(methodOn(CholecystitisController.class)
                        .delRecord(hospitalName, id, null))
                        .withRel(messages.getMessage("record.delete.id", null, locale)));
        return ResponseEntity.ok(record);
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> delRecord(
            @PathVariable("hospitalName") String hospitalName,
            @PathVariable("id") int id,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        System.out.println("DELETE: "+id);
        return ResponseEntity.ok(CholecystitisService.delete(id, hospitalName, locale));
    }
}
