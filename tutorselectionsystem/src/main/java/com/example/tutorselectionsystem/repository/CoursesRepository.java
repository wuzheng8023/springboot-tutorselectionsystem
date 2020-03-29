package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.repository.Impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends BaseRepository<Courses,Integer> {
}
