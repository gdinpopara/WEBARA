package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Korisnik;
import com.example.WebProjekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService
{
    @Autowired
    private KorisnikRepository korisnikRepository;


}
