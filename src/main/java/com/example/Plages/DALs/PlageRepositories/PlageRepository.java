package com.example.Plages.DALs.PlageRepositories;

import com.example.Plages.Models.Concession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlageRepository extends JpaRepository<Concession, Long>{
    
}