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
@RequestMapping("/{hospitalname}/")
public class CholecystitisController {

    @Autowired
    private CholecystitisService CholecystitisService;
    @Autowired
    MessageSource messages;
    @GetMapping(value="/test")
    public ResponseEntity<String> test(
            @PathVariable("hospitalname") String hospitalname){
        return ResponseEntity.ok("Welcome to "+hospitalname);
    }

    @GetMapping(value="/fillSomething")
    public ResponseEntity<String> fillData(
            @PathVariable("hospitalname") String hospitalname,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale){
        CholecystitisService.fillDummy(hospitalname,locale);
        return ResponseEntity.ok("\uD83D\uDC4C");
    }

    // Все записи пациента
    @GetMapping(value="/allRecords/{patientid}")
    public ResponseEntity<String> viewOtherRecords(
            @PathVariable("hospitalname") String hospitalname,
            @PathVariable("patientid") int patientid){
        return ResponseEntity.ok(CholecystitisService.serialize(patientid,hospitalname));
    }

    // Все записи пациента
    @PutMapping(value="/edit/{recordID}")
    public ResponseEntity<String> editRecord(
            @PathVariable("hospitalname") String hospitalname,
            @RequestBody HashMap<String, String> record,
            @PathVariable("recordID") int recordID,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale){
        try {
            return ResponseEntity.ok(CholecystitisService.edit(recordID, record.get("field"), record.get("value"), locale));
        }
        catch(Exception e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
        }

    @PostMapping
    public ResponseEntity<String> createRecord(
            @PathVariable("hospitalname") String hospitalname,
            @RequestBody Cholecystitis request,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        CholecystitisService.createInstance(request, hospitalname, locale);
        return ResponseEntity.ok(messages.getMessage("record.create.message",null,locale));
    }

    @GetMapping(value="/record/{id}")
    public ResponseEntity<Object> getRecord(
            @PathVariable("hospitalname") String hospitalname,
            @PathVariable("id") int id,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        System.out.println("GET: "+id);
        System.out.println("TEST\n\n"+locale);
        try {
            Cholecystitis record = CholecystitisService.getById(id, hospitalname,locale);
            record.add(linkTo(methodOn(CholecystitisController.class)
                            .getRecord(hospitalname, id, null))
                            .withSelfRel(),
                    linkTo(methodOn(CholecystitisController.class)
                            .viewOtherRecords(hospitalname, id))
                            .withRel(messages.getMessage("record.get.all", null, locale)),
                    linkTo(methodOn(CholecystitisController.class)
                            .delRecord(hospitalname, id, locale))
                            .withRel(messages.getMessage("record.delete.id", null, locale)));
            return ResponseEntity.ok(record);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body(String.format(messages.getMessage("search.error.message",null,locale),hospitalname,id));
        }
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> delRecord(
            @PathVariable("hospitalname") String hospitalname,
            @PathVariable("id") int id,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale) {
        System.out.println("DELETE: "+id);
        return ResponseEntity.ok(CholecystitisService.delete(id, hospitalname, locale));
    }
}
