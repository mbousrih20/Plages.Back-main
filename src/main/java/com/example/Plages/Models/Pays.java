package com.example.Plages.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    public String Code;

    public String Nom;

    @JsonIgnore
    @OneToMany(mappedBy = "Pays", cascade = CascadeType.ALL)
    public List<User> Clients;

    public Pays(String code ,String nom) {
        this.Code = code;
        this.Nom = nom;
    }
}