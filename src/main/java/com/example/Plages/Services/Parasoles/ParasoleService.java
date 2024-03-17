package com.example.Plages.Services.Parasoles;

import com.example.Plages.DALs.ParasoleRepositories.ParasoleRepository;
import com.example.Plages.Models.Parasole;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParasoleService implements IParasoleService {

    private final ParasoleRepository ParasoleRepository;

    @Autowired
    public ParasoleService(ParasoleRepository pr) {
        this.ParasoleRepository = pr;
    }

    @Override
    public Optional<Parasole> GetParasoleById(Long id) {
        return this.ParasoleRepository.findById(id);
    }

    @Override
    public Optional<Parasole> GetParasoleByNumEmplacement(int numEmplacement) {
        return this.ParasoleRepository.findByNumEmplacement(numEmplacement);
    }
@Override
public List<Parasole> findByFileId(Long fileId) {
        return this.ParasoleRepository.findByFileId(fileId);
}
    @Override
    public void DeleteParasole(Long id) {
        this.ParasoleRepository.deleteById(id);
    }

    @Override
    public List<Parasole> GetAllParasoles() {
        return this.ParasoleRepository.findAll();
    }

    @Override
    public Parasole AddParasole(Parasole parasole) {
        return this.ParasoleRepository.save(parasole);
    }

}
