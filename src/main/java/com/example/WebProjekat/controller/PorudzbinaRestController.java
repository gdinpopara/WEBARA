package com.example.WebProjekat.controller;

import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.MenadzerService;
import com.example.WebProjekat.service.PorudzbinaService;
import com.example.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.UUID;

@RestController
public class PorudzbinaRestController
{
    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping(value = "/api/porudzbine/pripremi/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Porudzbina> uPripremi(@PathVariable UUID id, HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.U_PRIPREMI);

        return ResponseEntity.ok(porudzbina);
    }

    @PostMapping(value = "/api/porudzbine/ceka-dostavljaca/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Porudzbina> cekaDostavljaca(@PathVariable String id, HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(UUID.fromString(id), Status.CEKA_DOSTAVLJACA);

        return ResponseEntity.ok(porudzbina);
    }

    @PostMapping("/api/porudzbine/u-transportu/{id}")
    public ResponseEntity<Porudzbina> uTransportu(@PathVariable UUID id, HttpSession session)
    {
        Dostavljac dostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(dostavljac==null)
        {
            return new ResponseEntity("Dostavljac nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        porudzbinaService.dodajPorudzbinuDostavljacu(dostavljac,id);
        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.U_TRANSPORTU);


        return ResponseEntity.ok(porudzbina);
    }

    @PostMapping("/api/porudzbine/dostavljeno/{id}")
    public ResponseEntity<Porudzbina> dostavljeno(@PathVariable UUID id, HttpSession session)
    {
        Dostavljac dostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(dostavljac==null)
        {
            return new ResponseEntity("Dostavljac nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.DOSTAVLJENA);

        return ResponseEntity.ok(porudzbina);
    }

    @GetMapping("/api/prikaz/{id}")
    public ResponseEntity<Restoran> pregledRestorana(@PathVariable String id)
    {
        return ResponseEntity.ok(restoranService.nadjiPoImenu(id));
    }
}
