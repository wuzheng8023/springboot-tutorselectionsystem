package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer> {
}
