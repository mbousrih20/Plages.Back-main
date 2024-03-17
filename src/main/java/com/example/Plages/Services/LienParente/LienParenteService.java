package com.example.Plages.Services.LienParente;

import com.example.Plages.DALs.FileRepositories.FileRepository;
import com.example.Plages.DALs.LienParenteRepo.LienParenteRepository;
import com.example.Plages.Models.LienParente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LienParenteService implements ILienParenteService{

    @Autowired
    private final LienParenteRepository lienParenteRepository;

    public LienParenteService(LienParenteRepository fr) {
        this.lienParenteRepository = fr;
    }
    @Override
    public LienParente AddLienParente(LienParente lienParente) {
        return this.lienParenteRepository.save(lienParente) ;
    }

    @Override
    public void DeleteLienParente(Long LienParenteId) {
         this.lienParenteRepository.deleteById(LienParenteId);
    }

    @Override
    public List<LienParente> GetAllLienParente() {
        return this.lienParenteRepository.findAll();
    }

    @Override
    public Optional<LienParente> GetLienParenteById(Long Id) {
        return this.lienParenteRepository.findById(Id);
    }
}
