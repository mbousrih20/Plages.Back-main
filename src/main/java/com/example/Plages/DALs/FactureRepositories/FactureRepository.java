package com.example.Plages.DALs.FactureRepositories;

import com.example.Plages.Models.Facture;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{
}