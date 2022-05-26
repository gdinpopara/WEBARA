package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.izmenaDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.MenadzerRepository;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class MenadzerService
{
    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public String dodajMenadzera(Menadzer menadzer)
    {
        menadzer.setUloga(Uloga.MENADZER);
        menadzerRepository.save(menadzer);
        return "Uspesno dodat menadzer!";
    }

    public Menadzer login(String username, String sifra)
    {
        Menadzer menadzer = menadzerRepository.getById(username);
        if(menadzer==null || !menadzer.getLozinka().equals(sifra))
        {
            return null;
        }

        return menadzer;
    }

    public Menadzer izmena(izmenaDto izmenadto, Menadzer menadzer)
    {
        if(!izmenadto.getIme().isEmpty())
        {
            menadzer.setIme(izmenadto.getIme());
        }

        if(!izmenadto.getPrezime().isEmpty())
        {
            menadzer.setPrezime(izmenadto.getPrezime());
        }

        if(!izmenadto.getPol().isEmpty())
        {
            menadzer.setPol(izmenadto.getPol());
        }

        if(!(izmenadto.getDatumRodjenja()==null))
        {
            menadzer.setDatumRodjenja(izmenadto.getDatumRodjenja());
        }

        menadzerRepository.save(menadzer);

        return menadzer;

    }


}
