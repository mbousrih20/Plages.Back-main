package com.example.Plages.Controllers;

import com.example.Plages.Models.Concession;
import com.example.Plages.Services.Concession.IConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/Concession")
public class ConcessionController {
    private final IConcessionService ConcessionService;

    @Autowired
    public ConcessionController(IConcessionService ConcessionService) {
        this.ConcessionService = ConcessionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Concession>> getAllConcessions() {
        List<Concession> clients = ConcessionService.GetAllPlages();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Concession> getConcessionById(@PathVariable("Id") Long Id) {
        Optional<Concession> clientOptional = ConcessionService.GetPlageById(Id);
        return clientOptional.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/GetConcessionByConsionnaireId/{Id}")
    public ResponseEntity<Concession> GetConcessionByConsionnaireId(@PathVariable("Id") Long Id) {
        Optional<Concession> Concession = ConcessionService.GetConcessionByConsionnaireId(Id);
        return Concession.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/")
    public ResponseEntity<Concession> createConcession(@RequestBody Concession Concession) {
        Concession createdConcession = ConcessionService.AddPlage(Concession);
        return new ResponseEntity<>(createdConcession, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteConcession(@PathVariable("Id") Long Id) {
        ConcessionService.DeletePlage(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
