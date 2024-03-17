package com.example.Plages.Services.Parasoles;

import com.example.Plages.Models.Parasole;
import java.util.List;
import java.util.Optional;

public interface IParasoleService {
    
    public Optional<Parasole> GetParasoleById(Long id);
    public Optional<Parasole> GetParasoleByNumEmplacement(int numEmplacement);
    public void DeleteParasole(Long id);
    public List<Parasole> GetAllParasoles();
    public Parasole AddParasole(Parasole parasole);
    public List<Parasole> findByFileId(Long fileId);
}