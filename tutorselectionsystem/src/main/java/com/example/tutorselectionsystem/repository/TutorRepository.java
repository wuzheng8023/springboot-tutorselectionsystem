package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer> {
  //查询所有老师
    @Query("from Tutor")
    Optional<List<Tutor>> tutorList();
    //根据老师id查询老师
    @Query("from Tutor t where  t.id=:id")
    Optional<Tutor> findById(@Param("id") Integer id);
    //根据老师姓名查询老师
    @Query("from Tutor  t where  t.name =:name")
    Optional<Tutor> findByName(@Param("name") String name);
}
