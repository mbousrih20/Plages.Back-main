package com.example.Plages.Services.Pays;

import com.example.Plages.Models.Pays;
import java.util.List;
import java.util.Optional;

public interface IPaysService {

    public Optional<Pays> GetPaysById(Long id);
    public List<Pays> GetAllPays();
    public void DeletePays(Long id);
    public Pays AddPays(Pays pays);
}
