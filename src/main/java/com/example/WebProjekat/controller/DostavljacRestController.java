package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.DostavljacService;
import com.example.WebProjekat.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class DostavljacRestController
{
    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @PostMapping("/api/dostavljac/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Dostavljac logovanidostavljac = dostavljacService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovanidostavljac==null)
        {
            return new ResponseEntity("Admin sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("dostavljac",logovanidostavljac);
        return ResponseEntity.ok("Uspesno logovanje dostavljacu!");
    }

    @GetMapping("/api/dostavljac/pregled-porudzbina-zaduzen")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        String username = logovaniDostavljac.getKorisnickoIme();

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = dostavljacService.pregledajPorudzbineZaduzen(username);

        return ResponseEntity.ok(porudzbine);
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
}
