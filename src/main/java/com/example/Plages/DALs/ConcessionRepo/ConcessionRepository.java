package com.example.Plages.DALs.ConcessionRepo;

import com.example.Plages.Models.Concession;
import com.example.Plages.Models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcessionRepository extends JpaRepository<Concession, Long>  {
public Optional<Concession> findByconcessionnaireId(Long concessionnaireId);
}