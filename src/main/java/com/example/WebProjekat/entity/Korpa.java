package com.example.WebProjekat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Korpa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kupac kupac;

    @OneToMany(mappedBy = "korpa",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

    public Korpa(Kupac kupac, Set<PoruceniArtikli> poruceniArtikli) {
        this.kupac = kupac;
        this.poruceniArtikli = poruceniArtikli;
    }

    public Korpa() {
    }


    public int ukupnaCena()
    {
        double ukupnacena=0;
        for (PoruceniArtikli pa:poruceniArtikli)
        {
            ukupnacena+=pa.getUkupnaCena();
        }

        return (int)ukupnacena;
    }

    public void azuriraj(int kolicina,Artikal a)
    {
        for (PoruceniArtikli por:poruceniArtikli)
        {
            if(a==por.getArtikal())
            {
                int kol = por.getKolicina();
                por.setKolicina(kol+kolicina);
            }
        }

    }

    public void dodajPoruceneArtikle(PoruceniArtikli pa)
    {
        poruceniArtikli.add(pa);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<PoruceniArtikli> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<PoruceniArtikli> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }
}
