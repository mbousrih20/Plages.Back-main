package com.example.Plages.Services.Pays;

import com.example.Plages.DALs.PaysRepositories.PaysRepository;
import com.example.Plages.Models.Pays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaysService implements IPaysService {

    private final PaysRepository PaysRepository;

    @Autowired
    public PaysService(PaysRepository pr) {
        this.PaysRepository = pr;
    }

    @Override
    public Optional<Pays> GetPaysById(Long id) {
        return this.PaysRepository.findById(id);
    }

    @Override
    public List<Pays> GetAllPays() {
        return this.PaysRepository.findAll();
    }

    @Override
    public void DeletePays(Long id) {
        this.PaysRepository.deleteById(id);
    }

    @Override
    public Pays AddPays(Pays pays) {
        return this.PaysRepository.save(pays);
    }

}
