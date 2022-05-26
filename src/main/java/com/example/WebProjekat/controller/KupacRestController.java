package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.KorpaDto;
import com.example.WebProjekat.dto.KupacDto;
import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.dto.PovecajSmanjiDTO;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class KupacRestController
{
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/api/kupac/registracija")
    public String saveKupac(@RequestBody Kupac kupac)
    {
        this.kupacService.save(kupac);
        return "Uspesna registracija!";
    }

    @PostMapping("/api/kupac/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Kupac logovaniKupac = kupacService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("kupac",logovaniKupac);
        return ResponseEntity.ok("Uspesno logovanje!");
    }

    @PostMapping("/api/kupac/logout")
    public ResponseEntity logout(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null)
        {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Kupac odjavljen!",HttpStatus.OK);
    }


    @GetMapping("/api/kupac/pregled-porudzbina")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        String username = logovaniKupac.getKorisnickoIme();

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = kupacService.pregledajPorudzbine(username);

        return ResponseEntity.ok(porudzbine);
    }

    @PostMapping("/api/kupac/restoran/dodaj-u-korpu")
    public ResponseEntity<KorpaDto> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        if(korpaDto.getKolicina()<=0)
        {
            return new ResponseEntity("Morate uneti jedan ili vise artikala!",HttpStatus.BAD_REQUEST);
        }

        if( korpaDto.getIDartikal()==null || korpaDto.getNazivRestorana()==null)
        {
            return new ResponseEntity("Morate uneti podatke!",HttpStatus.BAD_REQUEST);
        }

        korpaService.dodajUKorpu(logovaniKupac,korpaDto);
        return ResponseEntity.ok(korpaDto);
    }

    @PostMapping("/api/kupac/restoran/izbaci-iz-korpe")
    public ResponseEntity<Korpa> izbaciIzKorpe(@RequestParam String nazivArtikla, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.izbaciIzKorpe(logovaniKupac,nazivArtikla);
        return ResponseEntity.ok(logovaniKupac.getKorpa());
    }

    @PostMapping("/api/kupac/pregled-korpe/povecaj-kolicinu")
    public ResponseEntity<Korpa> povecajKolicinu(@RequestBody PovecajSmanjiDTO povecajSmanjiDTO, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.povecaj(logovaniKupac,povecajSmanjiDTO);
        return ResponseEntity.ok(logovaniKupac.getKorpa());
    }

    @PostMapping("/api/kupac/pregled-korpe/smanji-kolicinu")
    public ResponseEntity<Korpa> smanjiKolicinu(@RequestBody PovecajSmanjiDTO povecajSmanjiDTO, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.smanji(logovaniKupac,povecajSmanjiDTO);
        return ResponseEntity.ok(logovaniKupac.getKorpa());
    }

    @GetMapping("/api/kupac/pregled-korpe")
    public ResponseEntity<Korpa> pregledKorpe(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Korpa korpa = korpaService.pregledajKorpu(logovaniKupac);
        return ResponseEntity.ok(korpa);

    }

    @PostMapping("/api/kupac/poruci")
    public ResponseEntity<Porudzbina> poruciIzRestorana(@RequestParam String restoranNaziv, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Restoran restoran = restoranService.nadjiPoImenu(restoranNaziv);
        Porudzbina porudzbina = porudzbinaService.poruci(logovaniKupac,restoran);

        return ResponseEntity.ok(porudzbina);
    }


}
