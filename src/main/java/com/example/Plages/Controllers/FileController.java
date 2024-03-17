package com.example.Plages.Controllers;

import com.example.Plages.Models.File;
import com.example.Plages.Models.Parasole;
import com.example.Plages.Services.Files.IFileService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/files")
public class FileController {

    private final IFileService FileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.FileService = fileService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> clients = FileService.GetAllFiles();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<File> getFileById(@PathVariable("Id") Long Id) {
        Optional<File> clientOptional = FileService.GetFileById(Id);
        return clientOptional.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/")
    public ResponseEntity<File> createFile(@RequestBody File file) {
        File createdFile = FileService.AddFile(file);
        return new ResponseEntity<>(createdFile, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteFile(@PathVariable("Id") Long Id) {
        FileService.DeleteFile(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getFilesbyConcessionId/{id}")
    public ResponseEntity<List<File>> GetFilesbyConcessionId(@PathVariable("id") Long id) {

        List<File> files = FileService.findByConcessionId(id);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}