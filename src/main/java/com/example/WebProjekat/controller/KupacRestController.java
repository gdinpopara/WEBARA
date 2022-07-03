package com.example.WebProjekat.controller;

import com.example.WebProjekat.dto.KorpaDto;
import com.example.WebProjekat.dto.KupacDto;
import com.example.WebProjekat.dto.LoginDto;
import com.example.WebProjekat.dto.PovecajSmanjiDTO;
import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class KupacRestController
{
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(value = "/api/kupac/registracija", // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Kupac> saveKupac(@RequestBody Kupac kupac)
    {
        if(kupacService.pronadjiKupca(kupac)==null)
        {
            return new ResponseEntity<>(kupac,HttpStatus.BAD_REQUEST);
        }
        this.kupacService.save(kupac);
        return new ResponseEntity<>(kupac,HttpStatus.OK);
    }

    @PostMapping(value = "/api/kupac/login",   // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Kupac logovaniKupac = kupacService.login(loginDto.getKorisnickoIme(),loginDto.getPassword());

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("kupac",logovaniKupac);
        return new ResponseEntity<>(loginDto,HttpStatus.OK);
    }

    @PostMapping("/api/kupac/logout") // URADJENO
    public ResponseEntity logout(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null)
        {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/api/kupac/{id}/artikli", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Artikal>> pregledArtikala(@PathVariable String id, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null)
        {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }

        Set<Artikal> artikals = restoranService.vratiArtikle(id);

        return new ResponseEntity<>(artikals,HttpStatus.OK);
    }


    @GetMapping(value = "/api/kupac/pregled-porudzbina", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        String username = logovaniKupac.getKorisnickoIme();

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = kupacService.pregledajPorudzbine(username);

        return new ResponseEntity<>(porudzbine,HttpStatus.OK);
    }

    @PostMapping(value = "/api/kupac/dodaj-u-korpu", // URADJENO
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorpaDto> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        if(korpaDto.getKolicina()<=0)
        {
            return new ResponseEntity("Morate uneti jedan ili vise artikala!",HttpStatus.BAD_REQUEST);
        }

        korpaService.dodajUKorpu(logovaniKupac,korpaDto);
        return new ResponseEntity<>(korpaDto,HttpStatus.OK);
    }


    @PostMapping(value = "/api/kupac/restoran/izbaci-iz-korpe/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO (ne radi)
    public ResponseEntity<String> izbaciIzKorpe(@PathVariable String id, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.izbaciIzKorpe(logovaniKupac,id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PostMapping(value = "/api/kupac/pregled-korpe/povecaj-kolicinu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<PovecajSmanjiDTO> povecajKolicinu(@RequestBody PovecajSmanjiDTO povecajSmanjiDTO, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.povecaj(logovaniKupac,povecajSmanjiDTO);
        return new ResponseEntity<>(povecajSmanjiDTO,HttpStatus.OK);
    }

    @PostMapping(value = "/api/kupac/pregled-korpe/smanji-kolicinu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<PovecajSmanjiDTO> smanjiKolicinu(@RequestBody PovecajSmanjiDTO povecajSmanjiDTO, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        korpaService.smanji(logovaniKupac,povecajSmanjiDTO);
        return new ResponseEntity<>(povecajSmanjiDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/api/kupac/pregled-korpe", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Korpa> pregledKorpe(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Korpa korpa = korpaService.pregledajKorpu(logovaniKupac);
        return new ResponseEntity<>(korpa,HttpStatus.OK);
    }

    @GetMapping(value = "/api/kupac/pregled-korpe/artikli", produces = MediaType.APPLICATION_JSON_VALUE) //URADJENO
    public ResponseEntity<Set<Artikal>> pregledajKorpu(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(korpaService.vratiArtikle(logovaniKupac),HttpStatus.OK);
    }


    @PostMapping(value = "/api/kupac/poruci/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> poruciIzRestorana(@PathVariable String id, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Restoran restoran = restoranService.nadjiPoImenu(id);
        Porudzbina porudzbina = porudzbinaService.poruci(logovaniKupac,restoran);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @GetMapping(value = "/api/kupac/restorani", produces = MediaType.APPLICATION_JSON_VALUE) // URADJENO
    public ResponseEntity<Set<Restoran>> prikazRestorana(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null) {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.spisakRestorana();

        return new ResponseEntity<>(restorani,HttpStatus.OK);
    }

    @GetMapping("/api/kupac/pretragarpn")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoNazivu(@RequestParam String naziv , HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null) {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoNazivu(naziv);

        return ResponseEntity.ok(restorani);
    }

    @GetMapping("/api/kupac/pretragarpt")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoTipu(@RequestParam String tip ,HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null) {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoNazivu(tip);

        return ResponseEntity.ok(restorani);
    }

    @GetMapping("/api/kupac/pretragarpl")
    public ResponseEntity<Set<Restoran>> pretraziRestoranPoLokaciji(@RequestBody Lokacija lokacija , HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac == null) {
            return new ResponseEntity("Kupac nije logovan!",HttpStatus.FORBIDDEN);
        }
        Set<Restoran> restorani = restoranService.pretraziRpoLokaciji(lokacija);

        return ResponseEntity.ok(restorani);
    }

}
