package com.example.Plages.Services.ReservationsParasole;

import com.example.Plages.Models.Pays;
import com.example.Plages.Models.ReservationParasole;

import java.util.List;
import java.util.Optional;

public interface IReservationParasoleService {
    public Optional<ReservationParasole> GetReservationParasoleById(Long id);
    public List<ReservationParasole> GetAllReservationParasole();
    public void DeleteReservationParasole(Long id);
    public ReservationParasole AddReservationParasole(ReservationParasole ReservationParasole);
}
