package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.KorpaRepository;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class PorudzbinaService
{
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public Set<Porudzbina> pregledajPorudzbineSlobodne(Status status)
    {
        Set<Porudzbina> porudzbine = (Set<Porudzbina>) porudzbinaRepository.findAll();
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            if(p.getStatus()==p.getStatus())
            {
                vrati.add(p);
            }
        }
        return vrati;
    }

    public Set<Porudzbina> porudzbineRestorana(Menadzer menadzer)
    {
        Set<Porudzbina> porudzbine = (Set<Porudzbina>) porudzbinaRepository.findAll();
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            if(p.getRestoranPoruceno()==menadzer.getZaduzenRestoran())
            {
                vrati.add(p);
            }
        }
        return vrati;
    }


    public void Poruci(Porudzbina porudzbina)
    {
        porudzbinaRepository.save(porudzbina);
    }

    public boolean poruci(Kupac kupac)
    {
        Porudzbina porudzbina = new Porudzbina();
        Korpa korpa = kupac.getKorpa();

        porudzbina.setPoruceniArtikli(korpa.getPoruceniArtikli());
        porudzbina.setDatumIVremePorudzbine(new Date());
        porudzbina.setKupacIme(kupac.getIme());
        porudzbina.setStatus(Status.OBRADA);


        kupac.dodajBodove(korpa.ukupnaCena());

        return true;
    }
}
