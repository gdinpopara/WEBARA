package com.example.WebProjekat.service;

import com.example.WebProjekat.dto.KorpaDto;
import com.example.WebProjekat.dto.PovecajSmanjiDTO;
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
            Set<Artikal> artikals = new HashSet<>();

            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            artikal.setKolicina(korpaDto.getKolicina());
            double ukupnaCena = korpaDto.getKolicina()*artikal.getCena();

            artikals.add(artikal);

            Korpa korpa = new Korpa(kupac,artikals,ukupnaCena);

            kupac.setKorpa(korpa);
            korpaRepository.save(korpa);
            return true;
        }

        else
        {
              Korpa k = kupac.getKorpa();
              double ukupnaCena = k.getUkupnaCena();


              for (Artikal artikal:k.getArtikli())
              {
                  if(korpaDto.getIDartikal().equals(artikal.getNaziv()))
                  {
                      Set<Artikal> artikals = new HashSet<>();
                      artikals = k.getArtikli();
                      int kol = artikal.getKolicina();
                      artikals.remove(artikal);

                      Artikal artikall = new Artikal();
                      artikall = artikalRepository.getById(korpaDto.getIDartikal());

                      artikall.setKolicina(korpaDto.getKolicina()+kol);
                      ukupnaCena-=kol*artikall.getCena();
                      ukupnaCena+=artikall.getCena()*(korpaDto.getKolicina()+kol);

                      artikals.add(artikall);

                      k.setArtikli(artikals);
                      k.setUkupnaCena(ukupnaCena);
                      korpaRepository.save(k);

                      return true;
                  }
              }


            Set<Artikal> artikals = new HashSet<>();

            artikals = k.getArtikli();


            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            artikal.setKolicina(korpaDto.getKolicina());
            ukupnaCena+=artikal.getCena()*korpaDto.getKolicina();

            artikals.add(artikal);

            k.setArtikli(artikals);
            k.setUkupnaCena(ukupnaCena);
            korpaRepository.save(k);
            return true;
        }
    }

    public Korpa pregledajKorpu(Kupac kupac)
    {
        return kupac.getKorpa();
    }

    public Set<Artikal> vratiArtikle(Kupac kupac)
    {
        return kupac.getKorpa().getArtikli();
    }

    public Korpa izbaciIzKorpe(Kupac kupac, String nazivArtikla)
    {
        if (kupac.getKorpa()==null)
        {
            return kupac.getKorpa();
        }

        else
        {
            Korpa korpa = kupac.getKorpa();
            Set<Artikal> artikli = korpa.getArtikli();
            for (Artikal artikal:artikli)
            {
                if(artikal.getNaziv().equals(nazivArtikla))
                {
                    int kol = artikal.getKolicina();
                    double cena = artikal.getCena();
                    artikli.remove(artikal);
                    korpa.setArtikli(artikli);
                    double novaCena = korpa.getUkupnaCena() - kol*cena;
                    korpa.setUkupnaCena(novaCena);
                    korpaRepository.save(korpa);
                    return korpa;
                }
            }
        }

        return kupac.getKorpa();
    }

    public Korpa povecaj(Kupac kupac, PovecajSmanjiDTO povecajSmanjiDTO)
    {
        if (kupac.getKorpa()==null)
        {
            return kupac.getKorpa();
        }
        else
        {
            Korpa korpa = kupac.getKorpa();
            Set<Artikal> artikli = korpa.getArtikli();
            for (Artikal artikal:artikli)
            {
                if(artikal.getNaziv().equals(povecajSmanjiDTO.getNazivArtikla()))
                {
                    Artikal izmenjeniArtikal = artikal;
                    int kol = povecajSmanjiDTO.getKolicina();
                    int novaKol = kol + artikal.getKolicina();

                    double cena = artikal.getCena();
                    double novaCena = korpa.getUkupnaCena() + kol*cena;

                    izmenjeniArtikal.setKolicina(novaKol);
                    korpa.setUkupnaCena(novaCena);

                    Set<Artikal> izmenjeniArtikli = new HashSet<>();
                    izmenjeniArtikli = artikli;
                    izmenjeniArtikli.remove(artikal);
                    izmenjeniArtikli.add(izmenjeniArtikal);

                    korpa.setArtikli(izmenjeniArtikli);

                    korpaRepository.save(korpa);
                    return korpa;
                }
            }
        }

        return kupac.getKorpa();
    }

    public Korpa smanji(Kupac kupac, PovecajSmanjiDTO povecajSmanjiDTO)
    {
        if (kupac.getKorpa()==null)
        {
            return kupac.getKorpa();
        }
        else
        {
            Korpa korpa = kupac.getKorpa();
            Set<Artikal> artikli = korpa.getArtikli();
            for (Artikal artikal:artikli)
            {
                if(artikal.getNaziv().equals(povecajSmanjiDTO.getNazivArtikla()))
                {
                    Artikal izmenjeniArtikal = artikal;
                    int kol = povecajSmanjiDTO.getKolicina();
                    int novaKol = artikal.getKolicina()-kol;

                    if(novaKol<=0)
                    {
                        return korpa;
                    }

                    double cena = artikal.getCena();
                    double novaCena = korpa.getUkupnaCena() - kol*cena;

                    izmenjeniArtikal.setKolicina(novaKol);
                    korpa.setUkupnaCena(novaCena);

                    Set<Artikal> izmenjeniArtikli = new HashSet<>();
                    izmenjeniArtikli = artikli;
                    izmenjeniArtikli.remove(artikal);
                    izmenjeniArtikli.add(izmenjeniArtikal);

                    korpa.setArtikli(izmenjeniArtikli);

                    korpaRepository.save(korpa);
                    return korpa;
                }
            }
        }

        return kupac.getKorpa();
    }


}