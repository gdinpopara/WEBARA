package com.example.WebProjekat.service;

import com.example.WebProjekat.entity.Artikal;
import com.example.WebProjekat.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtikalService
{
    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal nadjiPoId(String id)
    {
        Artikal artikal = new Artikal();
        artikal = artikalRepository.getById(id);
        return artikal;
    }
}
