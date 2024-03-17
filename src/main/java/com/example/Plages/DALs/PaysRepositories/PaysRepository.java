package com.example.Plages.DALs.PaysRepositories;

import com.example.Plages.Models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long>{
    
}