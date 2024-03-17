package com.example.Plages.Services.LienParente;

import com.example.Plages.Models.File;
import com.example.Plages.Models.LienParente;

import java.util.List;
import java.util.Optional;

public interface ILienParenteService {
    public LienParente AddLienParente(LienParente LienParente);

    public void DeleteLienParente(Long LienParenteId);

    public List<LienParente> GetAllLienParente();

    public Optional<LienParente> GetLienParenteById(Long Id);
}
