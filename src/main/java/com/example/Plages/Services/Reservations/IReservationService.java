package com.example.Plages.Services.Reservations;

import com.example.Plages.Models.Facture;
import com.example.Plages.Models.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservationService {

    public Optional<Reservation> GetReservationById(Long id);

    public List<Reservation> GetAllReservations();

    public void DeleteReservation(Long id);

    public Reservation AddReservation(Reservation reservation);
    
    public List<Reservation> GetReservationByDateDebut(LocalDate dateDebut);
    public List<Reservation> GetReservationByDateFin(LocalDate dateFin);
    public List<Reservation> GetReservationByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
    public List<Reservation> GetReservationByClient(Long clientId);
    
    public Reservation UpdateReservation(Reservation reservation);
    public Facture CalculeFacture(Reservation reservation);
}
