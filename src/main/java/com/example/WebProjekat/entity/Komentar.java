package com.example.WebProjekat.entity;

import javax.persistence.*;

enum Ocena{JEDAN, DVA, TRI, CETIRI, PET};

@Entity
public class Komentar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Kupac kupac;

    @ManyToOne
    private Restoran restoran;

    @Column
    private String tekstKomentara;

    @Column
    private Ocena ocena;

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public Ocena getOcena() {
        return ocena;
    }

    public void setOcena(Ocena ocena) {
        this.ocena = ocena;
    }

    public Komentar(Kupac kupac, Restoran restoran, String tekstKomentara, Ocena ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    public Komentar(){}
}
