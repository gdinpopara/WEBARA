package com.example.WebProjekat.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

enum Ime{ZLATNI, SREBRNI, BRONZANI};

@Entity
public class TipKupca
{

    @Column
    private Ime ime;

    @Column
    private double popust;

    @Id
    private int brojBodova;

    @OneToMany
    private Set<Kupac> kupci = new HashSet<>();


    public TipKupca(double popust, int brojBodova, Set<Kupac> kupci) {
        this.ime = Ime.BRONZANI;
        this.popust = popust;
        this.brojBodova = brojBodova;
        this.kupci = kupci;
    }

    public TipKupca(){}

    public Ime getIme() {
        return ime;
    }

    public void setIme(Ime ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Set<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(Set<Kupac> kupci) {
        this.kupci = kupci;
    }
}
