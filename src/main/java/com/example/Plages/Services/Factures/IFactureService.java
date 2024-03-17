package com.example.Plages.Services.Factures;

import com.example.Plages.Models.Facture;
import java.util.List;
import java.util.Optional;

public interface IFactureService {

    public Facture AddFacture(Facture facture);

    public void DeleteFacture(Long factureId);

    public List<Facture> GetAllFactures();

    public Optional<Facture> GetFactureById(Long Id);
}
