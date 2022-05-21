package com.example.WebProjekat.entity;

import javax.persistence.*;

@Entity
public class admin
{

    @Id
    @Column(unique = true)
    private String username;

    @Column
    private String sifra;

    public admin(String username,String sifra)
    {
        this.username = username;
        this.sifra = sifra;
    }

    public admin(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
