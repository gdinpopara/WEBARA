package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca,Long> { }
