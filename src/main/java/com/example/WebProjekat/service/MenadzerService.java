package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.entity.Status;
import com.example.WebProjekat.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MenadzerService
{
    @Autowired
    private MenadzerRepository menadzerRepository;

    public String dodajMenadzera(Menadzer menadzer)
    {
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

    public boolean PromeniStatus(Porudzbina porudzbina, Status status)
    {
        if(status!=Status.U_PRIPREMI || status!=Status.CEKA_DOSTAVLJACA)
        {
            return false;
        }

        else
        {
            porudzbina.setStatus(status);
        }

        return true;
    }


}
