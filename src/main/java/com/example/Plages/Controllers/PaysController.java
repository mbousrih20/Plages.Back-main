package com.example.Plages.Controllers;

import com.example.Plages.Models.Pays;
import com.example.Plages.Services.Pays.IPaysService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/pays")
public class PaysController {

    private final IPaysService PaysService;

    @Autowired
    public PaysController(IPaysService fileService) {
        this.PaysService = fileService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Pays>> getAllPays() {
        List<Pays> clients = PaysService.GetAllPays();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Pays> getPaysById(@PathVariable("Id") Long Id) {
        Optional<Pays> clientOptional = PaysService.GetPaysById(Id);
        return clientOptional.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/")
    public ResponseEntity<Pays> createFile(@RequestBody Pays pays) {
        Pays createdPays = PaysService.AddPays(pays);
        return new ResponseEntity<>(createdPays, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deletePays(@PathVariable("Id") Long Id) {
        PaysService.DeletePays(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
