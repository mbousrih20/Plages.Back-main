package com.example.Plages.Services.Concession;

import com.example.Plages.Models.Concession;

import java.util.List;
import java.util.Optional;

public interface IConcessionService {

    public Optional<Concession> GetPlageById(Long id);

    public List<Concession> GetAllPlages();

    public void DeletePlage(Long id);

    public Concession AddPlage(Concession concession);
    public Optional<Concession> GetConcessionByConsionnaireId(Long Id);
}