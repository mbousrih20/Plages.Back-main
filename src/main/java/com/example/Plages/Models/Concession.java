package com.example.Plages.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class Concession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    public String Name;

    public String Localisation;

    @JsonIgnore
    @OneToMany(mappedBy = "concession", cascade = CascadeType.ALL)
    public List<File> Files;


    @OneToOne
    @JoinColumn(name = "concessionnaire_id")
    public User concessionnaire;

    public Concession(String Name,String Localisation,User concessionnaire) {
        this.Name = Name ;
        this.Localisation = Localisation ;
        this.concessionnaire = concessionnaire ;
    }
}
