package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Dostavljac;
import com.example.WebProjekat.entity.Kupac;
import com.example.WebProjekat.entity.Menadzer;
import com.example.WebProjekat.entity.admin;
import com.example.WebProjekat.repository.AdminRepository;
import com.example.WebProjekat.repository.DostavljacRepository;
import com.example.WebProjekat.repository.KupacRepository;
import com.example.WebProjekat.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService
{
    @Autowired
    private AdminRepository adminRepository;

    public admin login(String username, String sifra)
    {
        admin Admin = adminRepository.getById(username);
        if(Admin==null || !Admin.getSifra().equals(sifra))
        {
            return null;
        }

        return Admin;
    }


}
