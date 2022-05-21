package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac,String> { }
