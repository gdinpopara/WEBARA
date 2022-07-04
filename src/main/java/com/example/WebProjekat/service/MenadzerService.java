package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.ArtikalDto;
import com.example.WebProjekat.dto.IzmenaArtiklaDto;
import com.example.WebProjekat.dto.KorpaDto;
import com.example.WebProjekat.dto.izmenaDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.ArtikalRepository;
import com.example.WebProjekat.repository.MenadzerRepository;
import com.example.WebProjekat.repository.PorudzbinaRepository;
import com.example.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class MenadzerService {
    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    public String dodajMenadzera(Menadzer menadzer) {
        menadzer.setUloga(Uloga.MENADZER);
        menadzerRepository.save(menadzer);
        return "Uspesno dodat menadzer!";
    }

    public void postaviMenadzera(String menadzer, String restoran)
    {
        Restoran rest = (Restoran) restoranRepository.getById(restoran);
        Menadzer men = (Menadzer) menadzerRepository.getById(menadzer);

        rest.setMenadzer(men);
        men.setZaduzenRestoran(rest);

        menadzerRepository.save(men);
        restoranRepository.save(rest);
    }

    public Menadzer login(String username, String sifra) {
        Menadzer menadzer = menadzerRepository.getById(username);
        if (menadzer == null || !menadzer.getLozinka().equals(sifra)) {
            return null;
        }

        return menadzer;
    }

    public Menadzer izmena(izmenaDto izmenadto, Menadzer menadzer) {
        if (!izmenadto.getIme().isEmpty()) {
            menadzer.setIme(izmenadto.getIme());
        }

        if (!izmenadto.getPrezime().isEmpty()) {
            menadzer.setPrezime(izmenadto.getPrezime());
        }

        if (!izmenadto.getPol().isEmpty()) {
            menadzer.setPol(izmenadto.getPol());
        }

        if (!(izmenadto.getDatumRodjenja() == null)) {
            menadzer.setDatumRodjenja(izmenadto.getDatumRodjenja());
        }

        menadzerRepository.save(menadzer);

        return menadzer;

    }

    public Set<Artikal> dodajURestoran(Menadzer menadzer, ArtikalDto artikalDto)
    {
        Restoran restoran = menadzer.getZaduzenRestoran();
        Artikal artikal = new Artikal();

        artikal.setTip(artikalDto.getTip());
        artikal.setCena(artikalDto.getCena());
        artikal.setNaziv(artikalDto.getNaziv());
        artikal.setKolicina(artikalDto.getKolicina());
        artikal.setOpis(artikalDto.getOpis());
        artikal.setRestoran(restoran);

        Set<Artikal> artikli = new HashSet<>();
        artikli = restoran.getArtikli();
        artikli.add(artikal);

        restoran.setArtikli(artikli);

        restoran.setArtikli(artikli);
        artikalRepository.save(artikal);
        restoranRepository.save(restoran);

        return artikli;
    }

    public Set<Artikal> azurirajIzRestorana(Menadzer menadzer, String nazivArtikla)
    {
        Restoran restoran = menadzer.getZaduzenRestoran();
        Artikal artikal = artikalRepository.getById(nazivArtikla);
        Set<Artikal> artikli1 = restoran.getArtikli();


        Set<Artikal> artikli = new HashSet<>();

        for (Artikal a:artikli1)
        {
            if(!nazivArtikla.equals(a.getNaziv()))
            {
                artikli.add(a);
            }
        }

        restoran.setArtikli(artikli);

        artikalRepository.delete(artikal);
        restoranRepository.save(restoran);

        return artikli;
    }

    public Artikal obrisiIzRestorana(Menadzer menadzer, IzmenaArtiklaDto izmenaArtiklaDto, String naziv)
    {
        Restoran restoran = menadzer.getZaduzenRestoran();
        Set<Artikal> artikli1 = restoran.getArtikli();

        Artikal art = new Artikal();
        Set<Artikal> artikli = new HashSet<>();

        for (Artikal a:artikli1)
        {
            if(a.getNaziv().equals(naziv))
            {
                art.setNaziv(a.getNaziv());

                if(izmenaArtiklaDto.getCena()>0)
                {
                    art.setCena(izmenaArtiklaDto.getCena());
                }

                if(izmenaArtiklaDto.getKolicina()>=0)
                {
                    art.setKolicina(izmenaArtiklaDto.getKolicina());
                }

                if(!izmenaArtiklaDto.getOpis().isEmpty())
                {
                    art.setOpis(izmenaArtiklaDto.getOpis());
                }

                if(izmenaArtiklaDto.getTip()!=null)
                {
                    art.setTip(izmenaArtiklaDto.getTip());
                }
            }
        }
        art.setRestoran(restoran);
        artikalRepository.save(art);
        return art;

    }

}