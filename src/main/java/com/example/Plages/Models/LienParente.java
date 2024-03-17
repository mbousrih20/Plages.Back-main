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
public class LienParente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;
    @Enumerated(EnumType.STRING)
    public TypeParente TypeDeparente;


    @ManyToOne
    @JoinColumn(name = "client_id")
    public User client;

    @ManyToOne
    @JoinColumn(name = "concessionnaire_id")
    public User concessionaire;
}