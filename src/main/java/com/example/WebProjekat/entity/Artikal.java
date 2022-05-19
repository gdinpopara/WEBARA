package com.example.WebProjekat.entity;

import javax.persistence.*;

;

@Entity
public class Artikal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Porudzbina porudzbina;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Restoran restoran;

    @Column
    private String naziv;

    @Column
    private double cena;

    @Column
    private Tip tip;

    @Column
    private int kolicina;

    @Column
    private String opis;


    public Artikal(String naziv, double cena, Tip tip, int kolicina, String opis)
    {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Artikal()
    {
        this.naziv = "Mleko";
        this.cena = 100;
        this.tip = Tip.PICE;
        this.kolicina = 100;
        this.opis = "Mleko brt";
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
