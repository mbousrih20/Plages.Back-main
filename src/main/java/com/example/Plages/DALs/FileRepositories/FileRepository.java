package com.example.Plages.DALs.FileRepositories;

import com.example.Plages.Models.File;
import com.example.Plages.Models.Parasole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
    public List<File> findByConcessionId(Long concessionId);
}
