package com.example.Plages.DALs.ParasoleRepositories;

import com.example.Plages.Models.Concession;
import com.example.Plages.Models.Parasole;

import java.util.List;
import java.util.Optional;

import com.example.Plages.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParasoleRepository extends JpaRepository<Parasole, Long>{
    public Optional<Parasole> findByNumEmplacement(int numEmplacement);
    public List<Parasole> findByFileId(Long fileId);
}