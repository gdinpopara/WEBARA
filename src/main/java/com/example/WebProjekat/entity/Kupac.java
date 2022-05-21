package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "kupac",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private int brojBodova;

    @ManyToOne
    private TipKupca tipKupca;

    @OneToMany(mappedBy = "kupac",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

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


}
