package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends BaseRepository<Transcript,Integer> {
}
