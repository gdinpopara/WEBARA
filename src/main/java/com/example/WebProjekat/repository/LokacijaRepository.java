package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija,Long> { }
