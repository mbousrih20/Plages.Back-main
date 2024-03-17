package com.example.Plages.Services.Concession;

import com.example.Plages.DALs.ConcessionRepo.ConcessionRepository;
import com.example.Plages.DALs.PlageRepositories.PlageRepository;
import com.example.Plages.Models.Concession;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConcessionService implements IConcessionService {
    private final PlageRepository PlageRepository;
    public final ConcessionRepository cn ;
    
    @Autowired
    public ConcessionService(PlageRepository pr,ConcessionRepository crp){
        this.PlageRepository = pr; this.cn = crp;
    }

    @Override
    public Optional<Concession> GetPlageById(Long id) {
        return this.PlageRepository.findById(id);
    }

    @Override
    public List<Concession> GetAllPlages() {
        return this.PlageRepository.findAll();
    }

    @Override
    public void DeletePlage(Long id) {
        this.PlageRepository.deleteById(id);
    }

    @Override
    public Concession AddPlage(Concession concession) {
        return this.PlageRepository.save(concession);
    }
    @Override
    public Optional<Concession> GetConcessionByConsionnaireId(Long Id) {
        return this.cn.findByconcessionnaireId(Id);
    }
}