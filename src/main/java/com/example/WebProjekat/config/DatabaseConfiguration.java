package com.example.WebProjekat.config;

import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Configuration
public class DatabaseConfiguration
{
    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Bean
    public boolean instantiate()
    {
        /*Artikal artikal1 = new Artikal("Cevapi",200, Tip.JELO,50,"Narodno jelo od mlevenog mesa juneceg porekla");
        Artikal artikal2 = new Artikal("Pljeskavica",150,Tip.JELO,20,"ide gas");

        Set<Artikal> artikli = new HashSet<>();
        artikli.add(artikal1);
        artikli.add(artikal2);

        Set<Porudzbina> porudzbine = new HashSet<>();




        Menadzer aleksa = new Menadzer("popara", "popara123", "Aleksa", "Lukac", "M", new Date());
        menadzerRepository.save(aleksa);
        Kupac nikola = new Kupac("cveya","cveya123","Nikola","Cvejic","M", new Date(),porudzbine,20);
        kupacRepository.save(nikola);

        Restoran restoran = new Restoran(aleksa,"Siki");
        restoranRepository.save(restoran);

        Set<Kupac> kupci = new HashSet<>();
        kupci.add(nikola);
        TipKupca tp = new TipKupca(42,50,kupci);

        tipKupcaRepository.save(tp);

        restoran.setArtikli(artikli);

        nikola.setTipKupca(tp);


        Dostavljac dostavljac1 = new Dostavljac("aaaa","123","aaa","aaa","M",new Date(),porudzbine);
        Porudzbina porudzbina1 = new Porudzbina(dostavljac1,artikli,nikola,restoran,new Date(),200,"pera",Status.DOSTAVLJENA);
        porudzbine.add(porudzbina1);



        menadzerRepository.save(aleksa);
        kupacRepository.save(nikola);*/

        //Menadzer aleksa = new Menadzer("popara", "popara123", "Aleksa", "Lukac", "M", new Date());
        //menadzerRepository.save(aleksa);

        admin admin1 = new admin("admin1","nekasifra");
        adminRepository.save(admin1);

        return true;
    }

}
