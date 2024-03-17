package com.example.Plages.Dtos;

import com.example.Plages.Models.Concession;
import com.example.Plages.Models.Pays;
import com.example.Plages.Models.Reservation;
import com.example.Plages.Models.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
public class ClientDto {
    public String username;
    public String Nom;

    public String Prenom;

    public String email;

    public String Password;

    public Long PaysId;


    public String AdresseDeFacturation;

    public int Phone ;

    public Set<String> role ;
}
