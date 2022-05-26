package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.AdminRepository;
import com.example.WebProjekat.service.AdminService;
import com.example.WebProjekat.service.DostavljacService;
import com.example.WebProjekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/api/admin/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        admin logovaniAdmin = adminService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovaniAdmin==null)
        {
            return new ResponseEntity("Admin sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("admin",logovaniAdmin);
        return ResponseEntity.ok("Uspesno logovanje admine!");
    }

    @PostMapping("/api/admin/dodaj-menadzer")
    public ResponseEntity dodajM(@RequestBody Menadzer menadzer, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        menadzerService.dodajMenadzera(menadzer);

        return new ResponseEntity("Menadzer dodat!",HttpStatus.OK);
    }
    @GetMapping("/api/admin/dodeli-menadzera")
    public ResponseEntity dodeliM(@RequestParam String korisnickoIme, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }
        Restoran restoran = restoranRepository.getById(id);

        if (restoran == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restoran ne postoji.");
        }

        Menadzer menadzer = menadzerRepository.getById(korisnickoIme);
        menadzer.setZaduzenRestoran(restoran);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Menadzer uspesno postavljen.");
    }

    @PostMapping("/api/admin/dodaj-dostavljac")
    public ResponseEntity dodajD(@RequestBody Dostavljac dostavljac, HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Dostavljac dodat!",HttpStatus.FORBIDDEN);
        }

        dostavljacService.dodajDostavljaca(dostavljac);

        return new ResponseEntity("Dostavljac dodat!",HttpStatus.OK);
    }

    @PostMapping("/api/admin/logout")
    public ResponseEntity logout(HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin == null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Admin odjavljen!",HttpStatus.OK);
    }

    @GetMapping("/api/admin/pregled-korisnika")
    public ResponseEntity<Set<Korisnik>> sviKorisnici(HttpSession session)
    {
        admin logovaniAdmin = (admin) session.getAttribute("admin");

        if(logovaniAdmin==null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(adminService.dobaviKorisnike());
    }


}
