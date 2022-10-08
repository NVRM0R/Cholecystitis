package com.clinic.cholecystitis.controller;

import com.clinic.cholecystitis.model.Cholecystitis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.clinic.cholecystitis.service.CholecystitisService;


@RestController

@RequestMapping("/{hospitalName}/")
public class CholecystitisController {

    @Autowired
    private CholecystitisService CholecystitisService;

    @GetMapping(value="/test")
    public ResponseEntity<String> test(
            @PathVariable("hospitalName") String hospitalName){
        return ResponseEntity.ok("Welcome to "+hospitalName);
    }

    @GetMapping(value="/fillSomething")
    public ResponseEntity<String> fillData(
            @PathVariable("hospitalName") String hospitalName){
        CholecystitisService.fillDummy();
        return ResponseEntity.ok("\uD83D\uDC4C");
    }

    @GetMapping(value="/getList")
    public ResponseEntity<String> getAllData(
            @PathVariable("hospitalName") String hospitalName){
        return ResponseEntity.ok(CholecystitisService.serialize(-1));
    }

    @PostMapping
    public ResponseEntity<String> createRecord(
            @PathVariable("hospitalName") String hospitalName,
            @RequestBody Cholecystitis request) {
        return ResponseEntity.ok(CholecystitisService.createInstance(request, hospitalName));
    }

    @GetMapping
    public ResponseEntity<String> getRecord(
            @PathVariable("hospitalName") String hospitalName,
            @RequestBody String id) {
        int num = Integer.parseInt(id.replaceAll("[^0-9]",""));
        System.out.println("GET: "+num);
        return ResponseEntity.ok(CholecystitisService.serialize(num));
    }
    @DeleteMapping
    public ResponseEntity<String> delRecord(
            @PathVariable("hospitalName") String hospitalName,
            @RequestBody String id) {
        int num = Integer.parseInt(id.replaceAll("[^0-9]",""));
        System.out.println("DELETE: "+num);
        String result = "???";
        if(CholecystitisService.delete(num))
            result = "\uD83D\uDC4C";
        else
            result = "\uD83D\uDE48";
        return ResponseEntity.ok(result);
    }
}