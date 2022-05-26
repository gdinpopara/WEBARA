package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> { }
