package com.example.WebProjekat.dto;

import java.util.UUID;

public class KorpaDto
{
    private String IDartikal;
    private String kolicina;
    private String nazivRestorana;


    public KorpaDto(String IDartikal, String kolicina, String nazivRestorana)
    {
        this.IDartikal = IDartikal;
        this.kolicina = kolicina;
        this.nazivRestorana = nazivRestorana;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public KorpaDto() {
    }

    public String getIDartikal() {
        return IDartikal;
    }

    public void setIDartikal(String IDartikal) {
        this.IDartikal = IDartikal;
    }

    public int getKolicina() {
        return Integer.parseInt(kolicina);
    }

    public void setKolicina(int kolicina) {
        this.kolicina = Integer.toString(kolicina);
    }
}
