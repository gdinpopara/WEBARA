package com.example.WebProjekat.dto;

public class PovecajSmanjiDTO
{
    private String nazivArtikla;
    private int kolicina;

    public PovecajSmanjiDTO(String nazivArtikla, int kolicina) {
        this.nazivArtikla = nazivArtikla;
        this.kolicina = kolicina;
    }

    public PovecajSmanjiDTO() {
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
