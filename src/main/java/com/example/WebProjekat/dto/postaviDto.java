package com.example.WebProjekat.dto;

public class postaviDto
{
    private String menadzer;
    private String restoran;

    public postaviDto(String menadzer, String restoran) {
        this.menadzer = menadzer;
        this.restoran = restoran;
    }

    public postaviDto() {
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }

    public String getRestoran() {
        return restoran;
    }

    public void setRestoran(String restoran) {
        this.restoran = restoran;
    }
}
