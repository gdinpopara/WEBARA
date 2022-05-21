package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.service.MenadzerService;
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
public class MenadzerRestController
{
    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private PorudzbinaService porudzbinaService;

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

}
