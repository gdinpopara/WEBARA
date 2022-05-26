package com.example.WebProjekat.dto;

import com.example.WebProjekat.entity.Tip;

public class ArtikalDto
{
    private String naziv;

    private Tip tip;

    private double cena;

    private String opis;

    private int kolicina;

    public ArtikalDto(String naziv, Tip tip, double cena, String opis, int kolicina) {
        this.naziv = naziv;
        this.tip = tip;
        this.cena = cena;
        this.opis = opis;
        this.kolicina = kolicina;
    }

    public ArtikalDto() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
