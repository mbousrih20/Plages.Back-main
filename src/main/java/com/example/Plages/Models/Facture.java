package com.example.Plages.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    public float montant;
    @JsonIgnore
    @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL)
    public Reservation reservation;
}
