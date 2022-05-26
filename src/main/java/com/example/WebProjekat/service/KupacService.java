package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.izmenaDto;
import com.example.WebProjekat.entity.*;
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
        kupac.setUloga(Uloga.KUPAC);
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

    public Kupac izmena(izmenaDto izmenadto, Kupac kupac)
    {
        if(!izmenadto.getIme().isEmpty())
        {
            kupac.setIme(izmenadto.getIme());
        }

        if(!izmenadto.getPrezime().isEmpty())
        {
            kupac.setPrezime(izmenadto.getPrezime());
        }

        if(!izmenadto.getPol().isEmpty())
        {
            kupac.setPol(izmenadto.getPol());
        }

        if(!(izmenadto.getDatumRodjenja()==null))
        {
            kupac.setDatumRodjenja(izmenadto.getDatumRodjenja());
        }

        kupacRepository.save(kupac);

        return kupac;

    }
}
