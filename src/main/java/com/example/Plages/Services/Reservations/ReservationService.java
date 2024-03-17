package com.example.Plages.Services.Reservations;

import com.example.Plages.DALs.LienParenteRepo.LienParenteRepository;
import com.example.Plages.DALs.ReservationRepositories.ReservationRepository;
import com.example.Plages.Models.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository ReservationRepository;
    @Autowired
    private final LienParenteRepository lienParenteRepository;

    @Autowired
    public ReservationService(ReservationRepository rr, LienParenteRepository lp) {
        this.ReservationRepository = rr;
        this.lienParenteRepository = lp;
    }

    @Override
    public Optional<Reservation> GetReservationById(Long id) {
        return this.ReservationRepository.findById(id);
    }

    @Override
    public List<Reservation> GetAllReservations() {
        return this.ReservationRepository.findAll();
    }

    @Override
    public void DeleteReservation(Long id) {
        this.ReservationRepository.deleteById(id);
    }

    @Override
    public Reservation AddReservation(Reservation reservation) {
        return this.ReservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> GetReservationByDateDebut(LocalDate dateDebut) {
        return this.ReservationRepository.findByDateDebut(dateDebut);
    }

    @Override
    public List<Reservation> GetReservationByDateFin(LocalDate dateFin) {
        return this.ReservationRepository.findByDateFin(dateFin);
    }

    @Override
    public Facture CalculeFacture(Reservation reservation) {
        Facture facture = new Facture();

        long nbJoursReservation = ChronoUnit.DAYS.between(reservation.getDateDebut().toInstant(), reservation.getDateFin().toInstant());

        float montantTotal = 0;
        for (ReservationParasole reservationParasole : reservation.getReservationParasoles()) {
            Parasole parasole = reservationParasole.getParasole();
            File file = parasole.getFile();
            long prixJournalier = file.getPrixJournalier();
            montantTotal += prixJournalier * nbJoursReservation;
        }
        Long clientId = reservation.getClient().getId();
        Parasole premierParasole = reservation.getReservationParasoles().get(0).getParasole();
        Long concessionnaireId = premierParasole.getFile().getConcession().getConcessionnaire().getId();
        Optional<LienParente> lienParente = this.lienParenteRepository.findTypeParenteByClientIdAndConcessionaireId(clientId, concessionnaireId);

        if (lienParente.isPresent()) {
            TypeParente parente = lienParente.get().TypeDeparente;
            if (parente == TypeParente.cousins) {
                // Réduction de 25% si cousins
                montantTotal *= 0.75; // Réduction de 25%
            } else if (parente == TypeParente.freres) {
                // Réduction de 50% si freres
                montantTotal *= 0.5; // Réduction de 50%
            } else if (parente == TypeParente.soeurs) {
                // Réduction de 50% si soeurs
                montantTotal *= 0.5; // Réduction de 50%
            } else if (parente == TypeParente.cousines) {
                // Réduction de 25% si cousines
                montantTotal *= 0.75; // Réduction de 25%
            }


            facture.setMontant(montantTotal);

            return facture;
        }

        @Override
        public List<Reservation> GetReservationByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin){
            return this.ReservationRepository.findByDateDebutAndDateFin(dateDebut, dateFin);
        }


        @Override
        public List<Reservation> GetReservationByClient (Long clientId){
            return this.ReservationRepository.findByClientId(clientId);
        }

        @Override
        public Reservation UpdateReservation (Reservation reservation){
            this.ReservationRepository.deleteById(reservation.getId());
            return this.ReservationRepository.save(reservation);
        }
    }


