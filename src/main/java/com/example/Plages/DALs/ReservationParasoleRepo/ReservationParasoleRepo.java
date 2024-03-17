package com.example.Plages.DALs.ReservationParasoleRepo;

import com.example.Plages.Models.Reservation;
import com.example.Plages.Models.ReservationParasole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationParasoleRepo extends JpaRepository<ReservationParasole, Long> {
}
