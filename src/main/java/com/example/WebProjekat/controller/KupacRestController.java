package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.KupacDto;
import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.entity.admin;
import com.example.WebProjekat.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class KupacRestController
{
    @Autowired
    private KupacService kupacService;

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

}
