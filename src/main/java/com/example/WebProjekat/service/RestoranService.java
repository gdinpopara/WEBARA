package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Restoran;
import com.example.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran nadjiPoImenu(String nazivRestorana)
    {
        Restoran restoran = (Restoran) restoranRepository.getById(nazivRestorana);
        return restoran;
    }
}
