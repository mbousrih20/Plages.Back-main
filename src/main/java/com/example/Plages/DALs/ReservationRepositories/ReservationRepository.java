package com.example.Plages.DALs.ReservationRepositories;

import com.example.Plages.Models.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public List<Reservation> findByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);

    public List<Reservation> findByDateDebut(LocalDate dateDebut);

    public List<Reservation> findByDateFin(LocalDate dateFin);

    public List<Reservation> findByClientId(Long clientId);

    public List<Reservation> findByStatut(String statut);
}