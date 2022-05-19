package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

;

@Entity
public class Porudzbina {
    public Porudzbina(Dostavljac dostavljac, Set<Artikal> poruceniArtikli, Kupac kupac, Restoran restoranPoruceno, Date datumIVremePorudzbine, double cena, String kupacIme, Status status) {
        this.dostavljac = dostavljac;
        this.poruceniArtikli = poruceniArtikli;
        this.kupac = kupac;
        this.restoranPoruceno = restoranPoruceno;
        this.datumIVremePorudzbine = datumIVremePorudzbine;
        this.cena = cena;
        this.kupacIme = kupacIme;
        this.status = status;
    }

    public Porudzbina(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UUID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dostavljac dostavljac;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

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

    public long getUUID() {
        return UUID;
    }

    public void setUUID(long UUID) {
        this.UUID = UUID;
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
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