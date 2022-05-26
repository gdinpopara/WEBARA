package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.*;
import com.example.WebProjekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminService
{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public admin login(String username, String sifra)
    {
        admin Admin = adminRepository.getById(username);
        if(Admin==null || !Admin.getSifra().equals(sifra))
        {
            return null;
        }

        return Admin;
    }

    public Set<Korisnik> dobaviKorisnike()
    {
        Set<Korisnik> vrati = new HashSet<>(korisnikRepository.findAll());
        return vrati;
    }


}
