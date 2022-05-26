package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Restoran
{
    //
    @OneToOne
    private Menadzer menadzer;

    @OneToOne(mappedBy = "restoranPoruceno",fetch = FetchType.EAGER)
    @JsonIgnore
    private Porudzbina porudzbina;

    @OneToMany(mappedBy = "restoran", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @Id
    private String naziv;

    @Column
    private String tip;

    @Column
    private boolean radi;

    @OneToMany(mappedBy = "restoran",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
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
        this.radi=true;
    }

    public Restoran(Menadzer menadzer, Porudzbina porudzbina, Set<Komentar> komentari, String naziv, String tip, Set<Artikal> artikli, Lokacija lokacija) {
        this.menadzer = menadzer;
        this.porudzbina = porudzbina;
        this.komentari = komentari;
        this.naziv = naziv;
        this.tip = tip;
        this.artikli = artikli;
        this.lokacija = lokacija;
        this.radi = true;
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

    public boolean isRadi() {
        return radi;
    }

    public void setRadi(boolean radi) {
        this.radi = radi;
    }
}
