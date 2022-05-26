package com.example.WebProjekat.dto;

public class LokacijaDto {
    private double geoSirina;

    private double geoDuzina;

    private String adresa;

    public LokacijaDto(double geoSirina, double geoDuzina, String adresa) {
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
    }

    public LokacijaDto() {
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public double getGeoDuzina() {
        return geoDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
