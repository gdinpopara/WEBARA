package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.izmenaDto;
import com.example.WebProjekat.entity.Dostavljac;
import com.example.WebProjekat.entity.Korisnik;
import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.service.DostavljacService;
import com.example.WebProjekat.service.KupacService;
import com.example.WebProjekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class KorisnikRestController
{
    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private KupacService kupacService;

    @Autowired
    private DostavljacService dostavljacService;

    @GetMapping("/api/kupac-pregled")
    public ResponseEntity<Kupac> pregledK(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(logovaniKupac);
    }

    @GetMapping("/api/menadzer-pregled")
    public ResponseEntity<Menadzer> pregledM(HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(logovaniMenadzer);
    }

    @GetMapping("/api/dostavljac-pregled")
    public ResponseEntity<Dostavljac> pregledD(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(logovaniDostavljac);
    }

    @PostMapping ("/api/kupac-izmeni")
    public ResponseEntity<Kupac> izmeniK(@RequestBody izmenaDto izmenadto, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Kupac kupac = kupacService.izmena(izmenadto,logovaniKupac);
        return ResponseEntity.ok(kupac);
    }

    @PostMapping("/api/menadzer-izmeni")
    public ResponseEntity<Menadzer> izmeniM(@RequestBody izmenaDto izmenadto, HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Menadzer menadzer = menadzerService.izmena(izmenadto,logovaniMenadzer);

        return ResponseEntity.ok(menadzer);
    }

    @PostMapping("/api/dostavljac-izmeni")
    public ResponseEntity<Dostavljac> izmeniD(@RequestBody izmenaDto izmenadto, HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Dostavljac dostavljac = dostavljacService.izmena(izmenadto,logovaniDostavljac);

        return ResponseEntity.ok(dostavljac);
    }
}
