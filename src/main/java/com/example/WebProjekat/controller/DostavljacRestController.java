package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.DostavljacService;
import com.example.WebProjekat.service.PorudzbinaService;
import com.example.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class DostavljacRestController
{
    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping(value = "/api/dostavljac/login",
            consumes = MediaType.APPLICATION_JSON_VALUE, // URADJENO
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Dostavljac logovanidostavljac = dostavljacService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovanidostavljac==null)
        {
            return new ResponseEntity("Dostavljac sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("dostavljac",logovanidostavljac);
        return new ResponseEntity<>(loginDto,HttpStatus.OK);
    }



    @GetMapping(value = "/api/dostavljac/pregled-porudzbina-zaduzen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        String username = logovaniDostavljac.getKorisnickoIme();

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = dostavljacService.pregledajPorudzbineZaduzen(username);

        return new ResponseEntity<>(porudzbine,HttpStatus.OK);
    }

    @GetMapping("/api/dostavljac/pregled-porudzbina-slobodne")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbinaSlobodne(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        String username = logovaniDostavljac.getKorisnickoIme();

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = porudzbinaService.pregledajPorudzbineSlobodne(Status.CEKA_DOSTAVLJACA);

        return ResponseEntity.ok(porudzbine);
    }

    @PostMapping("/api/dostavljac/logout")
    public ResponseEntity logout(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null)
        {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Dostavljac je odjavljen!",HttpStatus.OK);
    }
    @GetMapping("/api/dostavljac/restorani")
    public ResponseEntity<Set<Restoran>> prikazRestorana(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null) {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.spisakRestorana();

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/dostavljac/pretragarpn")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoNazivu(@RequestParam String naziv , HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null) {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoNazivu(naziv);

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/dostavljac/pretragarpt")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoTipu(@RequestParam String tip ,HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null) {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoTipu(tip);

        return ResponseEntity.ok(restorani);
    }
    @GetMapping("/api/dostavljac/pretragarpl")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoLokaciji(@RequestBody Lokacija lokacija , HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null) {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoLokaciji(lokacija);

        return ResponseEntity.ok(restorani);
    }
}
