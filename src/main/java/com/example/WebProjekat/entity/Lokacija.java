package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Lokacija
{
    public Lokacija(Long id, Restoran restoran, double geoSirina, double geoDuzina, String adresa) {
        this.id = id;
        this.restoran = restoran;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
    }

    public Lokacija(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Restoran restoran;

    @Column
    private double geoSirina;

    @Column
    private double geoDuzina;

    @Column
    private String adresa;

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public double getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

}
