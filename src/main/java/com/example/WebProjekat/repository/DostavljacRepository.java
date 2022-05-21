package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Dostavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac,String> { }
