package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal,String> { }
