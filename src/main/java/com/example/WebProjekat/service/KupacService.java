package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class KupacService
{
    @Autowired
    private KupacRepository kupacRepository;



    public Kupac save(Kupac kupac)
    {
        return kupacRepository.save(kupac);
    }

    public Kupac login(String username,String sifra)
    {
        Kupac kupac = kupacRepository.getById(username);
        if(kupac==null || !kupac.getLozinka().equals(sifra))
        {
            return null;
        }

        return kupac;
    }

    public Set<Porudzbina> pregledajPorudzbine(String username)
    {
        Kupac kupac = kupacRepository.getById(username);

        return kupac.getPorudzbine();
    }
}
