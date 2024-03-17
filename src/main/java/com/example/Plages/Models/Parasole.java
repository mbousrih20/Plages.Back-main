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
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class Parasole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numEmplacement;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = true)
    private File file;

    public Parasole(int numEmplacement,File file) {
        this.numEmplacement = numEmplacement ;
        this.file= file;
    }

}