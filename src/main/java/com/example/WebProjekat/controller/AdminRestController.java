package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.AdminRepository;
import com.example.WebProjekat.repository.MenadzerRepository;
import com.example.WebProjekat.repository.RestoranRepository;
import com.example.WebProjekat.service.AdminService;
import com.example.WebProjekat.service.DostavljacService;
import com.example.WebProjekat.service.MenadzerService;
import com.example.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class AdminRestController
{
    @Autowired
    AdminService adminService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    RestoranService restoranService;

    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

    @PostMapping(value = "/api/admin/login",
            consumes = MediaType.APPLICATION_JSON_VALUE, // URADJENO
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        admin logovaniAdmin = adminService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovaniAdmin==null)
        {
            return new ResponseEntity("Admin sa ovim podacima ne postoji!",HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("admin",logovaniAdmin);
        return new ResponseEntity<>(loginDto,HttpStatus.OK);
    }



    @PostMapping(value = "/api/admin/dodaj-menadzer", // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menadzer> dodajM(@RequestBody Menadzer menadzer, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.BAD_REQUEST);
        }

        menadzerService.dodajMenadzera(menadzer);

        return new ResponseEntity<>(menadzer,HttpStatus.OK);
    }


    @PostMapping(value = "/api/admin/dodaj-dostavljac", // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Dostavljac> dodajD(@RequestBody Dostavljac dostavljac, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.BAD_REQUEST);
        }

        dostavljacService.dodajDostavljaca(dostavljac);

        return new ResponseEntity<>(dostavljac,HttpStatus.OK);
    }




    @GetMapping("/api/admin/dodeli-menadzera")// TREBA ISPRAVITI
    public ResponseEntity dodeliM(@RequestParam String korisnickoIme, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Restoran restoran = restoranRepository.getById(korisnickoIme);

        if (restoran == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restoran ne postoji.");
        }

        Menadzer menadzer = menadzerRepository.getById(korisnickoIme);
        menadzer.setZaduzenRestoran(restoran);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Menadzer uspesno postavljen.");
    }



    @PostMapping(value = "/api/admin/dodaj-restoran", // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restoran> dodajR(@RequestBody Restoran restoran, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.BAD_REQUEST);
        }

        restoranService.dodajRestoran(restoran);

        return new ResponseEntity<>(restoran,HttpStatus.OK);
    }



    @PostMapping(value = "/api/admin/logout")// URADJENO
    public ResponseEntity logout(HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.BAD_REQUEST);
        }

        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping(value = "/api/admin/pregled-korisnika", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Set<Korisnik>> sviKorisnici(HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin==null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(adminService.dobaviKorisnike());
    }




    @GetMapping(value = "/api/admin/restorani", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Set<Restoran>> prikazRestorana(HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.spisakRestorana();

        return ResponseEntity.ok(restorani);
    }




    @GetMapping("/api/admin/pretragarpn")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoNazivu(@RequestParam String naziv ,HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoNazivu(naziv);

        return ResponseEntity.ok(restorani);
    }




    @GetMapping("/api/admin/pretragarpt")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoTipu(@RequestParam String tip ,HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoTipu(tip);

        return ResponseEntity.ok(restorani);
    }



    @GetMapping("/api/admin/pretragarpl")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoLokaciji(@RequestBody Lokacija lokacija , HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoLokaciji(lokacija);

        return ResponseEntity.ok(restorani);
    }


}
