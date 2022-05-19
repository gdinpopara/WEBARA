package com.example.WebProjekat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik
{
    @OneToOne(mappedBy = "menadzer")
    private Restoran zaduzenRestoran;

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.uloga = Uloga.MENADZER;
    }

    public Menadzer() {}

    public Restoran getZaduzenRestoran() {
        return zaduzenRestoran;
    }

    public void setZaduzenRestoran(Restoran zaduzenRestoran) {
        this.zaduzenRestoran = zaduzenRestoran;
    }

}
