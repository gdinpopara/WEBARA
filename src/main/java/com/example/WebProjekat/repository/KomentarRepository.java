package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar,Long> { }
