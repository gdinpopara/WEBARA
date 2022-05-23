package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa,Long>
{
}
