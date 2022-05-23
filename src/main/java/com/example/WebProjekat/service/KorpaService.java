package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.KorpaDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.ArtikalRepository;
import com.example.WebProjekat.repository.KorpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KorpaService {
    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    public boolean dodajUKorpu(Kupac kupac, KorpaDto korpaDto) {

        if (kupac.getKorpa()==null)
        {
            Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            PoruceniArtikli pa = new PoruceniArtikli();
            pa.setArtikal(artikal);
            pa.setKolicina(korpaDto.getKolicina());

            poruceniArtikli.add(pa);

            Korpa korpa = new Korpa(kupac,poruceniArtikli);

            kupac.setKorpa(korpa);
            korpaRepository.save(korpa);
            return true;
        }

        else
        {
            Korpa k = kupac.getKorpa();
            for (PoruceniArtikli pa : k.getPoruceniArtikli()) {
                Artikal a = pa.getArtikal();
                if (korpaDto.getIDartikal() == a.getNaziv()) {
                    k.azuriraj(korpaDto.getKolicina(), a);
                    korpaRepository.save(k);

                    return true;
                }
            }// resiti restoran nekako


            Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();
            poruceniArtikli = k.getPoruceniArtikli();


            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            PoruceniArtikli pa = new PoruceniArtikli();
            pa.setArtikal(artikal);
            pa.setKolicina(korpaDto.getKolicina());

            poruceniArtikli.add(pa);

            k.setPoruceniArtikli(poruceniArtikli);
            korpaRepository.save(k);
            return true;
        }
    }

    public Korpa pregledajKorpu(Kupac kupac)
    {
        return kupac.getKorpa();
    }
}