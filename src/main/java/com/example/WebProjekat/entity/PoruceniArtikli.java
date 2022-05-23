package com.example.WebProjekat.entity;

import javax.persistence.*;

@Entity
public class PoruceniArtikli
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;

    @ManyToOne
    private Porudzbina porudzbina;

    @Column
    private int kolicina;

    @Column
    private double ukupnaCena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Korpa korpa;


    public PoruceniArtikli() {
    }

    public PoruceniArtikli(Long id, Artikal artikal, Porudzbina porudzbina, int kolicina, double ukupnaCena) {
        this.id = id;
        this.artikal = artikal;
        this.porudzbina = porudzbina;
        this.kolicina = kolicina;
        this.ukupnaCena = ukupnaCena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
