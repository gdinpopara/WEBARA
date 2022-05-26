package com.example.WebProjekat.controller;

import com.example.WebProjekat.entity.Dostavljac;
import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.entity.Porudzbina;
import com.example.WebProjekat.entity.Status;
import com.example.WebProjekat.service.MenadzerService;
import com.example.WebProjekat.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/porudzbine/pripremi")
    public ResponseEntity<Porudzbina> uPripremi(@RequestParam UUID id, HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.U_PRIPREMI);

        return ResponseEntity.ok(porudzbina);
    }

    @PostMapping("/api/porudzbine/ceka-dostavljaca")
    public ResponseEntity<Porudzbina> cekaDostavljaca(@RequestParam UUID id, HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.CEKA_DOSTAVLJACA);

        return ResponseEntity.ok(porudzbina);
    }

    @PostMapping("/api/porudzbine/u-transportu")
    public ResponseEntity<Porudzbina> uTransportu(@RequestParam UUID id, HttpSession session)
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

    @PostMapping("/api/porudzbine/dostavljeno")
    public ResponseEntity<Porudzbina> dostavljeno(@RequestParam UUID id, HttpSession session)
    {
        Dostavljac dostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(dostavljac==null)
        {
            return new ResponseEntity("Dostavljac nije prijavljen!", HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaService.promeniStatus(id, Status.DOSTAVLJENA);

        return ResponseEntity.ok(porudzbina);
    }
}
