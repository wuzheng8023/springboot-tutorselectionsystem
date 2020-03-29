package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduateRepository extends JpaRepository<Graduate,Integer> {
}
