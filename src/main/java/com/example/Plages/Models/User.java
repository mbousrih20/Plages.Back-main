package com.example.Plages.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

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
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;
    private String username;
    public String Nom;

    public String Prenom;

    public String email;

    public String Password;
    @Temporal (TemporalType.DATE)
    private Date creationDate;

    private String token;
    @ManyToOne
    @JoinColumn(name = "pays_id", nullable = true)
    public Pays Pays;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    public List<Reservation> Reservations;

    public String AdresseDeFacturation;
    public String HistoriqueDeTransaction;
    @JsonIgnore
    @OneToOne(mappedBy = "concessionnaire")
    public Concession concession;

    public int Phone ;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;
    @JsonIgnore
    @OneToMany(mappedBy = "client" ,cascade = CascadeType.ALL )
    private List<LienParente> lienParentesByClient ;
    @JsonIgnore
    @OneToMany(mappedBy = "concessionaire" ,cascade = CascadeType.ALL )
    private List<LienParente> lienParentesByConcessionnaire ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public User(String username, String email, String password ) {
        this.username = username;
        this.email = email;
        this.Password = password;
    }
    public User(String username, String email, String password ,String nom ,String prenom,String adresseDeFacturation,int phone,Pays pays) {
        this.username = username;
        this.email = email;
        this.Password = password;
        this.Nom = nom;
        this.Prenom = prenom;
        this.AdresseDeFacturation = adresseDeFacturation;
        this.Phone = phone;
        this.Pays = pays ;
    }
}
