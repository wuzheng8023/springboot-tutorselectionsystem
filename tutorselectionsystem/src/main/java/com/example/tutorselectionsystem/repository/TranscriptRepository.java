package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Transcript;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranscriptRepository extends BaseRepository<Transcript,Integer> {
    //查询指定学生的全部课程
    @Query("from Transcript  t where t.graduate.id=:id ")
    Optional<List<Transcript>> findAllTranscriptByStudentid(@Param("id") Integer id);



}
