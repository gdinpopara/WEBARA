package com.example.WebProjekat.dto;

import com.example.WebProjekat.entity.Tip;

public class IzmenaArtiklaDto
{


    private double cena;

    private  String opis;

    private Tip tip;

    private int kolicina;

    public IzmenaArtiklaDto(double cena, String opis, Tip tip, int kolicina) {
        this.cena = cena;
        this.opis = opis;
        this.tip = tip;
        this.kolicina = kolicina;
    }

    public IzmenaArtiklaDto() {
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
}
