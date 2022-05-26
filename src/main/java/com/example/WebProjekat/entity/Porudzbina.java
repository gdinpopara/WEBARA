package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Porudzbina {


    public Porudzbina(){}

//    public Porudzbina(UUID id, Dostavljac dostavljac, Kupac kupac, Set<PoruceniArtikli> poruceniArtikli, Restoran restoranPoruceno, Date datumIVremePorudzbine, double cena, String kupacIme, Status status) {
//        this.id = id;
//        this.dostavljac = dostavljac;
//        this.kupac = kupac;
//        this.poruceniArtikli = poruceniArtikli;
//        this.restoranPoruceno = restoranPoruceno;
//        this.datumIVremePorudzbine = datumIVremePorudzbine;
//        this.cena = cena;
//        this.kupacIme = kupacIme;
//        this.status = status;
//        this.ukupnaCena = 0;
//    }

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    private Dostavljac dostavljac;

    @ManyToOne
    private Kupac kupac;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();


//    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Artikal> artikli = new HashSet<>();

    @OneToOne
    private Restoran restoranPoruceno;

    @Column
    private Date datumIVremePorudzbine;

    @Column
    private String kupacIme;

    @Column
    private Status status;

    @Column
    private double ukupnaCena;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


//    public void dodajPoruceniArtikal(PoruceniArtikli pa)
//    {
//        poruceniArtikli.add(pa);
//    }
//
    public Set<PoruceniArtikli> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<PoruceniArtikli> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }


    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

//    public Set<Artikal> getArtikli() {
//        return artikli;
//    }
//
//    public void setArtikli(Set<Artikal> artikli) {
//        this.artikli = artikli;
//    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
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