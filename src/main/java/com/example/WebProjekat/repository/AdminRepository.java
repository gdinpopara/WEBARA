package com.example.WebProjekat.repository;

import com.example.WebProjekat.entity.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<admin,String>
{
}
