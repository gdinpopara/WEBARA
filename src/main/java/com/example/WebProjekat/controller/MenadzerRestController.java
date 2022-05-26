package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.MenadzerService;
import com.example.WebProjekat.service.PorudzbinaService;
import com.example.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class MenadzerRestController
{
    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping("/api/menadzer/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Menadzer logovaniMenadzer = menadzerService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("menadzer",logovaniMenadzer);
        return ResponseEntity.ok("Uspesno logovanje!");
    }

    @PostMapping("/api/menadzer/logout")
    public ResponseEntity logout(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("menadzer");

        if(logovaniKupac == null)
        {
            return new ResponseEntity("Menadzer nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Menadzer odjavljen!",HttpStatus.OK);
    }

    @GetMapping("/api/menadzer/porudzbine")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = porudzbinaService.porudzbineRestorana(menadzer);
        return ResponseEntity.ok(porudzbine);
    }
    @GetMapping("/api/menadzer/restorani")
    public ResponseEntity<Set<Restoran>> prikazRestorana(HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer == null) {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.spisakRestorana();

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/menadzer/pretragarpn")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoNazivu(@RequestParam String naziv , HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer == null) {
            return new ResponseEntity("Menadzer nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoNazivu(naziv);

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/menadzer/pretragarpt")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoTipu(@RequestParam String tip ,HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer == null) {
            return new ResponseEntity("Menadzer nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoTipu(tip);

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/menadzer/pretragarpl")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoLokaciji(@RequestBody Lokacija lokacija , HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer == null) {
            return new ResponseEntity("Menadzer nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoLokaciji(lokacija);

        return ResponseEntity.ok(restorani);
    }
}
