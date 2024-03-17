package com.example.Plages.Controllers;

import com.example.Plages.Models.Parasole;
import com.example.Plages.Models.Reservation;
import com.example.Plages.Services.Parasoles.IParasoleService;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/parasoles")
public class ParasoleController {

    private final IParasoleService ParasoleService;

    @Autowired
    public ParasoleController(IParasoleService parasoleService) {
        this.ParasoleService = parasoleService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Parasole>> getAllParasoles() {
        List<Parasole> parasoles = ParasoleService.GetAllParasoles();
        return new ResponseEntity<>(parasoles, HttpStatus.OK);
    }
    @GetMapping("/getParasoleByFileId/{id}")
    public ResponseEntity<List<Parasole>> getAllReservationsByClient(@PathVariable("id") Long id) {

        List<Parasole> parasoles = ParasoleService.findByFileId(id);
        return new ResponseEntity<>(parasoles, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<Parasole> getParasoleById(@PathVariable("Id") Long Id) {
        Optional<Parasole> parasoleOptional = ParasoleService.GetParasoleById(Id);
        return parasoleOptional.map(parasole -> new ResponseEntity<>(parasole, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getbynumemplacement/{Id}")
    public ResponseEntity<Parasole> getParasoleByNumEmplacement(@PathVariable("Id") int Id) {
        Optional<Parasole> parasoleOptional = ParasoleService.GetParasoleByNumEmplacement(Id);
        return parasoleOptional.map(parasole -> new ResponseEntity<>(parasole, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/")
    public ResponseEntity<Parasole> createParasole(@RequestBody Parasole parasole) {
        Parasole createdParasole = ParasoleService.AddParasole(parasole);
        return new ResponseEntity<>(createdParasole, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteParasole(@PathVariable("Id") Long Id) {
        ParasoleService.DeleteParasole(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}