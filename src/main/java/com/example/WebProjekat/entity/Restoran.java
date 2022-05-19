package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran
{



    @OneToOne
    private Menadzer menadzer;

    @OneToOne(mappedBy = "restoranPoruceno")
    private Porudzbina porudzbina;

    @OneToMany(mappedBy = "restoran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @Id
    private String naziv;

    @Column
    private String tip;

    @OneToMany(mappedBy = "restoran",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Artikal> artikli = new HashSet<>();

    @OneToOne(mappedBy = "restoran")
    private Lokacija lokacija;

//    public Restoran(String naziv, Menadzer menadzer, Porudzbina porudzbina, Komentar komentar, String tip, Set<Artikal> artikli, Lokacija lokacija) {
//        this.naziv = naziv;
//        this.menadzer = menadzer;
//        this.porudzbina = porudzbina;
//        this.komentari = komentar;
//        this.tip = tip;
//        this.artikli = artikli;
//        this.lokacija = lokacija;
//    }


    public Restoran(){}


    public Restoran(Menadzer menadzer, String naziv) {
        this.menadzer = menadzer;
        this.naziv = naziv;
    }

    public Restoran(Menadzer menadzer, Porudzbina porudzbina, Set<Komentar> komentari, String naziv, String tip, Set<Artikal> artikli, Lokacija lokacija) {
        this.menadzer = menadzer;
        this.porudzbina = porudzbina;
        this.komentari = komentari;
        this.naziv = naziv;
        this.tip = tip;
        this.artikli = artikli;
        this.lokacija = lokacija;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
