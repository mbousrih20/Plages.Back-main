package com.example.Plages.Controllers;

import com.example.Plages.Models.LienParente;
import java.util.List;
import java.util.Optional;

import com.example.Plages.Services.LienParente.ILienParenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/lienParentes")
public class LienParenteController {

    private final ILienParenteService lienParenteService;

    @Autowired
    public LienParenteController(ILienParenteService lienParenteService) {
        this.lienParenteService = lienParenteService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<LienParente>> getAllLienParentes() {
        List<LienParente> LienParentes = lienParenteService.GetAllLienParente();
        return new ResponseEntity<>(LienParentes, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<LienParente> getLienParenteById(@PathVariable("Id") Long Id) {
        Optional<LienParente> lienParenteOptional = lienParenteService.GetLienParenteById(Id);
        return lienParenteOptional.map(parasole -> new ResponseEntity<>(parasole, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/add/")
    public ResponseEntity<LienParente> createLienParente(@RequestBody LienParente lienParente) {
        LienParente createdLienParente = lienParenteService.AddLienParente(lienParente);
        return new ResponseEntity<>(createdLienParente, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteLienParente(@PathVariable("Id") Long Id) {
        lienParenteService.DeleteLienParente(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}