package com.example.Plages.DALs.LienParenteRepo;

import com.example.Plages.Models.LienParente;
import com.example.Plages.Models.Parasole;
import com.example.Plages.Models.TypeParente;
import com.example.Plages.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LienParenteRepository extends JpaRepository<LienParente, Long> {
    Optional<LienParente> findTypeParenteByClientIdAndConcessionaireId(Long ClientId, Long ConcessionaireId);
}
