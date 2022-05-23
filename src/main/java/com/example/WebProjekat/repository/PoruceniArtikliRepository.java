package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.PoruceniArtikli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoruceniArtikliRepository extends JpaRepository<PoruceniArtikli,Long>
{
}
