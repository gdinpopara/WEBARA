package com.example.WebProjekat.dto;

import com.example.WebProjekat.entity.Kupac;

import java.util.Date;

public class KupacDto
{
    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String pol;

    private Date datumRodjenja;

    public KupacDto() {
    }

    public KupacDto(String korisnickoIme, String lozinka, String ime, String pol, Date datumRodjenja)
    {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }


    public KupacDto(Kupac kupac)
    {
        this.korisnickoIme = kupac.getKorisnickoIme();
        this.lozinka = kupac.getLozinka();
        this.ime = kupac.getIme();
        this.pol = kupac.getPol();
        this.datumRodjenja = kupac.getDatumRodjenja();
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
