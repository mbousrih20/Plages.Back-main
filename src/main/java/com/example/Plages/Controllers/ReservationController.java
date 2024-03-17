package com.example.Plages.Controllers;

import com.example.Plages.DALs.ReservationParasoleRepo.ReservationParasoleRepo;
import com.example.Plages.Models.Facture;
import com.example.Plages.Models.Reservation;
import com.example.Plages.Models.ReservationParasole;
import com.example.Plages.Services.Reservations.IReservationService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.Plages.Services.ReservationsParasole.IReservationParasoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservationService ReservationService;
    private final IReservationParasoleService ReservationParasoleService;
    @Autowired
    public ReservationController(IReservationService reservationService,IReservationParasoleService ReservationParasoleService) {
        this.ReservationService = reservationService;
        this.ReservationParasoleService = ReservationParasoleService ;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Reservation>> getAllReservation() {
        List<Reservation> clients = ReservationService.GetAllReservations();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/getbydatedebutanddatefin/{dateDebut}-{dateFin}")
    public ResponseEntity<List<Reservation>> getReservationsByDateDebutAndDateFin(@PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin) {
        List<Reservation> clients = ReservationService.GetReservationByDateDebutAndDateFin(dateDebut, dateFin);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/getbydatedebut/{dateDebut}")
    public ResponseEntity<List<Reservation>> getAllReservationsByDateDebut(@PathVariable("dateDebut") LocalDate dateDebut) {
        List<Reservation> reservations = ReservationService.GetReservationByDateDebut(dateDebut);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/getbydateFin/{dateFin}")
    public ResponseEntity<List<Reservation>> getAllReservationsByDateFin(@PathVariable("dateFin") LocalDate dateFin) {
        List<Reservation> reservations = ReservationService.GetReservationByDateFin(dateFin);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/getbyclient/{id}")
    public ResponseEntity<List<Reservation>> getAllReservationsByClient(@PathVariable("id") Long id) {

        List<Reservation> reservations = ReservationService.GetReservationByClient(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("Id") Long Id) {
        Optional<Reservation> reservationOptional = ReservationService.GetReservationById(Id);
        return reservationOptional.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = ReservationService.AddReservation(reservation);
        if(reservation.getReservationParasoles() != null ) {
           for (int i = 0; i < reservation.getReservationParasoles().size(); i++) {
            ReservationParasole reservationparasole = reservation.getReservationParasoles().get(i);
           reservationparasole.setReservation(reservation);
             ReservationParasole reservationParasoleCreated = ReservationParasoleService.AddReservationParasole(reservationparasole);
           }
      }
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/update/")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = ReservationService.UpdateReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("Id") Long Id) {
        ReservationService.DeleteReservation(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/calculMontant")
    public ResponseEntity<Facture> calculMontant(@RequestBody Reservation reservation) {
      Facture facture = this.ReservationService.CalculeFacture(reservation);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }
}