package com.example.Plages.Services.Files;

import com.example.Plages.DALs.FileRepositories.FileRepository;
import com.example.Plages.Models.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService implements IFileService {

    @Autowired
    private final FileRepository FileRepository;

    public FileService(FileRepository fr) {
        this.FileRepository = fr;
    }

    @Override
    public File AddFile(File file) {
        return this.FileRepository.save(file);
    }

    @Override
    public void DeleteFile(Long fileId) {
        this.FileRepository.deleteById(fileId);
    }

    @Override
    public List<File> GetAllFiles() {
        return this.FileRepository.findAll();
    }

    @Override
    public Optional<File> GetFileById(Long Id) {
        return this.FileRepository.findById(Id);
    }
    @Override
    public List<File> findByConcessionId(Long concessionId) {
        return this.FileRepository.findByConcessionId(concessionId);
    }
}