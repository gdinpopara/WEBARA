package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.izmenaDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.DostavljacRepository;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class DostavljacService
{
    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public String dodajDostavljaca(Dostavljac dostavljac)
    {
        dostavljac.setUloga(Uloga.DOSTAVLJAC);
        dostavljacRepository.save(dostavljac);
        return "Uspesno dodat dostavljac!";
    }

//    public String dodajMenadzera(Menadzer menadzer) {
//        menadzer.setUloga(Uloga.MENADZER);
//        menadzerRepository.save(menadzer);
//        return "Uspesno dodat menadzer!";
//    }

    public Dostavljac login(String username, String sifra)
    {
        Dostavljac dostavljac = dostavljacRepository.getById(username);
        if(dostavljac==null || !dostavljac.getLozinka().equals(sifra))
        {
            return null;
        }

        return dostavljac;
    }

    public Set<Porudzbina> pregledajPorudzbineZaduzen(String username)
    {
        Dostavljac dostavljac = dostavljacRepository.getById(username);

        return dostavljac.getPorudzbine();
    }

    public Dostavljac izmena(izmenaDto izmenadto, Dostavljac dostavljac)
    {
        if(!izmenadto.getIme().isEmpty())
        {
            dostavljac.setIme(izmenadto.getIme());
        }

        if(!izmenadto.getPrezime().isEmpty())
        {
            dostavljac.setPrezime(izmenadto.getPrezime());
        }

        if(!izmenadto.getPol().isEmpty())
        {
            dostavljac.setPol(izmenadto.getPol());
        }

        if(!(izmenadto.getDatumRodjenja()==null))
        {
            dostavljac.setDatumRodjenja(izmenadto.getDatumRodjenja());
        }

        dostavljacRepository.save(dostavljac);

        return dostavljac;

    }



}
