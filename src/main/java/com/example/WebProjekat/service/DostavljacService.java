package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Dostavljac;
import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.entity.admin;
import com.example.WebProjekat.repository.DostavljacRepository;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DostavljacService
{
    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public String dodajDostavljaca(Dostavljac dostavljac)
    {
        dostavljacRepository.save(dostavljac);
        return "Uspesno dodat dostavljac!";
    }

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



}
