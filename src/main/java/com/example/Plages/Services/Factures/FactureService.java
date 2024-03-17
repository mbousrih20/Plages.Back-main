package com.example.Plages.Services.Factures;

import com.example.Plages.DALs.FactureRepositories.FactureRepository;
import com.example.Plages.Models.Facture;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService implements IFactureService {

    @Autowired
    private final FactureRepository FactureRepository;

    public FactureService(FactureRepository fr) {
        this.FactureRepository = fr;
    }



    @Override
    public Facture AddFacture(Facture facture) {
        return this.FactureRepository.save(facture);
    }

    @Override
    public void DeleteFacture(Long factureId) {
        this.FactureRepository.deleteById(factureId);
    }

    @Override
    public List<Facture> GetAllFactures() {
        return this.FactureRepository.findAll();
    }

    @Override
    public Optional<Facture> GetFactureById(Long Id) {
        return this.FactureRepository.findById(Id);
    }
}
