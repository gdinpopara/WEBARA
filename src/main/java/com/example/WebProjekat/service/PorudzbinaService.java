package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.entity.Status;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
}
