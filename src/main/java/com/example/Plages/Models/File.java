package com.example.Plages.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    public int numero;

    public long PrixJournalier;
    @JsonIgnore
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
    public List<Parasole> Parasoles;

    @ManyToOne
    @JoinColumn(name = "concessionId", nullable = true)
    public Concession concession;

    public File(int numero,long PrixJournalier,Concession concession) {
        this.numero = numero;
        this.PrixJournalier = PrixJournalier;
        this.concession = concession;
    }
}