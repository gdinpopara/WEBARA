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
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/api/kupac-pregled", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Kupac> pregledK(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(logovaniKupac,HttpStatus.OK);
    }

    @GetMapping(value = "/api/menadzer-pregled", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Menadzer> pregledM(HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(logovaniMenadzer,HttpStatus.OK);
    }

    @GetMapping(value = "/api/dostavljac-pregled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dostavljac> pregledD(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(logovaniDostavljac,HttpStatus.OK);
    }

    @PostMapping (value = "/api/kupac-izmeni",     // uradjeno
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Kupac> izmeniK(@RequestBody izmenaDto izmenadto, HttpSession session) // URADJENO
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Kupac kupac = kupacService.izmena(izmenadto,logovaniKupac);
        return new ResponseEntity<>(kupac,HttpStatus.OK);
    }

    @PostMapping(value = "/api/menadzer-izmeni",     // uradjeno
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menadzer> izmeniM(@RequestBody izmenaDto izmenadto, HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Menadzer menadzer = menadzerService.izmena(izmenadto,logovaniMenadzer);

        return new ResponseEntity<>(menadzer,HttpStatus.OK);
    }

    @PostMapping(value = "/api/dostavljac-izmeni",     // uradjeno
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dostavljac> izmeniD(@RequestBody izmenaDto izmenadto, HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Dostavljac dostavljac = dostavljacService.izmena(izmenadto,logovaniDostavljac);

        return new ResponseEntity<>(dostavljac,HttpStatus.OK);
    }
}
