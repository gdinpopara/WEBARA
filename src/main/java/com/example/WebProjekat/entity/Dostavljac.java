package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik
{
    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Set<Porudzbina> porudzbine) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.porudzbine = porudzbine;
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(){}

    @OneToMany(mappedBy = "dostavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

}