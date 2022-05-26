package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class PoruceniArtikli
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Artikal artikal;

    @ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private Porudzbina porudzbina;

    @Column
    private int kolicina;


    public PoruceniArtikli() {
    }

    public PoruceniArtikli(Artikal artikal, Porudzbina porudzbina, int kolicina) {
        this.artikal = artikal;
        this.porudzbina = porudzbina;
        this.kolicina = kolicina;;
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

}
