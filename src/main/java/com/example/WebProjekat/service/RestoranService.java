package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Artikal;
import com.example.WebProjekat.entity.Lokacija;
import com.example.WebProjekat.entity.Restoran;
import com.example.WebProjekat.repository.MenadzerRepository;
import com.example.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran nadjiPoImenu(String nazivRestorana)
    {
        Restoran restoran = (Restoran) restoranRepository.getById(nazivRestorana);
        return restoran;
    }
    public String dodajRestoran(Restoran restoran) {
        restoranRepository.save(restoran);
        return "Uspesno dodat restoran!";
    }

    public Set<Restoran> spisakRestorana() {
        Set<Restoran> restorani = new HashSet<>(restoranRepository.findAll());
        return restorani;
    }
    public Set<Restoran> pretraziRpoNazivu(String naziv)
    {
        Set<Restoran> restorani =new HashSet<>(restoranRepository.findAll()) ;
        Set<Restoran> vrati = new HashSet<>();
        for (Restoran r:restorani)
        {
            if(r.getNaziv().equals(naziv))
            {
                vrati.add(r);
            }
        }
        return vrati;
    }
    public Set<Restoran> pretraziRpoTipu(String tip)
    {
        Set<Restoran> restorani = new HashSet<>(restoranRepository.findAll());
        Set<Restoran> vrati = new HashSet<>();
        for (Restoran r:restorani)
        {
            if(r.getTip().equals(tip))
            {
                vrati.add(r);
            }
        }
        return vrati;
    }
    public Set<Restoran> pretraziRpoLokaciji(Lokacija lokacija)
    {
        Set<Restoran> restorani = new HashSet<>(restoranRepository.findAll());
        Set<Restoran> vrati = new HashSet<>();
        for (Restoran r:restorani)
        {
            if(r.getLokacija()==lokacija)
            {
                vrati.add(r);
            }
        }
        return vrati;
    }

    public Set<Artikal> vratiArtikle(String id)
    {
        Restoran restoran = (Restoran) restoranRepository.getById(id);
        return restoran.getArtikli();
    }
}
