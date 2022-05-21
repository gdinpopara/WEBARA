package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

;

@Entity
public class Porudzbina {


    public Porudzbina(){}

    public Porudzbina(UUID id, Dostavljac dostavljac, Set<Artikal> artikli, Kupac kupac, Set<PoruceniArtikli> poruceniArtikli, Restoran restoranPoruceno, Date datumIVremePorudzbine, double cena, String kupacIme, Status status) {
        this.id = id;
        this.dostavljac = dostavljac;
        this.artikli = artikli;
        this.kupac = kupac;
        this.poruceniArtikli = poruceniArtikli;
        this.restoranPoruceno = restoranPoruceno;
        this.datumIVremePorudzbine = datumIVremePorudzbine;
        this.cena = cena;
        this.kupacIme = kupacIme;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dostavljac dostavljac;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Artikal> artikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

    @OneToOne
    private Restoran restoranPoruceno;

    @Column
    private Date datumIVremePorudzbine;

    @Column
    private double cena;

    @Column
    private String kupacIme;

    @Column
    private Status status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public Set<PoruceniArtikli> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<PoruceniArtikli> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Restoran getRestoranPoruceno() {
        return restoranPoruceno;
    }

    public void setRestoranPoruceno(Restoran restoranPoruceno) {
        this.restoranPoruceno = restoranPoruceno;
    }

    public Date getDatumIVremePorudzbine() {
        return datumIVremePorudzbine;
    }

    public void setDatumIVremePorudzbine(Date datumIVremePorudzbine) {
        this.datumIVremePorudzbine = datumIVremePorudzbine;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKupacIme() {
        return kupacIme;
    }

    public void setKupacIme(String kupacIme) {
        this.kupacIme = kupacIme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}