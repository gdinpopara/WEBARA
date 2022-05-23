package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Kupac extends Korisnik
{
    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Set<Porudzbina> porudzbine, int brojBodova) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.porudzbine = porudzbine;
        this.brojBodova = brojBodova;
        this.uloga = Uloga.KUPAC;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, int brojBodova) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.brojBodova = brojBodova;
    }



    public Kupac()
    {}

    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private int brojBodova;

    @ManyToOne
    private TipKupca tipKupca;

    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    //@JsonIgnore
    @OneToOne(mappedBy = "kupac",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korpa korpa;

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    public void dodajBodove(int brojBodova)
    {
        this.brojBodova+=brojBodova;
    }
}
