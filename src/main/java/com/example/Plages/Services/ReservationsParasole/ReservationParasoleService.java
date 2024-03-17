package com.example.Plages.Services.ReservationsParasole;

import com.example.Plages.DALs.ReservationParasoleRepo.ReservationParasoleRepo;
import com.example.Plages.Models.ReservationParasole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationParasoleService implements IReservationParasoleService{
    private final ReservationParasoleRepo ReservationParasoleRepo ;
    @Autowired
    public ReservationParasoleService(ReservationParasoleRepo reservationParasoleRepo) {
        ReservationParasoleRepo = reservationParasoleRepo;
    }

    @Override
    public Optional<ReservationParasole> GetReservationParasoleById(Long id) {
        return this.ReservationParasoleRepo.findById(id);
    }

    @Override
    public List<ReservationParasole> GetAllReservationParasole() {
        return this.ReservationParasoleRepo.findAll();
    }

    @Override
    public void DeleteReservationParasole(Long id) {
                 this.ReservationParasoleRepo.deleteById(id);
    }

    @Override
    public ReservationParasole AddReservationParasole(ReservationParasole ReservationParasole) {
        return this.ReservationParasoleRepo.save(ReservationParasole);
    }
}
