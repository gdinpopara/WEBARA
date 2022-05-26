package com.example.WebProjekat.service;

import com.example.WebProjekat.controller.DostavljacRestController;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.*;
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

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private PoruceniArtikliRepository poruceniArtikliRepository;

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;


    public Set<Porudzbina> pregledajPorudzbineSlobodne(Status status)
    {
        Set<Porudzbina> porudzbine = new HashSet<>(porudzbinaRepository.findAll());
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            if(p.getStatus()==status)
            {
                vrati.add(p);
            }
        }
        return vrati;//asd
    }

    public Set<Porudzbina> porudzbineRestorana(Menadzer menadzer)
    {
        Restoran restoran = menadzer.getZaduzenRestoran();
        Set<Porudzbina> porudzbine = new HashSet<>(porudzbinaRepository.findAll());
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            Restoran restoran1 = p.getRestoranPoruceno();
            if(restoran1.getNaziv().equals(restoran.getNaziv()))
            {
                vrati.add(p);
            }
        }
        return porudzbine;
    }


    public void Poruci(Porudzbina porudzbina)
    {
        porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina poruci(Kupac kupac, Restoran restoran)
    {
        Porudzbina porudzbina = new Porudzbina();


        Korpa korpa = kupac.getKorpa();

        //porudzbina.setArtikli(korpa.getArtikli());

        Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

        for (Artikal artikal:korpa.getArtikli())
        {
            PoruceniArtikli pa = new PoruceniArtikli();
            pa.setKolicina(artikal.getKolicina());
            pa.setArtikal(artikal);
            pa.setPorudzbina(porudzbina);
            poruceniArtikli.add(pa);

            porudzbinaRepository.save(porudzbina);
            poruceniArtikliRepository.save(pa);
        }

        //porudzbina.setArtikli(korpa.getArtikli());
        porudzbina.setPoruceniArtikli(poruceniArtikli);
        porudzbina.setDatumIVremePorudzbine(new Date());
        porudzbina.setKupacIme(kupac.getIme());
        porudzbina.setStatus(Status.OBRADA);
        porudzbina.setRestoranPoruceno(restoran);
        porudzbina.setKupac(kupac);
        porudzbina.setUkupnaCena(korpa.getUkupnaCena());

        int brBod = (int) korpa.getUkupnaCena();
        kupac.setBrojBodova((brBod/1000)*133);

        porudzbinaRepository.save(porudzbina);

        Set<Porudzbina> porudzbinas = kupac.getPorudzbine();
        porudzbinas.add(porudzbina);
        kupac.setPorudzbine(porudzbinas);

        korpa.setArtikli(null);
        korpa.setUkupnaCena(0);

        korpaRepository.save(korpa);
        kupac.setKorpa(null);
        kupacRepository.save(kupac);

        return porudzbina;
    }


    public Porudzbina promeniStatus(UUID uuid, Status status)
    {
        Porudzbina porudzbina = porudzbinaRepository.getById(uuid);

        porudzbina.setStatus(status);

        porudzbinaRepository.save(porudzbina);

        return porudzbina;
    }

    public void dodajPorudzbinuDostavljacu(Dostavljac dostavljac,UUID uuid)
    {
        Porudzbina porudzbina = porudzbinaRepository.getById(uuid);

        Set<Porudzbina> porudzbine  = new HashSet<>();
        porudzbine = dostavljac.getPorudzbine();
        dostavljac.setPorudzbine(porudzbine);
        porudzbina.setDostavljac(dostavljac);

        dostavljacRepository.save(dostavljac);
        porudzbinaRepository.save(porudzbina);

    }
}
