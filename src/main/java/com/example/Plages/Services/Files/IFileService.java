package com.example.Plages.Services.Files;

import com.example.Plages.Models.File;
import java.util.List;
import java.util.Optional;

public interface IFileService {

    public File AddFile(File file);

    public void DeleteFile(Long fileId);

    public List<File> GetAllFiles();

    public Optional<File> GetFileById(Long Id);
    public List<File> findByConcessionId(Long concessionId);
}