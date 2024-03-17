package com.example.Plages.Controllers;

import com.example.Plages.Models.Facture;
import com.example.Plages.Services.Factures.IFactureService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/factures")
public class FactureController {

    private final IFactureService FactureService;

    @Autowired
    public FactureController(IFactureService factureService) {
        this.FactureService = factureService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = FactureService.GetAllFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable("Id") Long Id) {
        Optional<Facture> factureOptional = FactureService.GetFactureById(Id);
        return factureOptional.map(facture -> new ResponseEntity<>(facture, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/add/")
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) {
        Facture createdFacture = FactureService.AddFacture(facture);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteFacture(@PathVariable("Id") Long Id) {
        FactureService.DeleteFacture(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
